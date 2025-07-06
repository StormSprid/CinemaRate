package com.example.cinemarate.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class to test a {@code create()} method from {@code MovieEntity} class
 */

public class MovieEntityTest {
    @Test
    void createTest_WithValidData(){
        MovieEntity m = MovieEntity.create(
                "Caveat",
                "A lone drifter suffering from partial memory loss accepts a job to look after " +
                        "a psychologically troubled woman in an abandoned house on an isolated island.",
                2020,
                "https://www.imdb.com/title/tt7917178/mediaviewer/rm1536143361/?ref_=tt_ov_i"
        );
        assertNotNull(m);
        assertEquals("Caveat",m.getTitle());
        assertEquals("A lone drifter suffering from partial memory loss accepts a job to look after " +
                                        "a psychologically troubled woman in an abandoned house on an isolated island.",m.getDescription());
        assertEquals(2020,m.getYear());
        assertEquals("https://www.imdb.com/title/tt7917178/mediaviewer/rm1536143361/?ref_=tt_ov_i",m.getPosterUrl());
    }

    @Test
    void createTest_InvalidTitle(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->MovieEntity.create(
                "",
                "desc",
                2020,
                "url"
        ));
        assertTrue(ex.getMessage().contains("Title"));
    }
    @Test
    void createTest_InvalidDescription(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->
                MovieEntity.create(
                        "Title",
                        "",
                        2020,
                        "url"

                ));
        assertTrue(ex.getMessage().contains("Description"));
    }
    @Test
    void createTest_InvalidLowYear(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->
                MovieEntity.create(
                        "Title",
                        "Desc",
                        10,
                        "url"

                ));
        assertTrue(ex.getMessage().contains("Year"));
    }
    @Test
    void createTest_InvalidHighYear(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->
                MovieEntity.create(
                        "Title",
                        "Desc",
                        10000,
                        "url"

                ));
                assertTrue(ex.getMessage().contains("Year"));
    }
    @Test
    void createTest_InvalidPoster(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->
                MovieEntity.create(
                        "Title",
                        "Desc",
                        2020,
                        ""

                ));
        assertTrue(ex.getMessage().contains("PosterURL"));
    }
}
