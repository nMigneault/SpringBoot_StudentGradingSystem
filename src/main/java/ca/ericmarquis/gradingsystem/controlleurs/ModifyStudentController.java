package ca.ericmarquis.gradingsystem.controlleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ericmarquis.gradingsystem.donnees.StudentRepository;
import ca.ericmarquis.gradingsystem.modeles.Student;

@Controller
public class ModifyStudentController {
    private final StudentRepository repo_student;

    @Autowired
    public ModifyStudentController(StudentRepository repo_student) {
        this.repo_student = repo_student;
    }

    @RequestMapping(value = "/modifystudent", method = RequestMethod.POST)
    public String modifyStudent(@RequestParam String studentId, @RequestParam String firstName,
                                @RequestParam String lastName, @RequestParam String address,
                                @RequestParam String city, Model model) {
        Student s = repo_student.findById(Long.valueOf(studentId)).orElse(null);
        s.setFirstName(firstName);
        s.setLastName(lastName);
        s.setAddress(address);
        s.setCity(city);
        repo_student.save(s);

        Iterable<Student> students = repo_student.findAll();
        model.addAttribute("sl", students);
        
        return "studentList";
    }
}