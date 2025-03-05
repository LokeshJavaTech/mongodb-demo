package com.lokesh.mongodb_demo.service;

import com.lokesh.mongodb_demo.repository.EmployeeRepository;
import com.lokesh.mongodb_demo.collection.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        employee.setCreatedDate(LocalDateTime.now());
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> findEmployeeByMobileNumber(String mobileNumber) {
        return employeeRepository.findByMobileNumber(mobileNumber);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(String id, Employee employee) {
        return employeeRepository.findById(id)
                .map(dbEmployee -> {
                    employee.setId(dbEmployee.getId());
                    employeeRepository.save(employee);
                    return employee;
                })
                .orElse(null);
    }

    public boolean deleteEmployeeById(String id) {
        return employeeRepository.findById(id)
                .map(dbEmployee -> {
                    employeeRepository.delete(dbEmployee);
                    return true;
                })
                .orElse(false);
    }
}
