package org.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import repo.EmployeeData;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTests {

    @Mock
    EmployeeData employeeData;

    @InjectMocks
    EmployeeService employeeService = new EmployeeService();  //class under test

    @Test
    public void testAddEmployee()
    {
        when(employeeData.verifyEmployeeWithFullName(anyString(),anyString())).thenReturn(true);

        //act
        String status =  employeeService.addEmployee("P","Choudhary","abc@gmail.com","Canada");

        assertEquals("Employee added successfully", status);

        //aasert: verifyFullName called
        verify(employeeData, times(1)).verifyEmployeeWithFullName("P","Choudhary");

        //assert: Data added to map with correct args
        verify(employeeData,times(1)).setEmployeesMap(anyInt(),
                eq("P"),
                eq("Choudhary"),
                eq("abc@gmail.com"),
                eq("Canada"),
                any(Date.class)
                );
    }

    @Test
    public void testShouldNotAddExistingEmployee()
    {
        //arrange: whatever dependencies your method has first
        when(employeeData.verifyEmployeeWithFullName(anyString(),anyString())).thenReturn(false);

        //act
        String status = employeeService.addEmployee("P","Choudhary","abc@gmail.com","Canada");

        //aasert: verifyFullName called: verify works only for mockObjects
        verify(employeeData, times(1)).verifyEmployeeWithFullName("P","Choudhary");

        assertEquals("Employee already exists", status);

        //assert: Method should not be called now
        verify(employeeData,never()).setEmployeesMap(anyInt(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                any(Date.class)
        );

    }

    @Test
    public void testAddEmployeeWithNullValues()
    {
        String status = employeeService.addEmployee(null, null, null, null);
        assertEquals("Invalid Input", status);

        verify(employeeData,never()).verifyEmployeeWithFullName(anyString(),anyString());

        verify(employeeData, never()).setEmployeesMap(anyInt(),
            anyString(),
            anyString(),
            anyString(),
            anyString(),
            any(Date.class));

    }

    @Test
    public void testAddEmployeeWithAnyValueAsNull()
    {
        String status = employeeService.addEmployee("P", null, "abc.", "address");
        assertEquals("Invalid Input", status);

        verify(employeeData,never()).verifyEmployeeWithFullName(anyString(),anyString());

        verify(employeeData, never()).setEmployeesMap(anyInt(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                any(Date.class));

    }
}
