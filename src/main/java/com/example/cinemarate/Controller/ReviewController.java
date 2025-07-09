package com.example.cinemarate.Controller;

import com.example.cinemarate.DTO.ReviewDTO;
import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Entity.ReviewEntity;
import com.example.cinemarate.Repository.MovieRepository;
import com.example.cinemarate.Repository.ReviewRepository;
import com.example.cinemarate.ServiceImpl.ReviewServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/review")
@RestController
public class ReviewController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private  ReviewServiceImpl reviewServiceImpl;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;




    @GetMapping("/{movieId}")
    public List<ReviewEntity> getMovieReviews(@PathVariable Long movieId){
        return reviewRepository.getAllByMovie_Id(movieId);
    }
    @PostMapping("/create/{movieId}")
    public ResponseEntity<ReviewEntity> createReview(@PathVariable Long movieId,@RequestBody ReviewDTO review){
        MovieEntity movie = movieRepository.findById(movieId).orElseThrow();
        logger.info("Get a request to create a review to a movie {}",movie.getTitle());



        return ResponseEntity.status(HttpStatus.CREATED).body(reviewServiceImpl.createReview(review,movie));


    }

}
