package com.eduexcellence.studentms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String grade;

    // Getters, Setters, and Constructors
    // (If using Lombok, you can just annotate with @Data, @NoArgsConstructor, @AllArgsConstructor)

    public Student() {}

    public Student(String name, String address, String grade) {
        this.name = name;
        this.address = address;
        this.grade = grade;
    }

    // Standard Getters and Setters for id, name, address, grade...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}