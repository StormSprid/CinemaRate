package com.example.cinemarate.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "review")
public class ReviewEntity {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    @Setter
    private String text;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private MovieEntity movie;
    @Setter
    @ManyToOne
    @JoinColumn(name= "user_id")
    @JsonIgnore
    private UserEntity user;


    public static ReviewEntity create(int rating, String text, MovieEntity movie, UserEntity user){
        ReviewEntity review = new ReviewEntity();

        review.setRating(rating);
        review.setUser(user);
        review.setText(text);
        review.setMovie(movie);
        return review;
    }

    public void setRating(int rating) {
        if(rating<1 || rating >10) {
        throw  new IllegalArgumentException("Rating must be 1-10");
        }

        this.rating = rating;

    }

}
