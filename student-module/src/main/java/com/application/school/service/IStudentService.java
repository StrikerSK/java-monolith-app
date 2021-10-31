package com.application.school.service;

import com.application.school.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudentService {

     Student getStudent(Long id);
     void deleteStudent(Long id);
     void createStudent(Student student);
     List<Student> getStudents();
     List<Student> getLimitedStudents(int count);
     List<Student> getRandomStudents(Integer count);

}
