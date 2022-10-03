package ca.ericmarquis.gradingsystem.controlleurs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String welcomePage(@RequestParam String username, @RequestParam String password, Model model) {
        if (username.equals("admin") && password.equals("admin")) {
            return "menu";
        } else {
            model.addAttribute("msg", "Mauvais mot de passe");
            return "login";
        }
    }

    @RequestMapping(value="/logout")
    public String logoutPage() {
        //model.addAttribute("msg", "Merci de votre visite!");
        return "logout";
    }

}
