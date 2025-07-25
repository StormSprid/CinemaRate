package com.example.cinemarate.Service;

import com.example.cinemarate.Entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MovieService {
   MovieEntity createMovie(MovieEntity movie);
   MovieEntity updateMovie(MovieEntity movie,Long id);
   void deleteMovie(Long id);
   MovieEntity updateMovieTitle(Long id,String newTitle);
   MovieEntity updateMovieDescription(Long id,String newDescription);
   MovieEntity updateMovieYear(Long id,String newYear);
   List<MovieEntity> search(String title);
   Page<MovieEntity> findAllPageable(Pageable pageable);

}
