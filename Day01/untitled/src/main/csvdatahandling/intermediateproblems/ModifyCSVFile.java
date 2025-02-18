package csvdatahandling.intermediateproblems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModifyCSVFile {
    // created a method to write a csv file
    public static void writeFile(ArrayList<ArrayList<String >> csvData, String file) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(file, true));
        for (ArrayList<String> strings : csvData) {
            String[] line = new String[strings.size()]; // string array to be added in the file
            for (int i = 0; i < strings.size(); i++) {
                line[i] = strings.get(i);
            }
            writer.writeNext(line); // appending string array to the file
        }
        writer.close();
    }

    // created a method to read and get the data from csv file
    public static ArrayList<ArrayList<String>> updateRecord(String file) throws CsvValidationException, IOException
    {
        CSVReader reader = new CSVReader(new FileReader(file));
        // 2d string arraylist to store the data
        ArrayList<ArrayList<String >> csvData = new ArrayList<>();
        String[] nextLine;
        while((nextLine = reader.readNext()) != null) { // reading the file
            ArrayList<String> line = new ArrayList<>(List.of(nextLine));
            if (line.get(2).equals("IT")){
                ArrayList<String> itLine = new ArrayList<>();
                itLine.add(line.getFirst());
                itLine.add(line.get(1));
                itLine.add(line.get(2));
                int salary = Integer.parseInt(line.get(3));
                salary += salary/10; // increasing the salary by 10%
                itLine.add(Integer.toString(salary));
                csvData.add(itLine);
                continue;
            }
            csvData.add(line);
        }
        return csvData; // returning csv data
    }

    public static void main(String[] args) {
        String file1 = " employees.csv" ; // file to be read
        String file2 = "modifyCsv.csv"; // file to be written

        try { // using openCSV library to read the CSV file
            ArrayList<ArrayList<String >> csvData; // 2D string arrayList to store the file data
            csvData = updateRecord(file1);
            writeFile(csvData, file2); // writing the csv file
            for (ArrayList<String> strings : csvData) {
                System.out.println(strings);
            }
        }
        // handling the exception
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exception Occurred");
        }
    }
}
