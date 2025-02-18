package csvdatahandling.basicproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CountRows {
    // created a method to read and get the data from csv file
    public static int readCsvFile(String file) throws CsvValidationException, IOException {
        CSVReader reader = new CSVReader(new FileReader(file));
        // counter variable to count the number of rows
        int count=0;

        while((reader.readNext()) != null) { // iteration through the file
            count++;
        }

        return count-1; // because we need to exclude the header
    }

    // main method
    public static void main(String[] args) {
        String file = "readcsv.csv"; // file path

        try {
            int numberOfRecords = readCsvFile(file);
            System.out.println("Number of records: " + numberOfRecords);
        }
        // handling the exception
        catch (Exception e) {
            System.out.println("Exception Occurred");
        }
    }
}
