package csvdatahandling.advancedproblems;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JSONToCSV {
    public static ArrayList<ArrayList<String>> readJson(String file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(new File(file));

        ArrayList<ArrayList<String>> jsonData = new ArrayList<>();
        for (JsonNode n : node) {
            ArrayList<String> row = new ArrayList<>();
            row.add(n.get("ID").asText());
            row.add(n.get("Name").asText());
            row.add(n.get("Age").asText());
            row.add(n.get("Marks").asText());
            jsonData.add(row);
        }

        return jsonData;
    }

    public static ArrayList<ArrayList<String>> readCsv(String file) throws CsvValidationException, IOException {
        CSVReader reader = new CSVReader(new FileReader(file));
        // 2d string arraylist to store the data
        ArrayList<ArrayList<String >> csvData = new ArrayList<>();
        String[] nextLine; // to store read row
        while((nextLine = reader.readNext()) != null) { // reading the file
            ArrayList<String> line = new ArrayList<>(List.of(nextLine));
            csvData.add(line);
        }
        return csvData; // returning csv data
    }

    public static void jsonToCSV(ArrayList<ArrayList<String>> jsonData, String file) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(file));

        for (ArrayList<String > strings : jsonData) {
            String [] row = new String[strings.size()];
            for (int i = 0; i < strings.size(); i++) {
                row [i] = strings.get(i);
            }
            writer.writeNext(row);
            System.out.println(Arrays.toString(row));
        }
        writer.close();
    }

    public static void csvToJson(ArrayList<ArrayList<String>> csvData, String file) throws IOException {
        // using List of Maps
        List<Map<String, Object>> students = new ArrayList<>();

        for (ArrayList<String> strings : csvData) {
            students.add(Map.of("ID", strings.getFirst(), "Name", strings.get(1), "Age", strings.get(2), "Marks", strings.get(3)));
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File(file), students);
        System.out.println("JSON File Writing Successful.");
    }

    public static void main(String[] args) {
        String jsonFile = "readJSON.json";
        String csvFile = "csvJson.csv";
        try {
            ArrayList<ArrayList<String>> jsonData = readJson(jsonFile);
            jsonToCSV(jsonData, csvFile);
            ArrayList<ArrayList<String>> csvData = readCsv(csvFile);
            csvToJson(csvData, jsonFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exception Occurred!!!");
        }
    }
}

