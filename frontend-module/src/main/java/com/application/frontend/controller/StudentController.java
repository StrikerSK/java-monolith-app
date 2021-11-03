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
import java.util.List;
import java.util.SortedMap;
import java.util.stream.Collectors;

@Component
@Controller
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    @Resource
    private final SortedMap<String, String> spokenLanguageOptions;

    @Resource
    private final SortedMap<String, String> programingLanguageOptions;

    @Resource
    private final SortedMap<String, String> countryOptions;

    private final IStudentService studentService;

    private final ISchoolService schoolService;

    @RequestMapping("/add")
    public String registerStudent(Model model){
        Student theStudent = new Student();
        model.addAttribute("student", theStudent);
        model.addAttribute("countries", countryOptions.values());
        model.addAttribute("progLang", programingLanguageOptions.values());
        model.addAttribute("languages", spokenLanguageOptions.values());
        model.addAttribute("universities", schoolService.getAllUniversities().stream().map(University::getName).collect(Collectors.toList()));
        return "school/studentForm";
    }

    @RequestMapping("/process")
    public String processForm(@ModelAttribute("student")Student student, Model model){
        studentService.createStudent(student);
        model.addAttribute("student", student);
        return "school/studentCard";
    }

    @RequestMapping("/list")
    public String getStudentList(Model model, @RequestParam(name = "count",required = false)Integer count, @RequestParam(name = "random", required = false)Boolean random){
        if (count != null && random != null) {
            if (studentService.getStudents().size() > count) {
                if(random){
                    List<Student> students = studentService.getRandomStudents(count);
                    model.addAttribute("students", students);
                    model.addAttribute("actualCount", count);
                    model.addAttribute("userCount", count);
                    model.addAttribute("random", true);
                } else {
                    List<Student> students = studentService.getLimitedStudents(count);
                    model.addAttribute("students", students);
                    model.addAttribute("actualCount", count);
                    model.addAttribute("userCount", count);
                    model.addAttribute("random", false);
                }
            }
        } else {
            List<Student> students = studentService.getStudents();
            model.addAttribute("students", students);
            model.addAttribute("actualCount", studentService.getStudents().size());
            model.addAttribute("userCount", count);
            model.addAttribute("random", random);
        }
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
        model.addAttribute("countries", countryOptions.values());
        model.addAttribute("progLang", programingLanguageOptions.values());
        model.addAttribute("languages", spokenLanguageOptions.values());
        model.addAttribute("student", getStudent);
        return "school/studentForm";
    }

    @RequestMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable("studentId")Long id){
        studentService.deleteStudent(id);
        return "redirect:/student/list";
    }

}
