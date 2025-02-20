package csvdatahandling.advancedproblems;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CreateListOfObject {
    // student class
    static class Student{
        String id;
        String name;
        String age;
        int marks;

        Student(String id, String name, String department, int marks){
            this.id = id;
            this.name = name;
            this.age = department;
            this.marks = marks;
        }

    }

    public static void main(String[] args) {
        File file = new File("studentsScore.csv");

        // list to store student data
        List<Student> listOfStudent = new ArrayList<>();

        try(CSVReader csvReader = new CSVReader(new FileReader(file))){
            csvReader.readNext(); // to avoid header
            String[]line;
            while((line = csvReader.readNext()) != null){
                // parsing data
                String id = line[0];
                String name = line[1];
                String age = line[2];
                int marks = Integer.parseInt(line[3]);
                Student student = new Student(id, name, age, marks);

                listOfStudent.add(student);
            }

            for (Student student : listOfStudent) {
                System.out.println("ID : " + student.id + ", Name : " + student.name + ", Department : " + student.age + ", Marks: " + student.marks);
            }
        }
        catch (Exception e) {
            System.out.println("Exception Occurred!!!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
