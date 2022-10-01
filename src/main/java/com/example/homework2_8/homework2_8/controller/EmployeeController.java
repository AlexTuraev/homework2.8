package com.example.homework2_8.homework2_8.controller;

import com.example.homework2_8.homework2_8.Employee;
import com.example.homework2_8.homework2_8.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("surname") String surname,
                      @RequestParam("name") String name,
                      @RequestParam("secondName") String secondName,
                      @RequestParam("department") int department,
                      @RequestParam("salary") float salary){
        return employeeService.add(surname, name, secondName, department, salary);
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeMaxSalaryByDepartment(@RequestParam("departmentId") int departmentId) {
        return employeeService.getEmployeeMaxSalaryByDep(departmentId)
                .orElseThrow(()->new RuntimeException("Employee with max salary not found in " + departmentId));
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeMinSalaryByDepartment(@RequestParam("departmentId") int departmentId){
        return employeeService.getEmployeeMinSalaryByDep(departmentId)
                .orElseThrow();
    }

    @GetMapping("/all")
    public List<Employee> getEmployeesByDepartment(@RequestParam(defaultValue = "-1") int departmentId) {
        return employeeService.getAllEmployeesByDepartment(departmentId);
    }
}
