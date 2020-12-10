package fileservice;

import entities.Employee;
import userInterface.EmployeeConsole;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IOWriter {
    private BufferedWriter bufferedWriter;

    public IOWriter() {
    }

    public void fileWrite(String path){
        File file = new File(path);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            List<Employee> arrayList = EmployeeConsole.em.getListOfEmployee();

            for(Employee employee:arrayList){
                bufferedWriter.append(employee.toString());
                bufferedWriter.append("\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
           if(bufferedWriter != null){
               try {
                   bufferedWriter.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
    }


}
