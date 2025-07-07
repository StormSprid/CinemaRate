package com.example.cinemarate.OMDB_MIGRATOR;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OmdbMovieDTO {
    @JsonProperty("Title")
    String title;
    @JsonProperty("Plot")
    String description;
    @JsonProperty("Poster")
    String posterUrl;
    @JsonProperty("Year")
    String year;

    @Override
    public String toString() {
        return "OmdbMovieDTO{" +
                "title='" + title + '\'' +
                ", plot='" + description + '\'' +
                ", poster='" + posterUrl + '\'' +
                ", released='" + year + '\'' +
                '}';
    }
}
