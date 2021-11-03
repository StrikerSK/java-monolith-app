package com.application.school.service;

import com.application.school.entity.Faculty;
import com.application.school.entity.University;
import com.application.school.repository.UniversityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SchoolService implements ISchoolService {

    private final UniversityRepository universityRepository;

    // Lookup for all faculties data in DB
    public List<Faculty> getAllFaculties(){
        return universityRepository.findAll().stream()
                .flatMap(e -> e.getFaculties().stream())
                .collect(Collectors.toList());
    }

    // Find faculties connected to specific university
    public List<Faculty> getUniversityFaculties(Long id){
        return new ArrayList<>(universityRepository.getOne(id).getFaculties());
    }

    public List<Faculty> getUniversityFaculties(String name){
        return new ArrayList<>(universityRepository.findByName(name).get().getFaculties());
    }

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public Optional<University> getUniversity(Long id) {
        return universityRepository.findById(id);
    }

    public Optional<University> getUniversity(String name) {
        return universityRepository.findByName(name);
    }
}
