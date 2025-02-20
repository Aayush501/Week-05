package csvdatahandling.advancedproblems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;

public class MergeTwoFiles {
    public static void main(String[] args) {
        try(CSVReader csvReader = new CSVReader(new FileReader("Student1.csv"));
            CSVReader csvReader1 = new CSVReader(new FileReader("Student2.csv"));
            CSVWriter csvWriter = new CSVWriter(new FileWriter("mergeStudent.csv"))
        ){
            String[]student1;
            String[]student2;
            String[] mergeStudent = new String[5];
            while((student1 = csvReader.readNext()) != null && (student2 = csvReader1.readNext()) != null){
                // merging two files in one
                mergeStudent[0] = student1[0];
                mergeStudent[1] = student1[1];
                mergeStudent[2] = student1[2];
                mergeStudent[3] = student2[1];
                mergeStudent[4] = student2[2];

                // writing merged array in merged file
                csvWriter.writeNext(mergeStudent);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
