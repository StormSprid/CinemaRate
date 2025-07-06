package com.example.cinemarate.Entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class to test a {@code create()} method from {@code MovieEntity} class
 */

public class MovieEntityTest {
    private static final String VALID_TITLE = "Title";
    private static final String VALID_DESCRIPTION = "Description";
    private static final String VALID_URL = "url";
    @Test
    @DisplayName("Should create MovieEntity when data is valid")
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
                VALID_DESCRIPTION,
                2020,
                "url"
        ));
        assertTrue(ex.getMessage().contains("Title"));
    }
    @Test
    void createTest_InvalidDescription(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->
                MovieEntity.create(
                        VALID_TITLE,
                        "",
                        2020,
                        VALID_URL

                ));
        assertTrue(ex.getMessage().contains("Description"));
    }
    @Test
    void createTest_InvalidLowYear(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->
                MovieEntity.create(
                        VALID_TITLE,
                        VALID_DESCRIPTION,
                        10,
                        VALID_URL

                ));
        assertTrue(ex.getMessage().contains("Year"));
    }
    @Test
    void createTest_InvalidHighYear(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->
                MovieEntity.create(
                        VALID_TITLE,
                        VALID_DESCRIPTION,
                        10000,
                        VALID_URL

                ));
                assertTrue(ex.getMessage().contains("Year"));
    }
    @Test
    void createTest_InvalidPoster(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->
                MovieEntity.create(
                        VALID_TITLE,
                        "Desc",
                        2020,
                        ""

                ));
        assertTrue(ex.getMessage().contains("PosterURL"));
    }
}
