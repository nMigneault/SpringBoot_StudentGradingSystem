package ca.ericmarquis.gradingsystem.controlleurs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddStudentController {

    @RequestMapping(value = "/addstudent", method = RequestMethod.GET)
    public String addStudent() {
        return "addstudent";
    }
}