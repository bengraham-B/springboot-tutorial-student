package com.example.demo.student;

//Interacts with JPA / DB

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Specifying Student and the ID (Long)
@Repository // This repository is responsible for data access
public interface StudentRepository extends JpaRepository<Student, Long> {

    // SQL Equivalent -> SELECT * FROM student WHERE email=''
//    @Query("SELECT s FROM Student s WHERE s.email=?1") // <- JBQL which is not straight SQL :(
    Optional<Student> findStudentByEmail(String email);
}
