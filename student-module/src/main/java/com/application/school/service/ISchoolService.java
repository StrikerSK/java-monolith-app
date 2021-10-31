package com.application.school.service;

import com.application.school.entity.Faculty;
import com.application.school.entity.University;

import java.util.List;

public interface ISchoolService {

	List<Faculty> getAllFaculties();
	List<Faculty> getUniversityFaculties(Long id);
	List<University> getUniversities();

}
