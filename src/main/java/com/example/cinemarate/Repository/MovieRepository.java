package com.example.cinemarate.Repository;

import com.example.cinemarate.Entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
}
