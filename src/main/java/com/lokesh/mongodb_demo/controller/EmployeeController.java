package com.lokesh.mongodb_demo.controller;

import com.lokesh.mongodb_demo.dto.EmployeeResponseDto;
import com.lokesh.mongodb_demo.collection.Employee;
import com.lokesh.mongodb_demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody Employee employee) {
        employee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(EmployeeResponseDto.builder()
                .employee(employee)
                .responseMessage("Employee Saved Successfully.")
                .build());
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable("id") String id) {
        Employee employee = employeeService.findEmployeeById(id);
        if (employee != null)
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .employee(employee)
                    .responseMessage("Employee Found Successfully.")
                    .build());
        else
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .employeeId(id)
                    .responseMessage("Employee not found with given id.")
                    .build());
    }

    @GetMapping("/mobile/{mobileNumber}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeByMobileNumber(@PathVariable("mobileNumber") String mobileNumber) {
        List<Employee> employeeList = employeeService.findEmployeeByMobileNumber(mobileNumber);
        if (!CollectionUtils.isEmpty(employeeList))
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .mobileNumber(mobileNumber)
                    .employeeList(employeeList)
                    .responseMessage("Employees Found Successfully with given mobile number.")
                    .build());
        else
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .mobileNumber(mobileNumber)
                    .responseMessage("Employees not found with given mobile number.")
                    .build());
    }

    @GetMapping
    public ResponseEntity<EmployeeResponseDto> getAllEmployees() {
        List<Employee> employeeList = employeeService.findAll();
        if (CollectionUtils.isEmpty(employeeList))
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .responseMessage("No employees found.")
                    .build());
        else
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .employeeList(employeeList)
                    .responseMessage("All employees retrieved Successfully.")
                    .build());
    }

    @GetMapping("/age")
    public ResponseEntity<EmployeeResponseDto> findByAgeBetween(@RequestParam int minAge,
                                                                        @RequestParam int maxAge) {
        List<Employee> employeeList = employeeService.findByAgeBetween(minAge, maxAge);
        if (CollectionUtils.isEmpty(employeeList))
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .responseMessage("No employees found for age between.")
                    .build());
        else
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .employeeList(employeeList)
                    .responseMessage("Successfully retrieved employee for age between.")
                    .build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable("id") String id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        if (updatedEmployee != null)
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .employee(updatedEmployee)
                    .responseMessage("Employee Updated Successfully.")
                    .build());
        else
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .employeeId(id)
                    .responseMessage("Employee not found with given id.")
                    .build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EmployeeResponseDto> deleteEmployee(@PathVariable("id") String id) {
        boolean isEmployeeDeleted = employeeService.deleteEmployeeById(id);
        if (isEmployeeDeleted)
            return ResponseEntity.ok(EmployeeResponseDto.builder()
                    .employeeId(id)
                    .responseMessage("Employee Deleted Successfully.")
                    .build());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(EmployeeResponseDto.builder()
                            .employeeId(id)
                            .responseMessage("Employee not found with provided employee id.")
                            .build());
    }
}