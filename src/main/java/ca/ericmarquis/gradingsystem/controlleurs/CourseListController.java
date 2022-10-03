package ca.ericmarquis.gradingsystem.controlleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ericmarquis.gradingsystem.donnees.CourseRepository;
import ca.ericmarquis.gradingsystem.modeles.Course;

@Slf4j
@Controller
public class CourseListController {
    private final CourseRepository repo_course;
    //private final TeacherRepository repo_teacher;

    @Autowired
    public CourseListController(CourseRepository repo_course){//}, TeacherRepository repo_teacher) {
        this.repo_course = repo_course;
        //this.repo_teacher = repo_teacher;
    }

    @RequestMapping(value = "/courselist", method = RequestMethod.GET)
    public String displayCourseList(@RequestParam(required = false) String x,
            @RequestParam(required = false) String act, Model model) {
        int id = -1;
        String action = "none";

        if(x != null) 
            id = Integer.parseInt(x);
        if(act != null)
            action = act;

        if(action.equals("delete") && id != -1){
            try {
                repo_course.deleteById(Long.valueOf(id));
            } catch(Exception e) {
                log.info(e.toString());
            }
        }
        
        if(action.equals("modify") && id != -1) {
            Course courseToModify = repo_course.findById(Long.valueOf(id)).orElse(null);
            model.addAttribute("courseToModify", courseToModify);

            return "modifycourse";
        }

        Iterable<Course> courses = repo_course.findAll();
        model.addAttribute("cl", courses);
        return "courseList";
    }

    @RequestMapping(value = "/courselist", method = RequestMethod.POST)
    public String addStudent(@RequestParam String courseName, @RequestParam int numCredits,
             Model model) {

            Course c = new Course(courseName, numCredits);
            repo_course.save(c);

        Iterable<Course> courses = repo_course.findAll();
        model.addAttribute("cl", courses);

        return "courseList";
    }
}
