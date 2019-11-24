package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import is.hi.hbv501g.team21.Podypus.Services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpGET(User user){
        return "fragments/Login :: signup";
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public String signUpPOST(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/login";
        }
        User exists = userService.findByEmail(user.getEmail()); //skilar user object fyrir notanda sem er nú þegar til
        User uname = userService.findByUsername(user.getUsername());
        if (uname != null) {
            return "fragments/Login :: unameExists";
        }
        if (exists == null) {
            userService.save(user);
            return "redirect:/";
        }
        if (exists != null) {
            return "fragments/Login :: userExists";
        }
        return "fragments/Login :: login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(Model model, HttpServletRequest request){
        model.addAttribute("user", new User());
        return "fragments/Login :: login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@Valid LoginForm loginForm,
                            BindingResult result,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "redirect:/";
        }

        User loggedInUser = userService.loginUser(loginForm);
        if (loggedInUser != null) {
            Cookie c = new Cookie("user", loggedInUser.getUsername());
            response.addCookie(c);
            return "redirect:/";
        }
        return "redirect:/";
    }

    //birta profile fyrir notanda sem er loggaður inn
    @RequestMapping(value = "/login/profile", method = RequestMethod.GET)
    public String loggedinGET(Model model, HttpServletRequest request) {
        Cookie[] clist = request.getCookies();
        Cookie c = clist[0];
        if (c.getValue() != null) {
            model.addAttribute("loggedinuser", c.getValue());
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
    public String logoutPOST(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] oldcookie = request.getCookies();
        Cookie c = oldcookie[0];
        c.setMaxAge(0);
        response.addCookie(c);

        return "redirect:/";
    }
    @RequestMapping(value = "/login/changePassword", method = RequestMethod.GET)
    //public String changePasswordGET(User user){
    public String changePasswordGET(Model model){
        model.addAttribute("user", new User());
        System.out.println("in changePasswordGET");
        return "fragments/Login :: changePassword";
    }
    @RequestMapping(value = "/login/changePassword", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute("user") User user) {
        System.out.println("in changePassword");
        User exists = userService.findByEmail(user.getEmail()); //returns a User object with that e-mail address that has been entered.
        if (exists != null) {
            System.out.println(exists.getUsername());
            System.out.println(exists.getEmail());
            System.out.println(user.getPassword());
            System.out.println(exists.getPassword());
            userService.resetPassword(exists.getEmail(), user.getPassword());
            return "redirect:/";
        }
        else return "fragments/Login :: notExists";
    }
}

