package com.example.SpringPostgres;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="Person_1")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;


    // Default constructor (Required by JPA)
    public Person(){

    }

    // Parameterized constructor
    public Person(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // toString method
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // getter method id
    public long getId() {
        return id;
    }

    // setter method for id
    public void setId(long id) {
        this.id = id;
    }

    // getter method for name
    public String getName() {
        return name;
    }

    // setter method for name
    public void setName(String name) {
        this.name = name;
    }
}
