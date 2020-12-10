package fileservice;

import controller.EmployeeManager;
import entities.Employee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class IOWriter {

    public IOWriter() {
    }

    public void fileWrite(String path){
        File file = new File(path);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            EmployeeManager employeeManager = new EmployeeManager();
            List<Employee> arrayList = employeeManager.getListOfEmployee();
            for (Employee employee: arrayList){
                bufferedWriter.append(employee.toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
