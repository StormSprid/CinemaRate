package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Repository.MovieRepository;
import com.example.cinemarate.Service.MovieService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieEntity createMovie(MovieEntity movie) {
        return null;
    }

    @Override
    public MovieEntity updateMovie(MovieEntity movie, Long id) {
        return null;
    }

    @Override
    public void deleteMovie(Long id) {
        Optional<MovieEntity> m = movieRepository.findById(id);
        movieRepository.delete(m.orElseThrow(() -> new EntityNotFoundException(
                String.format("No movie with id %d was found",id)
        ))
        );
    }

}

