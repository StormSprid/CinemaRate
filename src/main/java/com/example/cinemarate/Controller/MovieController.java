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
        MovieEntity m = new MovieEntity();
        m.setTitle("28 Days Later");
        m.setDescription("Zombie Film");
        m.setYear(2004);
        m.setPosterUrl("https://m.media-amazon.com/images/M/MV5BM2I4NTI0ZGQtNGQ2ZC00ODIxLWI2N2QtMDBkNzI1NDhjYjE5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");
        m.setReviews( List.of(
                new ReviewEntity(1L,5,1),
                new ReviewEntity(2L,5,2),
                new ReviewEntity(2L,1,3)

        ));
        return m;
    }
}
