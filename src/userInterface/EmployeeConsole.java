package userInterface;

import controller.EmployeeManager;
import entities.Employee;
import fileservice.IOReader;
import fileservice.IOWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeConsole {

    public static EmployeeManager em;
    private Scanner sc;

    public EmployeeConsole() {
        this.sc = new Scanner(System.in);
        this.em = new EmployeeManager();
    }

    private int menu() {
        System.out.println("----------Menu---------");
        System.out.println("1: ADD EMPLOYEE");
        System.out.println("2: VIEW EMPLOYEES LIST");
        System.out.println("3: DELETE EMPLOYEE");
        System.out.println("4: EDIT EMPLOYEES VIA ID");
        System.out.println("5: SAVE INTO FILE");
        System.out.println("0: EXIT !!");
        System.out.println("-------------------------------------");
        int choice = readInt(0, 5);
        return choice;
    }

    public void start() {
        while (true) {
            int choice = menu();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    addEmployee();
                    break;
                case 2:
                    showEmployee(IOReader.fileRead("data.txt"));
                    break;
                case 3:
                    removeEmployee();
                    break;
                case 4:
                    editEmployee();
                    break;
                case 5:
                    IOWriter.fileWrite("data.txt");
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void editEmployee() {
        System.out.println("Enter ID of employee");
        int id = Integer.parseInt(sc.nextLine());
        int n = 0;
        for (int i = 0; i < em.getListOfEmployee().size(); i++) {
            if (id == em.getListOfEmployee().get(i).getId()) {
                System.out.println("Change ID to: ");
                int newId = Integer.parseInt(sc.nextLine());
                em.getListOfEmployee().get(i).setId(newId);
                System.out.println("Change name");
                String newName = sc.nextLine();
                em.getListOfEmployee().get(i).setName(newName);
                System.out.println("Change salary");
                float newSalary = Float.parseFloat(sc.nextLine());
                em.getListOfEmployee().get(i).setSalary(newSalary);
            }
        }
    }

    private void removeEmployee() {
        System.out.println("Enter ID of employee");
        int id = readInt(0, Integer.MAX_VALUE);
        boolean result = this.em.removeEmployee(id);
        if (result) {
            System.out.println("Employee fired!");
        } else {
            System.out.println("Employee not found!");
        }
    }


    private void showEmployee(ArrayList<Employee> employees) {
        System.out.println("-------Employees List-------");
        System.out.println("ID      |    NAME   |     SALARY    ");
        for (int i = 0; i < employees.size(); i++) {
//            Employee e = this.em.getEmployee(i);
            System.out.println(employees.get(i).getId() + "\t\t" + employees.get(i).getName() + "\t\t" + employees.get(i).getSalary());
        }
    }

    private float readFloat(int min, float max) {
        float salary;
        while (true) {
            try {
                salary = Float.parseFloat(sc.nextLine());
                if (salary >= min && salary <= max) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid value");
            }
        }
        return salary;
    }

    private int readInt(int min, int max) {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return choice;
    }

    private void addEmployee() {
        String path = "data.txt";
        FileWriter fileWriter = null;
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true);
            System.out.println("Enter Employees ID: ");
            String id = sc.nextLine();
            System.out.println("Enter Employees Name: ");
            String name = sc.nextLine();
            System.out.println("Enter Employees Salary: ");
            String salary = sc.nextLine();
            fileWriter.append(id);
            fileWriter.append( ",");
            fileWriter.append(name);
            fileWriter.append( ",");
            fileWriter.append(salary);
            fileWriter.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
