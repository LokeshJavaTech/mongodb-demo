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


    @Query(
            value = "{ 'age' : { $gte : ?0 , $lte : ?1 }}",
            fields = "{ 'mobileNumber' : 0 }"                       // Don't send mobileNumber in the response
    )
    List<Employee> findByAgeBetweenRange(int minAge, int maxAge);

    //List<Employee> findByAgeBetween(int minAge, int maxAge);      // Using repository query


}
