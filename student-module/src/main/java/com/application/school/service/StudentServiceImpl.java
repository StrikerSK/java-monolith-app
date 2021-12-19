package com.application.school.service;

import com.application.school.entity.Student;
import com.application.school.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;

    public Student getStudent(Long id){
        return studentRepository.findById(id).orElse(null);
    }

    // Lookup for all students occurrence
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getUniversityStudents(String universityName) {
        return getStudents().stream()
                .filter(e -> universityName.equals(e.getUniversity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getFacultyStudents(String universityName, String facultyName) {
        return getUniversityStudents(universityName).stream()
                .filter(e -> facultyName.equals(e.getFaculty()))
                .collect(Collectors.toList());
    }

    public void saveStudent(Student student){
        log.info("Saving student: " + student.toString());
        studentRepository.save(student);
    }

    public void deleteStudent(Long id){
        try {
            log.info("Student [{}] deleted!", id);
            studentRepository.deleteById(id);
        } catch (Exception e){
            log.warn("Student [{}] cannot be found!", id, e);
        }
    }

}