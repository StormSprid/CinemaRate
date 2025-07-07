package com.example.cinemarate.OMDB_MIGRATOR;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OmdbConnector {
    //http://www.omdbapi.com/?apikey=[yourkey]&i=http://www.omdbapi.com/?apikey=51290d5d&i=tt0111161
    @Value("${imdb.api}")
    private  String API_KEY;
    private final String PATH = "http://www.omdbapi.com/?apikey=";

    String movie_id = "tt0111161";


    public String createLink(String id){
        return PATH + API_KEY + "&i=" + id;
    }


}
