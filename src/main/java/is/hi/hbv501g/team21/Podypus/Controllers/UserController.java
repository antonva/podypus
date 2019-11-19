package is.hi.hbv501g.team21.Podypus.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    /*@RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "Login";
    }*/

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpGET(User user){
        return "fragments/Login :: signup";
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public String signUpPOST(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "Login";
        }
        User exists = userService.findByEmail(user.getEmail()); //skilar user object fyrir notanda sem er nú þegar til
        if (exists == null) {
            userService.save(user);
            return "redirect:/login";
        }
        if (exists != null) {
        }
        return "fragments/Login :: userExists";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(Model model){
        model.addAttribute("user", new User());
        return "fragments/Login :: login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@Valid @ModelAttribute User user, BindingResult result, Model model, HttpSession session){
        if (result.hasErrors()) {
            return "Login";
        }

        User exists = userService.loginUser(user);
        if (exists != null) {
            session.setAttribute("LoggedInUser", exists);
            return "redirect:/login/profile";
        }
        return "redirect:/login";
    }
    //birta profile fyrir notanda sem er loggaður inn
    @RequestMapping(value = "/login/profile", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if (sessionUser != null) {
            model.addAttribute("loggedinuser", sessionUser);
            return "UserProfile";
        }
        return "redirect:/";
    }
    //aðferð til að testa signup
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usersGET(Model model){
        model.addAttribute("users", userService.findAll());
        return "Users";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPOST(HttpSession session) {
        session.invalidate();
        return "/";
    }
    @RequestMapping(value = "/login/changePassword", method = RequestMethod.GET)
    public String changePasswordGET(User user){
        return "fragments/Login :: changePassword";
    }
    @RequestMapping(value = "/login/changePassword", method = RequestMethod.POST)
    public String changePassword(String email, String newpassword) {
        User exists = userService.findByEmail(email); //returns a User object with that e-mail address that has been entered.
        if (exists != null) {
            userService.resetPassword(exists.getEmail(), newpassword);
            return "fragments/Login :: login";
        }
        else return "fragments/Login :: notExists";
    }
}

