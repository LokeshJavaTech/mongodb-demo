package com.lokesh.mongodb_demo.repository;

import com.lokesh.mongodb_demo.collection.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Query("{ 'mobileNumber' : ?0 }")
    List<Employee> findByMobileNumber(String mobileNumber);
}
