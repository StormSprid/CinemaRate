package com.example.cinemarate.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.valves.rewrite.RewriteCond;

import java.util.List;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    int year;
    String posterUrl;
    //TODO create a new table with review per film
    List<ReviewEntity> reviews;
    @Transient
    double meanRating = -1;

    public MovieEntity(String title, String description, int year, List<ReviewEntity> reviews) {
        this.title = title;
        this.description = description;
        this.year = year;
        this.reviews = reviews;

    }


    @Override
    public String toString() {
        return "MovieEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year + '\'' +
                ", rating=" + getMeanRating() +
                '}';
    }

    /** Calculate a mean rating to a film.
     * {@code meanRating} variable is a cache that that help us to avoid extra calculations.
     * If there are no reviews,return 0
     * **/
    private double getMeanRating() {
        if (reviews.isEmpty()){
            return 0;
        }
        if (meanRating == -1) {
            int sum = 0;
            for (ReviewEntity r : reviews) {
                sum += r.rating;
            }
            meanRating =  (double) Math.round((double) sum / reviews.size() * 100) / 100;
        }
            return meanRating;
        }

    private void resetMeanRating(){
        meanRating = -1;
    }
    }


