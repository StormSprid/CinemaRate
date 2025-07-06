package com.example.cinemarate.Controller;

import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Entity.ReviewEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("/movie")
@RestController()
public class MovieController {
    @GetMapping("/{id}")
    public MovieEntity getMovie( @PathVariable String id){
        MovieEntity m = MovieEntity.create(
                "28 Days Later",
                "Zombie Film",
                2003,
                "https://m.media-amazon.com/images/M/MV5BM2I4NTI0ZGQtNGQ2ZC00ODIxLWI2N2QtMDBkNzI1NDhjYjE5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg"

        );
        m.setReviews( List.of(
                new ReviewEntity(1L,5,1),
                new ReviewEntity(2L,5,2),
                new ReviewEntity(2L,1,3)

        ));
        MovieEntity caveat = MovieEntity.create(
                "Caveat",
                "A lone drifter suffering from partial memory loss accepts a job to look after " +
                        "a psychologically troubled woman in an abandoned house on an isolated island.",
                2020,
                "https://resizing.flixster.com/H50hcdB92hfMfmqAB1OOavpVOBQ=/ems.cHJkLWVtcy1hc3NldHMvbW92aWVzL2U4ZjcyNTM0LWNkYjktNDhiOC1iYTJkLTI4OTJjMGYwZTBlOC5qcGc="
        );
        caveat.setReviews( List.of(
                new ReviewEntity(1L,5,1),
                new ReviewEntity(2L,5,2),
                new ReviewEntity(2L,4,3)

        ));
        return caveat;
    }
}
