package test.java;


import java.io.FileReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVReader;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VacancyCsvReader {
    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String parsedDate = dateTimeFormatter.format(LocalDate.now());

        String csvFile = "src/test/java/test/telegram/filtered_vacancies_" + parsedDate + ".csv";
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile))
                .withCSVParser(new com.opencsv.CSVParserBuilder()
                        .withSeparator('|')
                        .withQuoteChar('"')
                        .build())
                .build()) {

            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                String channel = row[0];
                String text = row[1];

                System.out.println("Channel: " + channel);
                System.out.println("Text: " + text);
                System.out.println("-----");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


//            // Print the vacancies
//        for (String vacancy : vacancies) {
//            System.out.println("VACANCY:");
//            System.out.println(vacancy);
//            System.out.println("-----");
//
//            // Here should be the AI model
//        }
    }
}
