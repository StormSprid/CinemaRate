package com.example.cinemarate.Controller;

import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Entity.ReviewEntity;
import com.example.cinemarate.OMDB_MIGRATOR.MigrationProcessor;
import com.example.cinemarate.Repository.MovieRepository;
import com.example.cinemarate.ServiceImpl.MovieServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/movie")
@RestController()
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    MigrationProcessor migrationProcessor;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    private MovieServiceImpl movieServiceImpl;

    @GetMapping("/{id}")
    public MovieEntity getMovie( @PathVariable String id){
        logger.info("Get a request to a film with id {}", id);
        return movieRepository.getById(Long.valueOf(id));

    }
    @GetMapping("/migration")
    public void migrateDb(){
        logger.info("Migration from CSV file has been started");
        migrationProcessor.executeMigration("imdb_film_data.csv");
    }
    @GetMapping("/all")
    public List<MovieEntity> getAllMovies(){
        logger.info("Get a request to get all movies");
        return movieRepository.findAll();
    }
    @PostMapping("/create")
    public ResponseEntity<MovieEntity> createMovie(@RequestBody MovieEntity movie){
        logger.info("Get a request to create a movie {}",movie.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(movieServiceImpl.createMovie(movie));
    }
    @PutMapping("/update")
    public MovieEntity updateMovie(@RequestBody MovieEntity movie,@RequestParam Long id){
        logger.info("Get a request to update a movie {}",movie.getTitle());
        return movieServiceImpl.updateMovie(movie,id);

    }
    @DeleteMapping()
    public void deleteMovie(@RequestParam Long id){
        logger.info("Get a request to delete a movie {}",movieRepository.getById(id).getTitle());
        movieServiceImpl.deleteMovie(id);
    }
}
