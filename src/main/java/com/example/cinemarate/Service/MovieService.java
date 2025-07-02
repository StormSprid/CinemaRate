package com.example.cinemarate.Service;

import com.example.cinemarate.Entity.MovieEntity;

import java.util.List;

public interface MovieService {
    public MovieEntity getMovieById(Long id);
    public MovieEntity save(MovieEntity m);
    List<MovieEntity> allMovies();
    void deleteMovie(MovieEntity m);
}
