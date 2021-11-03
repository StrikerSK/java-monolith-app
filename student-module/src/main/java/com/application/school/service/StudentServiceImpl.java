package com.application.school.service;

import com.application.school.entity.Student;
import com.application.school.repository.FacultyRepository;
import com.application.school.repository.StudentRepository;
import com.application.school.repository.UniversityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@AllArgsConstructor
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;

    public Student getStudent(Long id){
        return studentRepository.findById(id).orElse(null);
    }

    // Lookup for all students occurrence
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void createStudent(Student student){
        log.info("Saving student: " + student.toString());
        studentRepository.save(student);
    }

    public long getStudentCount(){
        return getStudents().size();
    }

    public List<Student> getLimitedStudents(int count){
        List<Student> allUsers = studentRepository.findAll();
        List<Student> newUserList = new ArrayList<>();
        for(int i = 0; i < count; i++){
            newUserList.add(allUsers.get(i));
        }
        return newUserList;
    }

    public void deleteStudent(Long id){
        try {
            log.info("Student [{}] deleted!", id);
            studentRepository.deleteById(id);
        } catch (Exception e){
            log.warn("Student [{}] cannot be found!", id, e);
        }
    }

    private List<Long> getRandomNumberList(Integer count){
        List<Long> randomNumberList = new ArrayList<>();
        Random rnd = new Random();
        Long studentCount = studentRepository.getMaxId();
        for (Integer i = 0; i < count; i++){
            Boolean flag = false;
            Integer pomocna = rnd.nextInt(studentCount.intValue());
            while (getStudent(pomocna.longValue()) != null ){
                pomocna = rnd.nextInt(studentCount.intValue());
            }

            if (randomNumberList.size() != 0) {
                for (Integer j = 0; j < i; j++) {
                    if (randomNumberList.get(j) == pomocna.longValue()) {
                        flag = true;
                    }
                }
            }

            if (!flag){
                randomNumberList.add(pomocna.longValue());
            } else {
                i--;
            }
        }
        return randomNumberList;
    }

    public List<Student> getRandomStudents(Integer count){
        List<Long> numberList = getRandomNumberList(count);
        Collections.sort(numberList);
        List<Student> randomStudentList = new ArrayList<>();
        for (Long i : numberList){
            randomStudentList.add(studentRepository.getStudentById(i));
        }
        return randomStudentList;
    }

}