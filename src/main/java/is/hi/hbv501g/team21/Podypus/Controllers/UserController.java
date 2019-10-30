package is.hi.hbv501g.team21.Podypus.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import is.hi.hbv501g.team21.Podypus.Services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "Login";
    }

    @RequestMapping(value = "/login/signup", method = RequestMethod.GET)
    public String signUpGET(User user){
        return "Login";
    }

    @RequestMapping(value="/login/signup", method = RequestMethod.POST)
    public String signUpPOST(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Login";
        }
        User exists = userService.findByEmail(user.getEmail()); //skilar user object fyrir notanda sem er nú þegar til
        if (exists != null) {
            userService.save(user);
        }
        return "Login";
        //TODO birta villuskilaboð um að það sé til notandi með þetta e-mail.
    }

    @RequestMapping(value = "/login/get-user", method = RequestMethod.GET)
    public String loginGET(User user){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@Valid User user, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "Login";
        }
        //model.addAttribute("users", userService.findAll());
        User exists = userService.loginUser(user);
        if (exists != null){
            session.setAttribute("LoggedInUser", user);
            return "redirect:/";
        }
        return "redirect:/";
    }
    //birta profile fyrir notanda sem er loggaður inn
    @RequestMapping(value = "/login/profile", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model){
        //model.addAttribute("users", userService.findAll());
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if(sessionUser  != null){
            model.addAttribute("loggedinuser", sessionUser);
            return "UserProfile";
        }
        return "redirect:/";
    }
    /*@RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginSubmit(@ModelAttribute User userInfo, Model model) {
        model.addAttribute("user", userInfo);
        return "InfoResult";
    }*/
}

//TODO ViewUserProfile Method