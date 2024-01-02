package com.example.filmback.services;

import com.example.filmback.model.Favoris;
import com.example.filmback.model.FilmUser;
import com.example.filmback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriService {
    @Autowired
    UserRepository userRepository;



    public boolean checkFavorite(String username, String filmId) {
        FilmUser filmUser = userRepository.getReferenceById(username);
        List<Favoris> favorisList = filmUser.getFavoris();
        for (Favoris f: favorisList) {
            if(f.getFilmId().equals(filmId)) {
                return true;
            }
        }
        return false;
    }
}
