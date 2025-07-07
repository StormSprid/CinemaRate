package com.example.cinemarate.OMDB_MIGRATOR;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OmdbConnector {
    //http://www.omdbapi.com/?apikey=[yourkey]&i=http://www.omdbapi.com/?apikey=51290d5d&i=tt0111161
    @Value("${imdb.api}")
    private  String API_KEY;
    private final String PATH = "http://www.omdbapi.com/?apikey=";
    private final RestTemplate restTemplate;

    String movie_id = "tt0111161";

    public OmdbConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String createLink(String id){
        return PATH + API_KEY + "&i=" + id;
    }

    public void testLinks(){
        List<String> ms = CsvParser.getListOfFilmTitles("imdb_film_data.csv");
        for(String m : ms){
            System.out.println(createLink(m));
        }
    }
    public OmdbMovieDTO fetchMovieById(String id){
        String url = createLink(id);
        System.out.println(url);
        return restTemplate.getForObject(url,OmdbMovieDTO.class);
    }


}
