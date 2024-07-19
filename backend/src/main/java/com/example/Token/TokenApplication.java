package com.example.Token;

import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.Token")
public class TokenApplication {

    public static void main(String[] args) {
    	String csvFilePath = "path/to/your/csvfile.csv";
        String columnName = "yourColumnName";

        try {
            FileReader reader = new FileReader(csvFilePath);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);

            for (CSVRecord record : records) {
                String columnValue = record.get(columnName);
                System.out.println(columnValue);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(TokenApplication.class, args);
    }
}
