package com.example.filmback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "film_user")
public class FilmUser implements Serializable {
    public FilmUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.favoris = new ArrayList<>();
    }

    @Id
    @Column(name = "username")
    private String username;
    private String password;

    @OneToMany(mappedBy = "filmUser", fetch = FetchType.EAGER)
    private List<Favoris> favoris;

    @OneToMany(mappedBy = "filmUser", fetch = FetchType.EAGER)
    private List<Comment> comments;




}
