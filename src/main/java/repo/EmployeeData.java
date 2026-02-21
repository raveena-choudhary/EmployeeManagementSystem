package repo;

import POJO.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeData {

    public HashMap<Integer, Employee> employeesMap = new HashMap<>();

    public void setEmployeesMap(int id, String firstName, String lastName, String email, String address, Date DOJ)
    {
        if(!employeesMap.containsKey(id))
        {
            employeesMap.put(id, new Employee(id,firstName,lastName,email,address,DOJ));
        }
    }

    public Employee getEmployee(int id)
    {
        return employeesMap.get(id);
    }

    public List<Employee> listAllEmployees()
    {
        return new ArrayList<>(employeesMap.values());
    }

    public boolean verifyEmployeeWithFullName(String firstName, String lastName)
    {
        List<Employee> employeeList = listAllEmployees();
        for(Employee employee : employeeList)
        {
            if(employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName))
            {
                return true;
            }
        }

        return false;
    }

}
