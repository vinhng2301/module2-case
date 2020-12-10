package controller;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private List<Employee> listOfEmployee;

    public EmployeeManager(){
        this.listOfEmployee = new ArrayList<>();
    }

    public List<Employee> getListOfEmployee() {
        return listOfEmployee;
    }

    public int addEmployee(Employee e){
        this.listOfEmployee.add(e);
        return this.listOfEmployee.size();
    }

    public int count(){
        return this.listOfEmployee.size();
    }

    public Employee getEmployee(int index){
        if(index < 0 || index >= count()){
            return null;
        }
        return this.listOfEmployee.get(index);
    }

    public boolean removeEmployee(int id){
        int index = -1;
        for (int i = 0, n = count(); i < n; i++) {
            if(this.listOfEmployee.get(i).getId() == id){
                index = i;
                break;
            }
        }
        if(index != -1) {
            this.listOfEmployee.remove(index);
            return true;
        }
        return false;
    }
}
