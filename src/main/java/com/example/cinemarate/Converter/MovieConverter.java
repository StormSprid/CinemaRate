package com.example.cinemarate.Converter;

import com.example.cinemarate.DTO.MovieDTO;
import com.example.cinemarate.DTO.ReviewDTO;
import com.example.cinemarate.Entity.MovieEntity;

import java.util.stream.Collectors;

public class MovieConverter {
    public static MovieDTO toDto(MovieEntity movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setYear(movie.getYear());
        dto.setPosterUrl(movie.getPosterUrl());
        dto.setMeanRating(movie.getMeanRating());
        dto.setViews(movie.getViews());

        dto.setReviews(movie.getReviews().stream().map(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setId(review.getId());
            reviewDTO.setRating(review.getRating());
            reviewDTO.setText(review.getText());
            reviewDTO.setUsername(review.getUser() != null ? review.getUser().getUsername() : "Anonymous");
            return reviewDTO;
        }).collect(Collectors.toList()));

        return dto;
    }
}
