package com.example.cinemarate.OMDB_MIGRATOR;

import com.example.cinemarate.Controller.MovieController;
import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MigrationProcessor {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    final OmdbConnector connector;


    final MovieRepository movieRepository;

    @Async
    public void executeMigration(String csvPath) {
        List<String> ids = CsvParser.getListOfFilmTitles(csvPath);


        for (String id : ids) {
            OmdbMovieDTO dto = connector.fetchMovieById(id);
            MovieEntity movie = MovieEntity.create(dto.getTitle(), dto.getDescription(), Integer.parseInt(dto.getYear()), dto.getPosterUrl());
            movieRepository.save(movie);
            logger.info("Movie {} has been saved into database", dto.title);

        }
    }
}



