package com.example.cinemarate.ServiceImpl;

import com.example.cinemarate.Converter.MovieConverter;
import com.example.cinemarate.DTO.MovieDTO;
import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Entity.MovieStatus;
import com.example.cinemarate.Repository.MovieRepository;
import com.example.cinemarate.Service.MovieService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);



    @Override
    public MovieEntity createMovie(MovieEntity movie) {
        if (movieRepository.findByTitle(movie.getTitle()).isPresent()){
            String msg = String.format("Movie with Title %s is already exist",movie.getTitle());
            logger.warn(msg);
            throw new EntityExistsException(msg);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(a-> a.getAuthority().equals("ROLE_ADMIN"));
        if(isAdmin){

            movie.setStatus(MovieStatus.APPROVED);
        }
        logger.info(movie.toString());
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

    @Override
    public MovieEntity updateMovieTitle( Long id,String newTitle) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);
        if(movieEntityOptional.isPresent()) {
            MovieEntity m = movieEntityOptional.get();
            m.setTitle(newTitle);
            return m;
        }else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public MovieEntity updateMovieDescription(Long id, String newDescription) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);
        if(movieEntityOptional.isPresent()) {
            MovieEntity m = movieEntityOptional.get();
            m.setTitle(newDescription);
            return m;
        }else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public MovieEntity updateMovieYear(Long id, String newYear) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);
        if(movieEntityOptional.isPresent()) {
            MovieEntity m = movieEntityOptional.get();
            m.setTitle(newYear);
            return m;
        }else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<MovieEntity> search(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Page<MovieEntity> findAllPageable(Pageable pageable) {
        return movieRepository.findAll(pageable);

    }

    @Override
    public MovieEntity getMovie(Long id) {
        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movie.increaseView();

        movieRepository.save(movie);
        return movie;
    }



    @Override
    public Page<MovieDTO> getMoviesWithStatus(String status, Pageable pageable) {
        return movieRepository.getMoviesByStatus(status, pageable)
                .map(MovieConverter::toDto);
    }

    @Override
    public MovieEntity changeMovieStatus(Long id, String status) {
        MovieEntity movie = movieRepository.findById(id).orElseThrow();
        try {


            movie.setStatus(MovieStatus.valueOf(status.toUpperCase()));
            return movieRepository.save(movie);
        }catch (IllegalArgumentException e){

            throw new IllegalArgumentException(e.getMessage());
        }   }


}

