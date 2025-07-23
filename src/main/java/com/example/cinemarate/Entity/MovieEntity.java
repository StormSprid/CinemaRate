package com.example.cinemarate.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //TODO find out for what it's stands for
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
     private Long  id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private int  year;
    private String posterUrl;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @Setter
    private List<ReviewEntity> reviews = new ArrayList<>();
    @Transient
    private double meanRating = -1;


    public static MovieEntity create(String title,String description,int year,String posterUrl){

        MovieEntity m = new MovieEntity();
        m.setTitle(title);
        m.setDescription(description);
        m.setYear(year);
        m.setPosterUrl(posterUrl);

     return m;
    }



    public void setTitle(String title) {
        if(title.isBlank()){
            throw  new IllegalArgumentException(String.format("Title %s is not valid.Error to create a Movie.",title));
        }
        this.title = title;

    }

    public void setDescription(String description) {
        if(description.isBlank()){
            throw  new IllegalArgumentException(String.format("Description %s is not valid.Error to create a Movie.",description));
        }

            this.description = description;

    }

    public void setYear(int year) {
        if (year < 1900 || year > 2030){
            throw new IllegalArgumentException(String.format("Year %s is not valid.Error to create a Movie.",year));
        }
        this.year = year;
    }

    public void setPosterUrl(String posterUrl) {
        if(posterUrl.isBlank()){
            throw  new IllegalArgumentException(String.format("PosterURL %s is not valid.Error to create a Movie.",posterUrl));
        }
        this.posterUrl = posterUrl;
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
    public  double getMeanRating() {
        if (reviews.isEmpty()){
            return 0;
        }
        if (meanRating == -1) {
            int sum = 0;
            for (ReviewEntity r : reviews) {
                sum += r.getRating();
            }
            meanRating =  (double) Math.round((double) sum / reviews.size() * 100) / 100;
        }
            return meanRating;
        }

    public void resetMeanRating(){
        meanRating = -1;
    }


}


