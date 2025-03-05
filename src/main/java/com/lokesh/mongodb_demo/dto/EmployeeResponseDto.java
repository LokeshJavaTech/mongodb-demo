package com.lokesh.mongodb_demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lokesh.mongodb_demo.collection.Employee;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponseDto {
    String employeeId;
    String mobileNumber;
    Employee employee;
    List<Employee> employeeList;
    String responseMessage;
}
