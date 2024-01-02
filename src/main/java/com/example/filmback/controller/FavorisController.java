package com.example.filmback.controller;


import com.example.filmback.model.Favoris;
import com.example.filmback.model.FavorisID;
import com.example.filmback.model.FilmUser;
import com.example.filmback.repository.FavorisRepository;
import com.example.filmback.repository.UserRepository;
import com.example.filmback.security.RegistrationController;
import com.example.filmback.services.FavoriService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/favoris",
        produces="application/json"
)
@CrossOrigin(origins="http://localhost:4201")
public class FavorisController {
    private final FavorisRepository favorisRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);


    @Autowired
    private FavoriService favoriService;


    public FavorisController(FavorisRepository favorisRepository, UserRepository userRepository) {
        this.favorisRepository = favorisRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{username}")
    public List<Favoris> getFavorisByUser(@PathVariable String username) {
        return favorisRepository.findByFilmUser(username);
    }

    @PostMapping("/add")
    public Favoris saveFavorite(@RequestBody FavorisID f) {
        String username = f.getUsername();
        String filmId = f.getFilmId();

        FilmUser user = userRepository.getReferenceById(username);
        Favoris favoris = new Favoris(filmId, user);
        return favorisRepository.save(favoris);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteFavorite(@RequestBody FavorisID f) {
        String username = f.getUsername();
        String filmId = f.getFilmId();

        Favoris favoris = favorisRepository.findFirstByFilmIdAndFilmUser(username, filmId).get(0);

        try {
            logger.info("no longer like ? : {}", username + " the Film :" + filmId);
            favorisRepository.delete(favoris);
            return ResponseEntity.ok("Favoris deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Favoris");
        }
    }



    @PostMapping
    public ResponseEntity<Map<String, Object>> isFavorite(@RequestBody FavorisID f) {
        String username = f.getUsername();
        String filmId = f.getFilmId();
        // Check credentials against the database
        boolean isFavorite = favoriService.checkFavorite(username, filmId);


        Map<String, Object> response = new HashMap<>();
        if (isFavorite) {
            response.put("success", true);
        } else {
            response.put("success", false);
        }

        return ResponseEntity.ok(response);
    }
}



