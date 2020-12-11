package fileservice;

import entities.Employee;
import userInterface.EmployeeConsole;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IOWriter {
    private static BufferedWriter bufferedWriter;

    public IOWriter() {
    }

    public static void fileWrite(String path){
        File file = new File(path);
        List<Employee> arrayList = IOReader.arrayList;
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                bufferedWriter = new BufferedWriter(new FileWriter(file));

                for (Employee employee : arrayList) {
                    bufferedWriter.append(employee.toString());
                    bufferedWriter.append("\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

}
