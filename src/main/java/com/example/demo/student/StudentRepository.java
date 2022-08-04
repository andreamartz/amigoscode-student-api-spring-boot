package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This is part of the Data Access Layer
@Repository  // tells Spring that this interface is responsible for data access
public interface StudentRepository extends JpaRepository<Student, Long> {

}
