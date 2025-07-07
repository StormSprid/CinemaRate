package com.example.cinemarate.OMDB_MIGRATOR;

import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MigrationProcessor {
    @Autowired
    OmdbConnector connector;

    @Autowired
    MovieRepository movieRepository;

    public  void executeMigration(String csvPath){
        List<String> ids = CsvParser.getListOfFilmTitles(csvPath);
        for (String id :ids){
            OmdbMovieDTO dto = connector.fetchMovieById(id);
            MovieEntity movie = MovieEntity.create(dto.getTitle(), dto.getDescription(), Integer.parseInt(dto.getYear()),dto.getPosterUrl());
            movieRepository.save(movie);
            System.out.println("Movie " + dto.title + "has been saved into database");
        }
    }
}
