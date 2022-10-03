package ca.ericmarquis.gradingsystem.controlleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.ericmarquis.gradingsystem.donnees.CourseRepository;
import ca.ericmarquis.gradingsystem.donnees.StudentRepository;
import ca.ericmarquis.gradingsystem.modeles.Course;
import ca.ericmarquis.gradingsystem.modeles.Student;

@Controller
public class AddGradeController {
    private final StudentRepository repo_student;
    private final CourseRepository repo_course;

    @Autowired
    public AddGradeController(StudentRepository repo_student, CourseRepository repo_course) {
        this.repo_student = repo_student;
        this.repo_course = repo_course;
    }

    @RequestMapping(value="/addgrade", method=RequestMethod.GET)
    public String addCoursePage(Model model) {
        Iterable<Student> students = repo_student.findAll();
        Iterable<Course> courses = repo_course.findAll();
        model.addAttribute("sl", students);
        model.addAttribute("cl", courses);
        return "addgrade";
    }

}
