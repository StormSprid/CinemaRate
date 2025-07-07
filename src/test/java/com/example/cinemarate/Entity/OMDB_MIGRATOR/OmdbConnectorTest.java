package com.example.cinemarate.Entity.OMDB_MIGRATOR;

import com.example.cinemarate.OMDB_MIGRATOR.OmdbConnector;
import com.example.cinemarate.OMDB_MIGRATOR.OmdbMovieDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OmdbConnectorTest {
    @Autowired
    private OmdbConnector connector;
    String movie_id = "tt0111161";
    @Test
    void testCreateLink(){
        String link = connector.createLink(movie_id);
        System.out.println(link);
        assert link.contains("51290d5d");
    }

    @Test
    void testFetchMovieById(){
        OmdbMovieDTO dto = connector.fetchMovieById(movie_id);
        System.out.println(dto);
    }}
