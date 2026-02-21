package org.example;

import POJO.Employee;
import repo.EmployeeData;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private static int id = 0;
    Employee employee = new Employee();
    EmployeeData employeeData = new EmployeeData();

    public String addEmployee(String firstName, String lastName, String email, String address)
    {
            if (firstName != null && !firstName.isEmpty() &&
                    lastName != null && !lastName.isEmpty() &&
                    email != null && !email.isEmpty() &&
                    address != null && !address.isEmpty()) {

                    employee.setId(id++);
                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setEmail(email);
                    employee.setAddress(address);
                    Date doj = Date.valueOf(LocalDate.now());
                    //        System.out.println(doj);
                    employee.setDOJ(doj);

                    if (!employeeData.verifyEmployeeWithFullName(firstName, lastName)) {
                        return "Employee already exists";
                    }else {
                         employeeData.setEmployeesMap(id, firstName, lastName, email, address, doj);  //add details of employee in Map
                    }
            }
        else {
            return "Invalid Input";
        }

        return "Employee added successfully";
    }

    public String getEmployee(int id)
    {
        //call map function to match for exiting employee
        return employeeData.getEmployee(id).toString();
    }

    public List<String> listAllEmployees()
    {
        List<Employee> employees = employeeData.listAllEmployees();  // get all details of employee from Map
        List<String> employeeList = new ArrayList<>();

        for(Employee employee: employees)
        {
            employeeList.add(employee.toString());
        }

        return employeeList;
    }

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        employeeService.addEmployee("Priya", "Chomu", "priya.chomu@gmail.com", "No address");
        employeeService.addEmployee("Nagesh", "Chomu", "priya.chomu@gmail.com", "No address");
        employeeService.addEmployee("Raveena", "Chomu", "priya.chomu@gmail.com", "No address");
        employeeService.addEmployee("Reva", "Chomu", "priya.chomu@gmail.com", "No address");
        employeeService.addEmployee("Priya", "Chomu", "priya.chomu@gmail.com", "No address");
        System.out.println(employeeService.listAllEmployees());
        System.out.println(employeeService.getEmployee(1));
    }

}
