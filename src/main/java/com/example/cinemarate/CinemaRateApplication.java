package com.example.cinemarate;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;


@SpringBootApplication
@Async
public class CinemaRateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaRateApplication.class, args);

    }

}
