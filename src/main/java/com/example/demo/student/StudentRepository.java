package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This is part of the Data Access Layer
@Repository  // tells Spring that this interface is responsible for data access
public interface StudentRepository extends JpaRepository<Student, Long> {
    // the following is equivalent to the following JPQL code:
        // @Query("SELECT s FROM Student s WHERE s.email = ?1");
    Optional<Student> findStudentByEmail(String email);
}
