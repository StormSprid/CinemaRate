package com.example.cinemarate.OMDB_MIGRATOR;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {


    public static List<String> getListofFilmTitles(String path){

        List<String> moviesId = new ArrayList<>();


        try(Reader in = new FileReader(path)){
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);

            for (CSVRecord record:records){
                String title = record.get("Title");
                String id = record.get("Const");
                //System.out.println(title + "|" + id);
                moviesId.add(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return moviesId;
    }




}
