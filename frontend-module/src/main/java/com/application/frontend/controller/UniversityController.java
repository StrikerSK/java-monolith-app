package com.application.frontend.controller;

import com.application.school.entity.Faculty;
import com.application.school.entity.University;
import com.application.school.repository.UniversityRepository;
import com.application.school.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityRepository universityRepository;
    private final SchoolService schoolService;

    @RequestMapping("/getUniversities")
    public List<University> getUniversities(){
        List<University> universities = universityRepository.findAll();
        universities.sort(Comparator.comparing(University::getName));
        return universities;
    }

    @RequestMapping("/getAllFaculties")
    public List<Faculty> getAllFaculties(){
        return this.schoolService.getAllFaculties();
    }

    @RequestMapping("/getFaculties")
    public List<Faculty> getAllUniversityFaculties(@RequestParam("university")String name){
        return this.schoolService.getUniversityFaculties(name);
    }

}
