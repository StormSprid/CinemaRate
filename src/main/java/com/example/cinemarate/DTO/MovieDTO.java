package com.example.cinemarate.DTO;

import com.example.cinemarate.Entity.MovieStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String title;
    private String description;
    private int year;
    private String posterUrl;
    private double meanRating;
    private List<ReviewDTO> reviews;
    private int views;
    private MovieStatus status;
}
