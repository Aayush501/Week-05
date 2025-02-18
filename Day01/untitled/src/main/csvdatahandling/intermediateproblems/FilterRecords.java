package csvdatahandling.intermediateproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilterRecords {
    // created a method to read and get the data from csv file
    public static ArrayList<ArrayList<String >> readCsvFile(String file) throws CsvValidationException, IOException {
        CSVReader reader = new CSVReader(new FileReader(file));
        // 2d string arraylist to store the data
        ArrayList<ArrayList<String >> csvData = new ArrayList<>();
        reader.readNext(); // to skip the header so there is no NumberFormatException
        String[] nextLine;
        while((nextLine = reader.readNext()) != null) { // reading the file
            ArrayList<String> line = new ArrayList<>(List.of(nextLine));
            csvData.add(line);
        }
        return csvData; // returning csv data
    }

    public static void main(String[] args) {
        String file = "studentsScore.csv"; // file path

        try { // using openCSV library to read the CSV file
            ArrayList<ArrayList<String >> csvData; // 2D string arrayList to store the file data
            csvData = readCsvFile(file);
            for (ArrayList<String> strings : csvData) {
                int studentMarks = Integer.parseInt(strings.get(3));
                if(studentMarks > 80) {
                    System.out.println(strings);
                }
            }
        }
        // handling the exception
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exception Occurred");
        }
    }
}
