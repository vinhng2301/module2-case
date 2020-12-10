package fileservice;

import entities.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class IOReader {
    private static ArrayList<Employee> fileRead(String path){
        File file = new File(path);
        ArrayList<Employee> arrayList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                String[] split = line.split(",");
                int id = Integer.parseInt(split[0]);
                String name = split[1];
                float salary = Float.parseFloat(split[2]);
                Employee employee = new Employee(id,name,salary);
                arrayList.add(employee);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
//      Method belows is for testing the fileReader
//    public static void main(String[] args) {
//        System.out.println(fileRead("data.txt").toString());
//    }
}
