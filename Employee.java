
/**
 Public class employee
 ANJ
 13/4/2024
 
 */
 
public class Employee {
    private String name;
    private double baseSalary;
    private int yearsOfService;

    public Employee(String name, double baseSalary, int yearsOfService) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.yearsOfService = yearsOfService;
    }

    public String getName() { return name; }
    public double getBaseSalary() { return baseSalary; }
    public int getYearsOfService() { return yearsOfService; }
    public double calculateAnnualSalary() { return baseSalary + (baseSalary * yearsOfService * 0.05); }
}
