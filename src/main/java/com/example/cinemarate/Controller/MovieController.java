package com.example.cinemarate.Controller;

import com.example.cinemarate.Converter.MovieConverter;
import com.example.cinemarate.DTO.MovieDTO;
import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Entity.ReviewEntity;
import com.example.cinemarate.OMDB_MIGRATOR.MigrationProcessor;
import com.example.cinemarate.Repository.MovieRepository;
import com.example.cinemarate.ServiceImpl.MovieServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/movie")
@RestController()
@RequiredArgsConstructor
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);


    private final MigrationProcessor migrationProcessor;

    private final MovieRepository movieRepository;

    private final MovieServiceImpl movieServiceImpl;

    @GetMapping("/{id}")
    public MovieDTO getMovie(@PathVariable String id) {
        logger.info("Get a request to a film with id {}", id);
        MovieEntity movie = movieRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return MovieConverter.toDto(movie);
    }
    @PostMapping("/migration")
    public ResponseEntity<String> migrateDb(){
        logger.info("Migration from CSV file has been started");
        migrationProcessor.executeMigration("imdb_film_data.csv");
        return  ResponseEntity.ok("Migration Started");
    }
    @GetMapping("/all")
    public List<MovieEntity> getAllMovies(){
        logger.info("Get a request to get all movies");
        return movieRepository.findAll();
    }
    @GetMapping("/all/page")
    public Page<MovieEntity> getAllMoviesPage(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "3") int size
                                              ){
        Pageable pageable = PageRequest.of(page,size);
        logger.info("Get a request to get all movies by pages {},{}",page,size);
        return movieServiceImpl.findAllPageable(pageable);
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
    @PatchMapping("/{id}/title")
    public MovieEntity updateMovieTitle(@PathVariable Long id, @RequestBody Map<String,String> body){
        String newTitle = body.get("title");
        return movieServiceImpl.updateMovieTitle(id,newTitle);

    }
    @PatchMapping("/{id}/description")
    public MovieEntity updateMovieDescription(@PathVariable Long id, @RequestBody Map<String,String> body){
        String newDescription = body.get("description");
        return movieServiceImpl.updateMovieTitle(id,newDescription);

    }
    @PatchMapping("/{id}/year")
    public MovieEntity updateMovieYear(@PathVariable Long id, @RequestBody Map<String,String> body){
        String newYear = body.get("year");
        return movieServiceImpl.updateMovieTitle(id,newYear);

    }
    @DeleteMapping()
    public void deleteMovie(@RequestParam Long id){
        logger.info("Get a request to delete a movie {}",movieRepository.getById(id).getTitle());
        movieServiceImpl.deleteMovie(id);
    }
    @GetMapping("/search")
    public List<MovieDTO> search(@RequestParam String title){
    logger.info("Get a request to find a movie with title => {}" ,title);
       List<MovieEntity> movieEntityList = movieServiceImpl.search(title);
       List<MovieDTO> movieDTOList = movieEntityList.stream()
               .map(MovieConverter::toDto)
               .toList();
       return movieDTOList;
    }
//
//    @GetMapping("/{id}/rating")
//    public ResponseEntity<Double> getMeanRating(@PathVariable Long id){
//        MovieEntity movie = movieRepository.findById(id).orElseThrow();
//        logger.info("Get a request to get a mean rating to movie: {}",movie);
//        Double rating =  movie.getMeanRating();
//        return ResponseEntity.ok(rating);
//    }


}
