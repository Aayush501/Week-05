package csvdatahandling.basicproblems;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToCSVFile {
    // created a method to write the file
    public static void writeToFile(String file, String [] nextData) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(file, true)); // FileWriter wrapped in CSVWriter
        // writing the file
        writer.writeNext(nextData);
        // closing the writer
        writer.close();
    }

    public static void main(String[] args) {
        String file = "writeCsv.csv"; // file path

        // defining the data to be written in the file
        String[] header = {"ID", "Name", "Department", "Salary"};
        String[] emp1 = {"104", "Name1", "Finance", "62000"};
        String[] emp2 = {"105", "Name2", "Sales", "58000"};
        String[] emp3 = {"104", "Name3", "HR", "62000"};
        String[] emp4 = {"105", "Name4", "IT", "58000"};
        String[] emp5 = {"104", "Name5", "Finance", "62000"};

        // writing to file inside try-catch block to handle exceptions
        try {
            writeToFile(file, header);
            writeToFile(file, emp1);
            writeToFile(file, emp2);
            writeToFile(file, emp3);
            writeToFile(file, emp4);
            writeToFile(file, emp5);
            System.out.println("File Writing Successful.");
        } catch (Exception e) {
            System.out.println("Exception Occurred!!!");
        }
    }
}
