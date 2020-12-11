 package employeesManagement;

import fileservice.IOReader;
import userInterface.EmployeeConsole;

public class EmployeeManagement {

    public static void main(String[] args) {
        IOReader.fileRead("data.txt");
        EmployeeConsole ec = new EmployeeConsole();
        ec.start();
    }
}
