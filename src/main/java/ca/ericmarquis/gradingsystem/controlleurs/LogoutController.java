package ca.ericmarquis.gradingsystem.controlleurs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(Model model) {
        model.addAttribute("msg", "Merci de votre visite!");
        return "logout";
    }
}
