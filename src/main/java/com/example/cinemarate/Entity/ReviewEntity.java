package com.example.cinemarate.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReviewEntity {
    Long id;
    int rating;
    int userId;
}
