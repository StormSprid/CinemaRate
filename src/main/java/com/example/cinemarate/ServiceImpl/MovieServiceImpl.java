package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Repository.MovieRepository;
import com.example.cinemarate.Service.MovieService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (movieRepository.findByTitle(movie.getTitle()).isPresent()){
            throw new EntityExistsException(String.format("Movie with Title %d is already exist",movie.getTitle()));
        }
        movieRepository.save(movie);

        return movie;
    }

    @Override
    public MovieEntity updateMovie(MovieEntity movie, Long id) {
        Optional<MovieEntity> optMovie = movieRepository.findById(id);
        if(optMovie.isPresent()){
            MovieEntity newMovie = optMovie.get();
            newMovie.setId(id);
            newMovie.setTitle(movie.getTitle());
            newMovie.setDescription(movie.getDescription());
            newMovie.setYear(movie.getYear());
            newMovie.setPosterUrl(movie.getPosterUrl());
            newMovie.setReviews(movie.getReviews());
            movieRepository.save(newMovie);
            return newMovie;
        }
        else{
            throw new EntityExistsException("Error with movie updating");
        }
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

