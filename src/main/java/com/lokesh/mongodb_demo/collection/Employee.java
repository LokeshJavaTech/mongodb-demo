package com.lokesh.mongodb_demo.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee
{
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "mobileNumber")
    @Indexed(unique = true)
    private String mobileNumber;

    @Field(name = "city")
    private String city;

    private int age;

    private LocalDateTime createdDate;
}
