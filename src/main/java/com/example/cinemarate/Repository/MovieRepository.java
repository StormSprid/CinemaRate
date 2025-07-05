package com.example.cinemarate.Repository;

import com.example.cinemarate.Entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Spring Data JPA repository for MovieEntity.
 * Includes standard CRUD operations, pagination, and query derivation.
 */
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findByYear(int year);


}
