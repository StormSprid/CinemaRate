package com.example.cinemarate.OMDB_MIGRATOR;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    /**
     *
     *Return list of movie ids from IMDB Database
     */
    public static List<String> getListOfFilmTitles(String path){

        List<String> moviesId = new ArrayList<>();


        try(Reader in = new FileReader(path)){
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);

            for (CSVRecord record:records){
                String id = record.get("Const");
                moviesId.add(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moviesId;
    }




}
