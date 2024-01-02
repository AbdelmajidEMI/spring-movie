package com.example.filmback.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "favoris")
public class Favoris implements Serializable {

    public Favoris(String filmId, FilmUser filmUser) {
        this.filmUser = filmUser;
        this.filmId = filmId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Favoriteid;


    @Column(name = "film_id")
    private String filmId;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "favoris_username", referencedColumnName = "username")
    @JsonBackReference
    private FilmUser filmUser;




    @Override
    public String toString() {
        return "Favoris{" +
                "filmId='" + filmId + '\'' +
                " Film user is :" + filmUser.getUsername() +
                '}';
    }
}
