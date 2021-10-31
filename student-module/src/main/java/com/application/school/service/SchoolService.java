package com.application.school.service;

import com.application.school.entity.Faculty;
import com.application.school.entity.University;
import com.application.school.repository.FacultyRepository;
import com.application.school.repository.UniversityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SchoolService implements ISchoolService {

    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;

    // Lookup for all faculties data in DB
    public List<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }

    // Find faculties connected to specific university
    public List<Faculty> getUniversityFaculties(Long id){
        return new ArrayList<>(universityRepository.getOne(id).getFaculties());
    }

    public List<University> getUniversities() {
        return universityRepository.findAll();
    }

}
