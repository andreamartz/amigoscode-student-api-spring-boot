package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

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
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                "student with id " + studentId + " does not exist"
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional  // means that you don't have to implement any JPQL query
    public void updateStudent(Long studentId, String name, String email) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new IllegalStateException(
                "student with id " + studentId + " does not exist"
            );
        }

        Student student = studentOptional.get();

        if (name != null && name.length() > 0 && student.getName() != name) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && student.getEmail() != email) {
            Optional<Student> studentOptional1 = studentRepository.findStudentByEmail(email);
            if (studentOptional1.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }

        studentRepository.save(student);
    }
}
