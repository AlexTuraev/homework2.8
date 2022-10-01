package com.example.homework2_8.homework2_8.service;

import com.example.homework2_8.homework2_8.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee add(String surname, String name, String secondName, int department, float salary);
    Optional<Employee> getEmployeeMaxSalaryByDep(int departmentId);
    Optional<Employee> getEmployeeMinSalaryByDep(int departmentId);

    List<Employee> getAllEmployees();

    List<Employee> getAllEmployeesByDepartment(int departmentId);
}
