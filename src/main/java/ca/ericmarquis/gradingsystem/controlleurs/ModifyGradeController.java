package ca.ericmarquis.gradingsystem.controlleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ericmarquis.gradingsystem.donnees.CourseRepository;
import ca.ericmarquis.gradingsystem.donnees.GradeRepository;
import ca.ericmarquis.gradingsystem.donnees.StudentRepository;
import ca.ericmarquis.gradingsystem.modeles.Course;
import ca.ericmarquis.gradingsystem.modeles.Grade;
import ca.ericmarquis.gradingsystem.modeles.Student;

@Controller
public class ModifyGradeController {
    private final GradeRepository repo_grade;
    private final StudentRepository repo_student;
    private final CourseRepository repo_course;

    @Autowired
    public ModifyGradeController(GradeRepository repo_grade, StudentRepository repo_student,
            CourseRepository repo_course) {
        this.repo_grade = repo_grade;
        this.repo_student = repo_student;
        this.repo_course = repo_course;
    }

    @RequestMapping(value = "/modifygrade", method = RequestMethod.POST)
    public String modifyGrade(@RequestParam String gradeId, @RequestParam String studentChoice,
            @RequestParam String courseChoice, @RequestParam int mark, Model model) {

        Student s = repo_student.findById(Long.valueOf(studentChoice)).orElse(null);
        Course c = repo_course.findById(Long.valueOf(courseChoice)).orElse(null);

        Grade gradeToUpdate = new Grade(s, c, mark);

        repo_grade.save(gradeToUpdate);

        Iterable<Grade> grades = repo_grade.findAll();
        model.addAttribute("gl", grades);
        return "gradeList";
    }
}
