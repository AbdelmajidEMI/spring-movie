package com.example.filmback.security;



import com.example.filmback.model.FilmUser;
import lombok.Data;


@Data
public class RegistrationForm {

    private String username;
    private String password;


    public FilmUser toUser() {
        return new FilmUser(this.username, this.password);
    }

}
