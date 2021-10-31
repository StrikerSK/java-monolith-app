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
import org.springframework.web.bind.annotation.RequestMethod;
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
    private final SortedMap<String, String> programingLanguageOptions;

    @Resource
    private final SortedMap<String, String> countryOptions;

    @Resource
    private final SortedMap<String, String> languageOptions;

    private final IStudentService studentService;

    private final ISchoolService schoolService;

    @RequestMapping("/registrationForm")
    public String registerStudent(Model model){
        Student theStudent = new Student();
        model.addAttribute("student", theStudent);
        model.addAttribute("countries", countryOptions.values());
        model.addAttribute("progLang", programingLanguageOptions);
        model.addAttribute("languages", languageOptions.values());
        model.addAttribute("universities", schoolService.getUniversities().stream().map(University::getName).collect(Collectors.toList()));
        return "school/registration-page";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student")Student theStudent, Model model){
        studentService.createStudent(theStudent);
        studentService.setUniversityAndFaculty(theStudent);
        model.addAttribute("theStudent", theStudent);
        return "jsp/student-confirmation";
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
            model.addAttribute("actualCount", studentService.getStudentCount());
            model.addAttribute("userCount", count);
            model.addAttribute("random", random);
        }
        return "jsp/student-list";
    }

    @GetMapping("/detail/{studentId}")
    public String getDetail(Model model, @PathVariable("studentId")Long id){
        Student student = studentService.findStudentDetail(id);
        if (student != null) {
            model.addAttribute("studentDetail", student);
            return "jsp/student-detail";
        }else{
            model.addAttribute("id", id);
            return  "jsp/student-empty-detail";
        }
    }

    @GetMapping("/edit/{studentId}")
    public String getStudentEdit(Model model, @PathVariable("studentId")Long id){
        Student getStudent = studentService.getStudent(id);
        model.addAttribute("facultyValue", getStudent.getFaculty());
        model.addAttribute("universityValue", getStudent.getUniversity());
        model.addAttribute("countries", countryOptions);
        model.addAttribute("progLang", programingLanguageOptions);
        model.addAttribute("languages", languageOptions);
        studentService.setUniversityAndFaculty(getStudent);
        model.addAttribute("editedStudent", getStudent);
        return "jsp/student-edit";
    }

    @RequestMapping(value = "/postEdit", method = RequestMethod.POST)
    public String confirmEdit(@ModelAttribute("editedStudent")Student student){
        studentService.createStudent(student);
        return "redirect:/student/list";
    }

    @RequestMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable("studentId")Long id){
        studentService.deleteStudentById(id);
        return "redirect:/student/list";
    }

}
