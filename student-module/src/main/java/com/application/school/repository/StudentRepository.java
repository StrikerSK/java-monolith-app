package com.application.school.repository;

import com.application.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200", "https://whispering-wildwood-78414.herokuapp.com"})
public interface StudentRepository extends JpaRepository<Student,Long> {

    Student getStudentById(Long id);

    @Query("SELECT MAX(id) FROM Student")
    Long getMaxId();

    List<Student> getAllByOrderByIdAsc();

}
