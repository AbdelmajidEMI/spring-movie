package com.example.filmback;

import com.example.filmback.repository.CommentRepository;
import com.example.filmback.repository.FavorisRepository;
import com.example.filmback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmBackApplication.class, args);
    }


    @Autowired
    UserRepository userRepository;

    @Autowired
    FavorisRepository favorisRepository;

    @Autowired
    CommentRepository commentRepository;


	/*
	@Bean
	CommandLineRunner coucou(UserRepository userRepository) {
		return args -> {
			FilmUser user = new FilmUser("abd", "0101");
			userRepository.save(user);



			Comment comment1 = new Comment("848326", user, "I love this movie 3 times");
			commentRepository.save(comment1);



			user = userRepository.findById("abd").orElse(null);

			List<Favoris> favorises = favorisRepository.findByFilmUser(user.getUsername());




			if (user != null) {
				System.out.println(user.getUsername());
				System.out.println(user.getFavoris());
				System.out.println(user.getComments());
				System.out.println(favorises);

			} else {
				System.out.println("User not found!");
			}
		};
	}

	 */
}
