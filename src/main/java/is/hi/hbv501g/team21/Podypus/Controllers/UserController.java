package is.hi.hbv501g.team21.Podypus.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;

@Controller
public class UserController {
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "Login";
    }
    /*@RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginSubmit(@ModelAttribute User userInfo, Model model) {
        model.addAttribute("user", userInfo);
        return "InfoResult";
    }*/
}

//TODO ViewUserProfile Method