package csvdatahandling.advancedproblems;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.HashSet;

public class DetectDuplicate {
    public static void main(String[] args) {
        // using hashSet to find duplicates
        HashSet<String>id = new HashSet<>();

        try(CSVReader csvReader = new CSVReader(new FileReader("sortedRecord.csv"))){
            String[]line;
            while((line = csvReader.readNext()) != null){
                // if id exist in hashset then print it
                if(id.contains(line[0])){
                    System.out.println("ID : " + line[0] + ", Name : " + line[1] + ", Department : " + line[2] + ", Marks " + line[3]);
                }
                id.add(line[0]);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
