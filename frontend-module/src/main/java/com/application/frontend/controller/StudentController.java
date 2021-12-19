package com.application.frontend.controller;

import com.application.school.entity.Student;
import com.application.school.entity.University;
import com.application.school.service.ISchoolService;
import com.application.school.service.IStudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

@Component
@Controller
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    @Resource
    private final List<String> spokenLanguagesList;

    @Resource
    private final List<String> programingLanguagesList;

    private final IStudentService studentService;

    private final ISchoolService schoolService;

    @RequestMapping("/add")
    public String registerStudent(Model model){
        Student theStudent = new Student();
        model.addAttribute("student", theStudent);
        model.addAttribute("progLang", programingLanguagesList);
        model.addAttribute("languages", spokenLanguagesList);
        model.addAttribute("universities", schoolService.getAllUniversities().stream().map(University::getName).collect(Collectors.toList()));
        return "school/studentForm";
    }

    @RequestMapping("/process")
    public String processForm(@ModelAttribute("student")Student student, Model model){
        studentService.saveStudent(student);
        model.addAttribute("student", student);
        return "school/studentCard";
    }

    @RequestMapping("/list")
    public String getStudentList(
            Model model,
            @RequestParam(name = "count",required = false, defaultValue = "100")Integer count,
            @RequestParam(name = "random", required = false, defaultValue = "false")Boolean random
    ){
        List<Student> students = studentService.getStudents();

        if (random) {
            Collections.shuffle(students);
        }

        if (students.size() > count) {
            List<Student> tempArr = new ArrayList<>();
            for(int i = 0; i < count; i++) {
                tempArr.add(students.get(i));
            }
            students = tempArr;
        }

        model.addAttribute("students", students);
        model.addAttribute("actualCount", students.size());
        model.addAttribute("userCount", count);
        model.addAttribute("random", random);
        return "school/studentList";
    }

    @GetMapping("/detail/{studentId}")
    public String getDetail(Model model, @PathVariable("studentId")Long id){
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        model.addAttribute("searchedID", id);
        return "school/studentCard";
    }

    @GetMapping("/edit/{studentId}")
    public String getStudentEdit(Model model, @PathVariable("studentId")Long id){
        Student getStudent = studentService.getStudent(id);
        model.addAttribute("universities", schoolService.getAllUniversities().stream().map(University::getName).collect(Collectors.toList()));
        model.addAttribute("progLang", programingLanguagesList);
        model.addAttribute("languages", spokenLanguagesList);
        model.addAttribute("student", getStudent);
        return "school/studentForm";
    }

    @RequestMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable("studentId")Long id){
        studentService.deleteStudent(id);
        return "redirect:/student/list";
    }

}
