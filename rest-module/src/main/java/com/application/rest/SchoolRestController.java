package com.application.rest;

import com.application.school.entity.Faculty;
import com.application.school.entity.University;
import com.application.school.service.ISchoolService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/university")
@Slf4j
public class SchoolRestController {

    private final ISchoolService schoolService;

    @RequestMapping("/list")
    public List<University> getUniversities(){
        List<University> universities = schoolService.getAllUniversities();
        universities.sort(Comparator.comparing(University::getName));
        return universities;
    }

    @RequestMapping("/getAllFaculties")
    public List<Faculty> getAllFaculties(){
        return this.schoolService.getAllFaculties();
    }

    @RequestMapping("/{name}/faculties")
    public List<Faculty> getAllUniversityFaculties(@PathVariable String name){
        log.info("Searched university: {}", name);
        return schoolService.getUniversityFaculties(name);
    }

}
