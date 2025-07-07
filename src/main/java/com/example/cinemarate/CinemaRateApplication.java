package com.example.cinemarate;

import com.example.cinemarate.Entity.MovieEntity;
import com.example.cinemarate.Entity.ReviewEntity;
import com.example.cinemarate.OMDB_MIGRATOR.OmdbConnector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class CinemaRateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaRateApplication.class, args);

    }

}
