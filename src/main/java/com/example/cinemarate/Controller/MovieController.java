package com.example.cinemarate.Controller;

import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Entity.ReviewEntity;
import com.example.cinemarate.OMDB_MIGRATOR.MigrationProcessor;
import com.example.cinemarate.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/movie")
@RestController()
public class MovieController {
    @Autowired
    MigrationProcessor migrationProcessor;
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/{id}")
    public MovieEntity getMovie( @PathVariable String id){
        System.out.println("Get a request to a film with id " + id);
        return movieRepository.getById(Long.valueOf(id));

    }
    @GetMapping("/migration")
    public void migrateDb(){
        migrationProcessor.executeMigration("imdb_film_data.csv");
    }
    @GetMapping("/all")
    public List<MovieEntity> getAllMovies(){
        System.out.println("Get a request to get all movies");
        return movieRepository.findAll();
    }

}
