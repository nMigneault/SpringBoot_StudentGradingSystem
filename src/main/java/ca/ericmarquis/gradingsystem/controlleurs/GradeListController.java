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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GradeListController {
    private final GradeRepository repo_grade;
    private final StudentRepository repo_student;
    private final CourseRepository repo_course;

    @Autowired
    public GradeListController(GradeRepository repo_grade, StudentRepository repo_student, CourseRepository repo_course) {
        this.repo_grade = repo_grade;
        this.repo_student = repo_student;
        this.repo_course = repo_course;
    }

    @RequestMapping(value = "/gradelist", method = RequestMethod.GET)
    public String displayGradeList(@RequestParam(required = false) String x,
            @RequestParam(required = false) String y, @RequestParam(required = false) String z,
            @RequestParam(required = false) String act, Model model) {

        int gradeId = -1;
        int studentId = -1;
        int courseId = -1;
        String action = "none";
        if (x != null) {
            gradeId = Integer.parseInt(x);
        }
        if (y != null) {
            studentId = Integer.parseInt(y);
        }
        if (z != null) {
            courseId = Integer.parseInt(z);
        }
        if (act != null) {
            action = act;
        }

        if (action.equals("delete") && gradeId != -1) {
            try {
                repo_grade.deleteById(Long.valueOf(gradeId));
            } catch(Exception e) {
                log.info(e.toString());
            }
        }

        if (action.equals("modify") && gradeId != -1 && studentId != -1 && courseId != -1) {
            Student s = repo_student.findById(Long.valueOf(studentId)).orElse(null);
            Course c = repo_course.findById(Long.valueOf(courseId)).orElse(null);
            Grade g = repo_grade.findById(Long.valueOf(gradeId)).orElse(null);

            Iterable<Student> students = repo_student.findAll();
            Iterable<Course> courses = repo_course.findAll();

            model.addAttribute("gradeToModify", g);
            model.addAttribute("c", c);
            model.addAttribute("s", s);

            model.addAttribute("sl", students);
            model.addAttribute("cl", courses);

            return "modifygrade";
        }

        Iterable<Grade> grades = repo_grade.findAll();
        model.addAttribute("gl", grades);
        return "gradeList";
    }

    @RequestMapping(value = "/gradelist", method = RequestMethod.POST)
    public String addGrade(@RequestParam String studentChoice, @RequestParam String courseChoice,
            @RequestParam int mark, Model model) {
        Long studentId = Long.valueOf(Integer.parseInt(studentChoice));
        Long courseId =  Long.valueOf(Integer.parseInt(courseChoice));

        Student s = repo_student.findById(studentId).orElse(null);
        Course c = repo_course.findById(courseId).orElse(null);

        Grade g = new Grade(s, c , mark);

        repo_grade.save(g);

        Iterable<Grade> grades = repo_grade.findAll();
        model.addAttribute("gl", grades);

        return "gradeList";
    }

}
