package com.example.filmback.services;

import com.example.filmback.model.FilmUser;
import com.example.filmback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    public boolean authenticateUser(String username, String password) {
        FilmUser filmUser = userRepository.getReferenceById(username);

        if (filmUser == null) {
            return false;
        } else {
            if (filmUser.getPassword().equals(password)) {
                return true;
            }
            return false;
        }
    }
}
