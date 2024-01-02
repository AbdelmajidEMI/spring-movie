package com.example.filmback.repository;


import com.example.filmback.model.Favoris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface FavorisRepository extends JpaRepository<Favoris, Long> {
    @Query("SELECT f FROM Favoris f WHERE f.filmUser.username = :filmUserName")
    List<Favoris> findByFilmUser(String filmUserName);

    @Query("SELECT f FROM Favoris f WHERE f.filmUser.username = :a and f.filmId = :b")
    List<Favoris> findFirstByFilmIdAndFilmUser(@Param("a") String a, @Param("b") String b);


}
