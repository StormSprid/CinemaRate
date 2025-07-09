package com.example.cinemarate.Service;

import com.example.cinemarate.DTO.ReviewDTO;
import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Entity.ReviewEntity;

import java.util.concurrent.locks.ReadWriteLock;

public interface ReviewService {
    ReviewEntity createReview(ReviewDTO reviewDTO, MovieEntity movie);
    ReviewEntity updateReview(ReviewEntity review);
    void deleteReview(Long id);
}
