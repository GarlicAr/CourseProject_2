package lv.venta.controllers;

import lv.venta.models.security.MyUser;
import lv.venta.models.users.Student;
import lv.venta.services.impl.security.MyUserDetailsManagerImpl;
import lv.venta.services.users.IStudentCRUDService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/student")
public class StudentController {

	private static Logger logger = LogManager.getLogger(StudentController.class);
	
	@Autowired
	MyUserDetailsManagerImpl userService;
	
    @Autowired
    private IStudentCRUDService studentService;

    @GetMapping("/showAll")
    public String showAllStudents(Model model) {
    	ArrayList<Student> tempArray = studentService.selectAllStudents();
        model.addAttribute("student", tempArray);
        return "student-all-page";
    }

    @GetMapping("/show/{matriculaNo}")
    public String showStudentByMatriculaNo(@PathVariable("matriculaNo") String matriculaNo, Model model) {
        try {
            Student student = studentService.selectStudentByMatriculaNo(matriculaNo);
            if(student != null) {
                model.addAttribute("MyStudentByMatriculaNo", student);
                return "student-one-page";
            } 
        } catch (Exception e) {
        	 logger.error("Error in showStudentByMatriculaNo: " + e.getMessage());
        }
        return "error-page";
    }

    @PostMapping("/remove/{matriculaNo}")
    public String removeStudentByMatriculaNo(@PathVariable("matriculaNo") String matriculaNo) {
        try {
            studentService.deleteStudentByMatriculaNo(matriculaNo);
            return "redirect:/student/showAll";
        } catch (Exception e) {
        	 logger.error("Error in removeStudentByMatriculaNo: " + e.getMessage());
            return "error-page";
        }
    }



    @GetMapping("/insertNew")
    public String insertNewStudent(Model model) {
        List<MyUser> users = userService.allUsers(); // Fetch all users
        model.addAttribute("users", users);
        model.addAttribute("student", new Student());
        return "student-add-page";
    }

    @PostMapping("/insertNew")
    public String insertNewStudentPost(@Valid @ModelAttribute("student") Student student,@ModelAttribute("user") MyUser user, BindingResult result) {
        if (!result.hasErrors()) {
        	Student stud = new Student(
                    student.getPersonName(),
                    student.getSurname(),
                    student.getPersonalCode(),
                    student.getUser(),
        			student.getMatriculaNo());
            studentService.insertNewStudent(stud);
            return "redirect:/student/showAll";
        } else {
        	 logger.error("Error in insertNewStudentPost: Validation failed");
            return "error-page";
        }
    }

    @GetMapping("/update/{matriculaNo}")
    public String showUpdateForm(@PathVariable("matriculaNo") String matriculaNo, Model model) {
        try {
            Student student = studentService.selectStudentByMatriculaNo(matriculaNo);
            model.addAttribute("student", student);
            return "update-student";
        } catch (Exception e) {
        	logger.error("Error in showUpdateForm: " + e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{matriculaNo}")
    public String updateStudentByMatriculaNo(@PathVariable("matriculaNo") String matriculaNo, @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "update-student";
        } else {
            try {
                studentService.updateStudentByMatriculaNo(matriculaNo, student);
                return "redirect:/student/show/" + student.getMatriculaNo();
            } catch (Exception e) {
            	 logger.error("Error in updateStudentByMatriculaNo: " + e.getMessage());
                return "redirect:/error-page";
            }
        }
    }
}
