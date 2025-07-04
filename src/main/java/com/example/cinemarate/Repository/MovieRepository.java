package com.example.cinemarate.Repository;

import com.example.cinemarate.Entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> getMovieEntityById(Long id);

    Optional<List<MovieEntity>> getMovieEntityByYear(int year);


}
