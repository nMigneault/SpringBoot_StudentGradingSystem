package ca.ericmarquis.gradingsystem.controlleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ericmarquis.gradingsystem.donnees.StudentRepository;
import ca.ericmarquis.gradingsystem.modeles.Student;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StudentListController {

    private final StudentRepository repo_student;

    @Autowired
    public StudentListController(StudentRepository repo_student) {
        this.repo_student = repo_student;
    }

    @RequestMapping(value = "/studentlist", method = RequestMethod.GET)
    public String displayStudentList(@RequestParam(required = false) String x, 
                                     @RequestParam(required = false) String act, Model model) {
        int id = -1;
        String action = "none";

        if (x != null) id = Integer.parseInt(x);
        if (act != null) action = act;

        if (action.equals("delete") && id != -1) {
            try {
                repo_student.deleteById(Long.valueOf(id));
            } catch(Exception e) {
                log.info(e.toString());
            }
        }

        if (action.equals("modify") && id != -1) {
            Student studentToModify = repo_student.findById(Long.valueOf(id)).orElse(null);
            model.addAttribute("studentToModify", studentToModify);
            return "modifystudent";
        }

        Iterable<Student> students = repo_student.findAll();
        model.addAttribute("sl", students);

        return "studentList";
    }

    @RequestMapping(value = "/studentlist", method = RequestMethod.POST)
    public String addStudent(@RequestParam String firstName, @RequestParam String lastName, 
                             @RequestParam String address, @RequestParam String city, Model model) {
        Student s = new Student(firstName, lastName, address, city);
        repo_student.save(s);

        Iterable<Student> students = repo_student.findAll();
        model.addAttribute("sl", students);

        return "studentList";
    }
                             
                             

    
}
