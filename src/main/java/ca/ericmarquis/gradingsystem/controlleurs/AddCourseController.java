package ca.ericmarquis.gradingsystem.controlleurs;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AddCourseController {

    @RequestMapping(value="/addcourse", method=RequestMethod.GET)
    public String addCoursePage() {
        return "addcourse";
    }
}