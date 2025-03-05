package com.lokesh.mongodb_demo.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document
@Data
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

    private LocalDateTime createdDate;
}
