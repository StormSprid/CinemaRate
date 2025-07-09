package com.example.cinemarate.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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
    private Long userId;
    @Setter
    private String text;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private MovieEntity movie;


    public static ReviewEntity create(int rating,Long userId,String text,MovieEntity movie){
        ReviewEntity review = new ReviewEntity();

        review.setRating(rating);
        review.setUserId(userId);
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
