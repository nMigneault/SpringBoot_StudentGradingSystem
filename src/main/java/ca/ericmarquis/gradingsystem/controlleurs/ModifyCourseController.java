package ca.ericmarquis.gradingsystem.controlleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ericmarquis.gradingsystem.donnees.CourseRepository;
import ca.ericmarquis.gradingsystem.modeles.Course;

@Controller
public class ModifyCourseController {
    private final CourseRepository repo_course;


    @Autowired
    public ModifyCourseController(CourseRepository repo_course) {
        this.repo_course = repo_course;
    }

    @RequestMapping(value = "/modifycourse", method = RequestMethod.POST)
    public String modifyCourse(@RequestParam String courseId, @RequestParam String courseName, @RequestParam int creditNumber,
                                Model model) {


        Course course = new Course(Long.valueOf(courseId), courseName, creditNumber);
        repo_course.save(course);

        Iterable<Course> courses = repo_course.findAll();
        model.addAttribute("cl", courses);
        
        return "courseList";
    }
}
