package com.example.cinemarate.Service;

import com.example.cinemarate.Entity.MovieEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MovieService {
   MovieEntity createMovie(MovieEntity movie);
   MovieEntity updateMovie(MovieEntity movie,Long id);
   void deleteMovie(Long id);
}
