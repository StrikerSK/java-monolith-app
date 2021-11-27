package com.application.rest;

import com.application.school.entity.Student;
import com.application.school.service.IStudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentRestController {

	private final IStudentService studentService;

	@RequestMapping("/list")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@PostMapping(value = {"", "/add"})
	public void createStudent(@RequestBody Student student) {
		studentService.createStudent(student);
	}

	@GetMapping("/{id}")
	public Student getStudent(@PathVariable Long id) {
		return studentService.getStudent(id);
	}

	@PutMapping("/{id}")
	public void updateStudent(@PathVariable Long id, @RequestBody Student newStudent) {
		newStudent.setId(id);
		studentService.createStudent(newStudent);
	}

	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
	}

}
