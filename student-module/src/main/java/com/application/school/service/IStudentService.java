package com.application.school.service;

import com.application.school.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudentService {

     Student getStudent(Long id);
     void createStudent(Student student);
     List<Student> getStudents();
     Student findStudentDetail(Long id);
     void setUniversityAndFaculty(Student student);
     long getStudentCount();
     List<Student> getLimitedStudents(int count);
     void deleteStudentById(Long id);
     List<Student> getRandomStudents(Integer count);

}
