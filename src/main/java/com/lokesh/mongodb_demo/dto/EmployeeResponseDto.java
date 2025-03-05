package com.lokesh.mongodb_demo.dto;

import com.lokesh.mongodb_demo.collection.Employee;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EmployeeResponseDto {
    String employeeId;
    String mobileNumber;
    Employee employee;
    List<Employee> employeeList;
    String responseMessage;
}
