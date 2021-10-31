package com.application.frontend.controller;

import com.application.school.entity.Faculty;
import com.application.school.entity.University;
import com.application.school.repository.UniversityRepository;
import com.application.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UniversityRepository universityRepository;
    private final SchoolService schoolService;

    @Autowired
    public UniversityController(UniversityRepository universityRepository, SchoolService schoolService) {
        this.universityRepository = universityRepository;
        this.schoolService = schoolService;
    }

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

    @RequestMapping("/getAllUniversityFaculties")
    public List<Faculty> getAllUniversityFaculties(@RequestParam("uniId")Long id){
        return this.schoolService.getUniversityFaculties(id);
    }

    @RequestMapping("/getFaculties")
    public List<Faculty> getFaculties(@RequestParam(value = "uniId", required = true)Long id){
        return this.schoolService.getUniversityFaculties(id);
    }

}
