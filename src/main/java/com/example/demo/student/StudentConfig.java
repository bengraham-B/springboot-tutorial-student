package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;
import static java.time.Month.MAY;
import static java.util.Calendar.DECEMBER;

@Configuration
public class StudentConfig {

    // Making a Bean
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student Ben = new Student(
                    1L,
                    "Ben",
                    LocalDate.of(2000, MAY, 5),
                    "Ben@gmail.com"
            );

            Student James = new Student(
                    1L,
                    "James",
                    LocalDate.of(2000, MAY, 5),
                    "james@gmail.com"
            );

            Student Roger = new Student(
                    1L,
                    "Roger",
                    LocalDate.of(2000, MAY, 5),
                    "roger@gmail.com"
            );


            //Save to database
            studentRepository.saveAll(
                    List.of(Roger)
            );
        };
    }
}
