package com.example.filmback.security;


import com.example.filmback.model.FilmUser;
import com.example.filmback.model.UserID;
import com.example.filmback.repository.UserRepository;
import com.example.filmback.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/register",
                produces="application/json"
)
@CrossOrigin(origins="http://localhost:4201")
public class RegistrationController {
    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private UserRepository userRepo;
    private AuthService authService;

    public RegistrationController(UserRepository userRepo, AuthService authService) {
        this.userRepo = userRepo;
        this.authService = authService;
    }

    @PostMapping(path = "/login", consumes="application/json")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserID credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();


        // Check credentials against the database
        boolean authenticated = authService.authenticateUser(username, password);
        logger.info("User logging in : {}", username + " Status :" + authenticated);

        Map<String, Object> response = new HashMap<>();
        if (authenticated) {
            response.put("success", true);
        } else {
            response.put("success", false);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public FilmUser processRegistration(@RequestBody RegistrationForm form) {
        return userRepo.save(form.toUser());
    }
}
