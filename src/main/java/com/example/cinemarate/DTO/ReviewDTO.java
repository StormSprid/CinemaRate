package com.example.cinemarate.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private Long id;
    private int rating;
    private String username;
    private String text;
}
