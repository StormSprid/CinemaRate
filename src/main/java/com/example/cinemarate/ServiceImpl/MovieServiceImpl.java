package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Service.MovieService;

import java.util.List;

public class MovieServiceImpl implements MovieService {
    @Override
    public MovieEntity getMovieById(Long id) {
        return null;
    }

    @Override
    public MovieEntity save(MovieEntity m) {
        return null;
    }

    @Override
    public List<MovieEntity> allMovies() {
        return List.of();
    }

    @Override
    public void deleteMovie(MovieEntity m) {

    }
}
