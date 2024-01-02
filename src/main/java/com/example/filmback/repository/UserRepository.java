package com.example.filmback.repository;

import com.example.filmback.model.FilmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<FilmUser, String> {

}
