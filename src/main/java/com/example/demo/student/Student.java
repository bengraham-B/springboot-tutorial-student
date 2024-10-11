package com.example.demo.student;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Student {
    @Id // Sets id as primary key
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private LocalDate dob;
    private String email;

    @Transient //Says that this does not need to be a column in the DB
    private Integer age;

    public Student(){

    }

    public Student(Long id, String name, LocalDate dob, String email){
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;

    }

    public Student(String name, LocalDate dob, String email){
        this.name = name;
        this.dob = dob;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public LocalDate getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears(); // Calculating the current age of user
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
