package util.diffutil.utils;

import util.diffutil.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class DummyEmployeeDataUtils {

    public static long getCurrentTimestamp() {
        return MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    //compare 2 Employee objects
    public static List<Employee> getEmployeeListSortedByName() {
        final List<Employee> employeeList = getEmployeeList();

        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee a1, Employee a2) {
                return a1.getName().compareTo(a2.getName());
            }
        });
        return employeeList;
    }

    public static List<Employee> getEmployeeListSortedByRole() {
        final List<Employee> employeeList = getEmployeeList();

        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee a1, Employee a2) {
                return a2.getRole().compareTo(a1.getRole());
            }
        });
        return employeeList;
    }

    private static List<Employee> getEmployeeList() {
        final List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Employee 1", "Yumla", getCurrentTimestamp()));
        employees.add(new Employee(2, "Employee 2", "Zet", getCurrentTimestamp()));
        employees.add(new Employee(3, "Employee 3", "Zetta", getCurrentTimestamp()));
        employees.add(new Employee(4, "Employee 4", "Zumba", getCurrentTimestamp()));
        employees.add(new Employee(5, "Employee 5", "Yumba", getCurrentTimestamp()));
        employees.add(new Employee(6, "Employee 6", "Umla", getCurrentTimestamp()));
        employees.add(new Employee(7, "Employee 7", "Chuchundra", getCurrentTimestamp()));
        employees.add(new Employee(8, "Employee 8", "Yumla2", getCurrentTimestamp()));
        employees.add(new Employee(9, "Employee 9", "Zet2", getCurrentTimestamp()));
        employees.add(new Employee(10, "Employee 10", "Zetta2" , getCurrentTimestamp()));
        employees.add(new Employee(11, "Employee 11", "Zumba2", getCurrentTimestamp()));
        employees.add(new Employee(12, "Employee 12", "Yumba2", getCurrentTimestamp()));
        employees.add(new Employee(13, "Employee 13", "Amba", getCurrentTimestamp()));
        employees.add(new Employee(14, "Employee 14", "Vobla", getCurrentTimestamp()));
        employees.add(new Employee(15, "Employee 15", "Sintra", getCurrentTimestamp()));
        employees.add(new Employee(16, "Employee 16", "Mega", getCurrentTimestamp()));
        employees.add(new Employee(17, "Employee 17", "Ultra", getCurrentTimestamp()));
        employees.add(new Employee(18, "Employee 18", "Test", getCurrentTimestamp()));
        employees.add(new Employee(19, "Employee 19", "Hyper", getCurrentTimestamp()));
        employees.add(new Employee(20, "Employee 20", "Bear", getCurrentTimestamp()));
        return employees;
    }

    public static List<Employee> getUpdatedData(){
        final List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(21, "New Employee", "Cheetah", getCurrentTimestamp()));
        employees.add(new Employee(5, "Updated Employee 5", "Yumba 555", getCurrentTimestamp()));

        return employees;
    }

}
