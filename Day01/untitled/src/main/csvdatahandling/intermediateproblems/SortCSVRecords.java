package csvdatahandling.intermediateproblems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SortCSVRecords {
    // created a method to sort the csv data
    public static ArrayList<ArrayList<String>> sortCsv(ArrayList<ArrayList<String>> csv) {
        ArrayList<ArrayList<String>> sorted = new ArrayList<>(); // to store the sorted data
        HashSet<ArrayList<String>> sortedPresent = new HashSet<>(); // to check if the row is already appended to the sorted data
        // performing selection sort to sort the 2d array
        for (int i = 0; i < csv.size(); i++) {
            ArrayList<String> least = new ArrayList<>();
            least.add(""); least.add(""); least.add(""); least.add(Integer.toString(Integer.MAX_VALUE));
            for (ArrayList<String> strings : csv) {
                if (!sortedPresent.contains(strings)) {
                    if (Integer.parseInt(least.get(3)) > Integer.parseInt(strings.get(3))) {
                        least = strings;
                    }
                }
            }
            sortedPresent.add(least);
            sorted.add(least); // adding record with least salary to the 2d array
        }
        return sorted;
    }

    // created a method to read and get the data from csv file
    public static ArrayList<ArrayList<String>> sortCsv(String file) throws CsvValidationException, IOException {
        CSVReader reader = new CSVReader(new FileReader(file));
        // 2d string arraylist to store the data
        ArrayList<ArrayList<String >> csvData = new ArrayList<>();
        reader.readNext(); // to avoid the header
        String[] nextLine; // to store read row
        while((nextLine = reader.readNext()) != null) { // reading the file
            ArrayList<String> line = new ArrayList<>(List.of(nextLine));
            csvData.add(line);
        }
        return sortCsv(csvData); // returning sorted csv data according to salary
    }

    // created a method to write to the file to be sorted
    public static void writeFile(ArrayList<ArrayList<String>> csv, String file) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(file, true)); // FileWriter wrapped in CSVWriter
        String [] line = {"ID","Name","Department","Salary"}; // adding header row
        writer.writeNext(line);
        System.out.println(Arrays.toString(line));
        // writing the file
        for (ArrayList<String> strings : csv) {
            for (int j = 0; j < strings.size(); j++) {
                line[j] = strings.get(j);
            }
            writer.writeNext(line); // appending row to the file
        }

        // closing the writer
        writer.close();
    }

    // created a method that sorts data of one file and stores the sorted data to another file
    public static ArrayList<ArrayList<String>> makeSorted(String unsorted, String sorted) throws CsvValidationException, IOException {
        ArrayList<ArrayList<String>> sortedCsv = sortCsv(unsorted);
        writeFile(sortedCsv, sorted);
        return sortedCsv;
    }

    // main method
    public static void main(String[] args) {
        String unsortedFile = "unsortedRecord.csv"; // file path of unsorted file
        String sortedFile = "sortedRecord.csv"; // file to store sorted data

        try {
            ArrayList<ArrayList<String>> sortedFileData =  makeSorted(unsortedFile, sortedFile);
            // displaying top 5 highest paid employees
            System.out.println(sortedFileData.getLast());
            System.out.println(sortedFileData.get(sortedFileData.size()-2));
            System.out.println(sortedFileData.get(sortedFileData.size()-3));
            System.out.println(sortedFileData.get(sortedFileData.size()-4));
            System.out.println(sortedFileData.get(sortedFileData.size()-5));
        }
        // handling the exception
        catch (Exception e) {
            System.out.println("Exception Occurred");
        }
    }
}