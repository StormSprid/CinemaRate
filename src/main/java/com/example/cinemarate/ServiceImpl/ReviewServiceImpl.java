package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.DTO.ReviewDTO;
import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Entity.ReviewEntity;
import com.example.cinemarate.Repository.ReviewRepository;
import com.example.cinemarate.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Override

    public ReviewEntity createReview(ReviewDTO reviewDTO, MovieEntity movie) {
        ReviewEntity review = ReviewEntity.create(reviewDTO.getRating(),reviewDTO.getUserId(),reviewDTO.getText(),movie);
        return reviewRepository.save(review);

    }

    @Override
    public ReviewEntity updateReview(ReviewEntity review) {
        return null;
    }

    @Override
    public void deleteReview(Long id) {
            reviewRepository.delete(reviewRepository.findById(id).orElseThrow());
    }
}
