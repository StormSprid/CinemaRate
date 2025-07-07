package com.example.cinemarate.Entity.OMDB_MIGRATOR;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvParser {


    public static List<String> getListofFilmTitles(){
        System.out.println("Enter a path of a CSV file:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
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

    public static void main(String[] args) {
        CsvParser.getListofFilmTitles();
    }


}
