package com.example.filmback.controller;


import com.example.filmback.model.Comment;
import com.example.filmback.model.CommentID;
import com.example.filmback.model.FilmUser;
import com.example.filmback.repository.CommentRepository;
import com.example.filmback.repository.UserRepository;
import com.example.filmback.security.RegistrationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/comment",
        produces="application/json"
)
@CrossOrigin(origins="http://localhost:4201")
public class CommentController {
    private final UserRepository userRepository;
    private  final CommentRepository commentRepository;
    private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    public CommentController(UserRepository userRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }


    @PostMapping("/add")
    public Comment saveComment(@RequestBody CommentID f) {
        String username = f.getUsername();
        String filmId = f.getFilmId();
        String comment = f.getComment();

        FilmUser user = userRepository.getReferenceById(username);
        Comment comment1 = new Comment(filmId, user, comment);
        return commentRepository.save(comment1);
    }

    @GetMapping("/{filmId}")
    public List<Comment> getAllComments(@PathVariable String filmId) {
        return commentRepository.findAllByFilmId(filmId);
    }

}
