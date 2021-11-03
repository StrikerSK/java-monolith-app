package com.application.school.service;

import com.application.school.entity.Faculty;
import com.application.school.entity.University;

import java.util.List;
import java.util.Optional;

public interface ISchoolService {

	List<University> getAllUniversities();
	Optional<University> getUniversity(Long id);
	Optional<University> getUniversity(String name);

	List<Faculty> getAllFaculties();
	List<Faculty> getUniversityFaculties(Long id);
	List<Faculty> getUniversityFaculties(String name);

}
