package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // this tells the server to serve REST endpoints
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired  // tells Spring to 'magically' instantiate and inject the studentService from above
    public StudentController(StudentService studentService) {
        // this.studentService = new StudentService();  // don't do this; use dependency injection instead
        this.studentService = studentService;  // uses dependency injection
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {  // map the request body into a student
        studentService.addNewStudent(student);
    }
}
