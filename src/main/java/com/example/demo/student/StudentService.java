package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// This class is responsible for the business logic of the application
@Service //<-- Making this a Bean (Component)
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
       return studentRepository.findAll(); // Returns a Lsit
    }

    //This is the POST method
    public void addNewStudent(Student student) {

        // Business Logic to see if there is already a student with this email
        Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken, please select a new one (Or steal it)");
        }

        // If the email is not taken
        studentRepository.save(student);

        // In application properties you are telling it to always include the server message

        System.out.println(student);

    }

    public void deleteStudent(Long studentId) {
        studentRepository.findById(studentId);

        // If the student exists
        boolean exists = studentRepository.existsById(studentId);

        if(!exists){
            throw new IllegalStateException("Student with id " + studentId + " Does not exists"); // This is the error message.
        }

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Could not find student"));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
           Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

           if(studentOptional.isPresent()){
               throw new IllegalStateException("Email Taken");
           }

           student.setEmail(email);
        }
    }
}
