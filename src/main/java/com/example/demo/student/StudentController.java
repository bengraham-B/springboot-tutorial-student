package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student") // Path in url -> http://localhost:8080/api/v1/student
public class StudentController {
    // All the resources for the Student API in this controller class

    private final StudentService studentService;

    @Autowired // <-- Dependency injection
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent(){
        return studentService.getStudent(); // Method from Student Service
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){ //Getting student from the client, from the request body and then map it into a student instance
        studentService.addNewStudent(student); // Inside the Controller file
    }

    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId, // Getting variable from URL and storing in a variable
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }




}
