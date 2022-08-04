package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service  // exactly like @Component, but more semantic
    // @Service tells Spring that studentService is a class that has to be instantiated, i.e. it has to be a Spring bean
    // this annotation (or @Component) lets our controller know that this is the class that needs to be instantiated and injected
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();  // returns a list to us
    }

    public void addNewStudent(Student student) {
        System.out.println(student);
    }
}
