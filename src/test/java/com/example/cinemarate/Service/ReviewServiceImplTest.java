package com.example.cinemarate.Service;

import com.example.cinemarate.DTO.ReviewDTO;
import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Entity.ReviewEntity;
import com.example.cinemarate.Entity.UserEntity;
import com.example.cinemarate.Repository.ReviewRepository;
import com.example.cinemarate.Repository.UserRepository;
import com.example.cinemarate.ServiceImpl.ReviewServiceImpl;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ReviewServiceImplTest {
    private ReviewService reviewService;
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    @BeforeEach
    void setUp(){
        reviewRepository = mock(ReviewRepository.class);
        userRepository = mock(UserRepository.class);
        reviewService = new ReviewServiceImpl(reviewRepository,userRepository);
    }

    @Test
    void testCreateReview(){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(1L);
        reviewDTO.setUsername("User");
        reviewDTO.setText("Good");
        reviewDTO.setRating(5);

        MovieEntity movie = MovieEntity.create("a","a",2004,"a");
        UserEntity user = UserEntity.create("User","@","12345678");
        when(userRepository.findByUsername("User")).thenReturn(Optional.of(user));

        ReviewEntity review = mock(ReviewEntity.class);
        when(reviewRepository.save(any(ReviewEntity.class))).thenReturn(review);

        ReviewEntity result = reviewService.createReview(reviewDTO, movie);

        assertNotNull(result);
        verify(userRepository).findByUsername("User");
        verify(reviewRepository).save(any(ReviewEntity.class));
    }}
