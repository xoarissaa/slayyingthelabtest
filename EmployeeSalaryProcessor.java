
/**
 * SWC Lab Test
 *
 * ANJ
 * 13 / 03 / 2024
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSalaryProcessor {

    public static void main(String[] args) {
        List<Employee> employees = readEmployeesFromFile("employeeSalaries.txt");
        calculateAnnualSalaries(employees);
        Employee topEmployee = findTopPerformingEmployee(employees);
        Employee leastExperiencedEmployee = findLeastExperiencedEmployee(employees);
        
        if (topEmployee != null && leastExperiencedEmployee != null) {
            System.out.printf("Top performing employee: %s, with an annual salary of %.2f\n", topEmployee.getName(), topEmployee.calculateAnnualSalary());
            System.out.printf("Employee with the least years of service: %s, with %d years of service\n", leastExperiencedEmployee.getName(), leastExperiencedEmployee.getYearsOfService());
            writeEmployeeDataToFile(employees, "employeeData.txt", topEmployee, leastExperiencedEmployee);
        } else {
            System.out.println("Could not determine the top performing or least experienced employee.");
        }
    }

    private static List<Employee> readEmployeesFromFile(String fileName) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");  // Adjust the delimiter based on your file format
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    double baseSalary = Double.parseDouble(parts[1].trim());
                    int yearsOfService = Integer.parseInt(parts[2].trim());
                    employees.add(new Employee(name, baseSalary, yearsOfService));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error in file format: " + e.getMessage());
        }
        return employees;
    }

    private static void calculateAnnualSalaries(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.printf("Annual salary of %s: %.2f\n", employee.getName(), employee.calculateAnnualSalary());
        }
    }

    private static Employee findTopPerformingEmployee(List<Employee> employees) {
        if (employees.isEmpty()) return null;
        Employee topEmployee = employees.get(0);
        for (Employee employee : employees) {
            if (employee.calculateAnnualSalary() > topEmployee.calculateAnnualSalary()) {
                topEmployee = employee;
            }
        }
        return topEmployee;
    }

    private static Employee findLeastExperiencedEmployee(List<Employee> employees) {
        if (employees.isEmpty()) return null;
        Employee leastExperiencedEmployee = employees.get(0);
        for (Employee employee : employees) {
            if (employee.getYearsOfService() < leastExperiencedEmployee.getYearsOfService()) {
                leastExperiencedEmployee = employee;
            }
        }
        return leastExperiencedEmployee;
    }

    private static void writeEmployeeDataToFile(List<Employee> employees, String fileName, Employee topEmployee, Employee leastExperiencedEmployee) {
        try (PrintWriter out = new PrintWriter(new File(fileName))) {
            for (Employee employee : employees) {
                out.printf("%s, %.2f, %d%n", employee.getName(), employee.getBaseSalary(), employee.getYearsOfService());
            }
            out.println();
            out.printf("Top Performing Employee: %s, Annual Salary: %.2f%n", topEmployee.getName(), topEmployee.calculateAnnualSalary());
            out.printf("Least Years of Service Employee: %s, Years of Service: %d%n", leastExperiencedEmployee.getName(), leastExperiencedEmployee.getYearsOfService());
        } catch (FileNotFoundException e) {
            System.err.println("Unable to write to file: " + e.getMessage());
        }
    }
}


