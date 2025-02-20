package csvdatahandling.advancedproblems;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmailAndPhone {
    public static void main(String[] args) {
        String email = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        String phone = "\\d{10}";
        Pattern pattern1 = Pattern.compile(email);
        Pattern pattern2 = Pattern.compile(phone);

        try(CSVReader csvReader = new CSVReader(new FileReader("emailAndPhone.csv"))){
            String[]line;

            while((line = csvReader.readNext()) != null){
                Matcher matcher1 = pattern1.matcher(line[4]);
                Matcher matcher2 = pattern2.matcher(line[5]);
                if(!matcher1.matches() || !matcher2.matches()){
                    System.out.println("ID : " + line[0] + " Name : " + line[1] + " Department : " + line[2] + " Salary : " + line[3] + " email : " + line[4] + " Phone : " + line[5]);
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
