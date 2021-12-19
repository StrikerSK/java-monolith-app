package com.application.school.service;

import com.application.school.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStudentService {

     Student getStudent(Long id);
     void deleteStudent(Long id);
     void saveStudent(Student student);
     List<Student> getStudents();

     List<Student> getUniversityStudents(String universityName);
     List<Student> getFacultyStudents(String universityName, String facultyName);

}
