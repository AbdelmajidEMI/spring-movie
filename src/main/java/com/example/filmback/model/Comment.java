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
@Table(name = "comments")
public class Comment implements Serializable{

    public Comment(String filmId, FilmUser filmUser, String comment) {
        this.filmUser = filmUser;
        this.username = this.filmUser.getUsername();
        this.filmId = filmId;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonBackReference
    private Long commentId;

    @Column(name = "film_id")
    private String filmId;


    @Column(name = "comment_text")
    private String comment;

    @Column(name = "comment_user_name")
    private String username;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_username", referencedColumnName = "username")
    @JsonBackReference
    private FilmUser filmUser;



    @Override
    public String toString() {
        return "Comment{" +
                "filmId='" + filmId + '\'' +
                " comment's user is :" + filmUser.getUsername() +
                " Comment is :" + comment +
                '}';
    }

}


