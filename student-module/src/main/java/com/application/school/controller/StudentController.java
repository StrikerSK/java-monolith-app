package com.application.school.controller;

import com.application.school.entity.Student;
import com.application.school.service.IStudentService;
import com.application.school.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.SortedMap;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private SortedMap<String, String> programingLanguageOptions;

    @Resource
    private SortedMap<String, String> countryOptions;

    @Resource
    private SortedMap<String, String> languageOptions;

    private final IStudentService IStudentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.IStudentService = studentService;
    }

    @RequestMapping("/registrationForm")
    public String registerStudent(Model model){
        Student theStudent = new Student();
        model.addAttribute("student", theStudent);
        model.addAttribute("countries", countryOptions);
        model.addAttribute("progLang", programingLanguageOptions);
        model.addAttribute("languages", languageOptions);
        return "jsp/registration-page";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student")Student theStudent, Model model){
        IStudentService.createStudent(theStudent);
        IStudentService.setUniversityAndFaculty(theStudent);
        model.addAttribute("theStudent", theStudent);
        return "jsp/student-confirmation";
    }

    @RequestMapping("/list")
    public String getStudentList(Model model, @RequestParam(name = "count",required = false)Integer count, @RequestParam(name = "random", required = false)Boolean random){
        if (count != null && random != null) {
            if (IStudentService.getStudents().size() > count) {
                if(random){
                    List<Student> students = IStudentService.getRandomStudents(count);
                    model.addAttribute("students", students);
                    model.addAttribute("actualCount", count);
                    model.addAttribute("userCount", count);
                    model.addAttribute("random", true);
                } else {
                    List<Student> students = IStudentService.getLimitedStudents(count);
                    model.addAttribute("students", students);
                    model.addAttribute("actualCount", count);
                    model.addAttribute("userCount", count);
                    model.addAttribute("random", false);
                }
            }
        } else {
            List<Student> students = IStudentService.getStudents();
            model.addAttribute("students", students);
            model.addAttribute("actualCount", IStudentService.getStudentCount());
            model.addAttribute("userCount", count);
            model.addAttribute("random", random);
        }
        return "jsp/student-list";
    }

    @GetMapping("/detail/{studentId}")
    public String getDetail(Model model, @PathVariable("studentId")Long id){
        Student student = IStudentService.findStudentDetail(id);
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
        Student getStudent = IStudentService.getStudent(id);
        model.addAttribute("facultyValue", getStudent.getFaculty());
        model.addAttribute("universityValue", getStudent.getUniversity());
        model.addAttribute("countries", countryOptions);
        model.addAttribute("progLang", programingLanguageOptions);
        model.addAttribute("languages", languageOptions);
        IStudentService.setUniversityAndFaculty(getStudent);
        model.addAttribute("editedStudent", getStudent);
        return "jsp/student-edit";
    }

    @RequestMapping(value = "/postEdit", method = RequestMethod.POST)
    public String confirmEdit(@ModelAttribute("editedStudent")Student student){
        IStudentService.createStudent(student);
        return "redirect:/student/list";
    }

    @RequestMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable("studentId")Long id){
        IStudentService.deleteStudentById(id);
        return "redirect:/student/list";
    }

}
