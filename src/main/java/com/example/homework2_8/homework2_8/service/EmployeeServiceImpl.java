package com.example.homework2_8.homework2_8.service;

import com.example.homework2_8.homework2_8.Employee;
import com.sun.tools.jconsole.JConsoleContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    private List<Employee> employeeList;

    public Employee add(String surname, String name, String secondName, int department, float salary) {
        Employee employee = new Employee(surname, name, secondName, department, salary);
        Set<String> set = employeeList.stream().map(e->e.getFullName())
                        .collect(Collectors.toSet());
        if (set.contains(employee.getFullName())) {
            throw new RuntimeException("Такой сотрудник " + employee.getFullName() + " уже есть");
        }else{
            employeeList.add(employee);
            return employee;
        }
    }

    @Override
    public Optional<Employee> getEmployeeMaxSalaryByDep(int departmentId) {
        return employeeList.stream().filter(e -> e.getDepartment() == departmentId)
                .max(Comparator.comparingDouble(employee -> employee.getSalary()));
    }

    @Override
    public Optional<Employee> getEmployeeMinSalaryByDep(int departmentId) {
        return employeeList.stream().filter(e -> e.getDepartment() == departmentId)
                .min(Comparator.comparingDouble(employee -> employee.getSalary()));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList.stream().sorted(Comparator.comparingInt(e -> e.getDepartment()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(int departmentId) {
        if (departmentId == -1) {
            return getAllEmployees();
        } else {
            return employeeList.stream().filter(e -> e.getDepartment() == departmentId)
                    .collect(Collectors.toList());
        }
    }
}
