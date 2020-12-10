package userInterface;

import controller.EmployeeManager;
import entities.Employee;
import fileservice.IOWriter;

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
                    showEmployee();
                    break;
                case 3:
                    removeEmployee();
                    break;
                case 4:
                    editEmployee();
                    break;
                case 5:
                    IOWriter ioWriter = new IOWriter();
                    ioWriter.fileWrite("data.txt");
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


    private void showEmployee() {
        System.out.println("-------Employees List-------");
        System.out.println("ID      |    NAME   |     SALARY    ");
        for (int i = 0; i < this.em.count(); i++) {
            Employee e = this.em.getEmployee(i);
            System.out.println(e.getId() + "\t\t" + e.getName() + "\t\t" + e.getSalary());
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
        System.out.println("Enter Employees ID: ");
        int id = readInt(0, Integer.MAX_VALUE);
        System.out.println("Enter Employees Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Employees Salary: ");
        float salary = readFloat(0, Float.MAX_VALUE);
        Employee e = new Employee(id, name, salary);
        this.em.addEmployee(e);
    }
}
