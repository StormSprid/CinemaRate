package com.example.cinemarate.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    //TODO create a new table with review per film
    List<ReviewEntity> reviews;


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
                ", rating=" + calculateMeanRating() +
                '}';
    }

    /** Calculate a mean rating to a film **/
    private double calculateMeanRating(){
        int sum  = 0;
        for (ReviewEntity r :reviews){
            sum += r.rating;
        }
        return (double) Math.round((double) sum / reviews.size() * 100) /100;
    }

}
