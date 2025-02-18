package csvdatahandling.basicproblems;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ReadCSVFileTest {
    // created a method to test the readCsvFile method
    @Test
    public void readCSVFile() throws CsvValidationException, IOException {
        // paths of test files
        String test1 = "csvTest1.csv", test2 = "csvTest2.csv", test3 = "csvTest3.csv";

        // defining the expected result arrays
        ArrayList<ArrayList<String>> result1 = new ArrayList<>();
        result1.add(new ArrayList<>(Arrays.asList("Aayush","Kumar","Shrivastav","Java","Programming","Learner")));
        ArrayList<ArrayList<String>> result2 = new ArrayList<>();
        result2.add(new ArrayList<>(Arrays.asList("technocrats","institute","of","technology","excellence","bhopal")));
        ArrayList<ArrayList<String>> result3 = new ArrayList<>();
        result3.add(new ArrayList<>(Arrays.asList("bridge","labz","teaching","us","java")));

        // performing tests
        assertEquals(result1, ReadCSVFile.readCsvFile(test1));
        assertEquals(result2, ReadCSVFile.readCsvFile(test2));
        assertEquals(result3, ReadCSVFile.readCsvFile(test3));
    }
}