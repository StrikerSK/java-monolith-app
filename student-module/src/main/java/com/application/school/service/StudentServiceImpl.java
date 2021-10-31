package com.application.school.service;

import com.application.school.entity.Faculty;
import com.application.school.entity.Student;
import com.application.school.entity.University;
import com.application.school.repository.FacultyRepository;
import com.application.school.repository.StudentRepository;
import com.application.school.repository.UniversityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, UniversityRepository universityRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student getStudent(Long id){
        try {
            log.info("Calling method: getStudent({})", id);
            return studentRepository.getStudentById(id);
        } catch (Exception e){
            return null;
        }
    }

    public void createStudent(Student student){
        log.info("Saving student: " + student.toString());
        studentRepository.save(student);
    }

    public List<Student> getStudents(){
        log.info("Calling method: getStudents()");
        return studentRepository.getAllByOrderByIdAsc();
    }

    public Student findStudentDetail(Long id){
        log.info("Calling method: getStudentDetail(" + id + ")");
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            setUniversityAndFaculty(student);
        }
        return student;
    }

    @Override
    public void setUniversityAndFaculty(Student student) {
        if(student != null) {
            setStudentFaculty(student);
            setStudentUniversity(student);
        }
    }

    private void setStudentUniversity(Student student) {
        try {
            if (student.getUniversity().equals("")) {
                student.setUniversity("No university");
            } else {
                University university = universityRepository.getOne(Long.parseLong(student.getUniversity()));
                String universityName = university.getName();
                student.setUniversity(universityName);
            }
        } catch (NullPointerException e){
            student.setUniversity("No university");
        }
    }

    private void setStudentFaculty(Student student) {
        try {
            if(student.getFaculty().equals("")){
                student.setFaculty("No faculty");
            } else {
                Faculty faculty = facultyRepository.getOne(Long.parseLong(student.getFaculty()));
                String facultyName = faculty.getName();
                student.setFaculty(facultyName);
            }
        } catch (NullPointerException e){
            student.setFaculty("No faculty");
        }

    }

    public long getStudentCount(){
        return studentRepository.count();
    }

    public List<Student> getLimitedStudents(int count){
        List<Student> allUsers = studentRepository.findAll();
        List<Student> newUserList = new ArrayList<>();
        for(int i = 0; i < count; i++){
            newUserList.add(allUsers.get(i));
        }
        return newUserList;
    }

    public void deleteStudentById(Long id){
        try {
            log.info("Student [{}] deleted!", id);
            studentRepository.deleteById(id);
        } catch (Exception e){
            log.warn("Student [{}] cannot be found!", id, e);
        }
    }

    private Boolean isStudentPresent(Long id){
        return studentRepository.getStudentById(id) != null;
    }

    private List<Long> getRandomNumberList(Integer count){
        List<Long> randomNumberList = new ArrayList<>();
        Random rnd = new Random();
        Long studentCount = studentRepository.getMaxId();
        for (Integer i = 0; i < count; i++){
            Boolean flag = false;
            Integer pomocna = rnd.nextInt(studentCount.intValue());
            while (!isStudentPresent(pomocna.longValue())){
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