package is.hi.hbv501g.team21.Podypus.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    /*@RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "Login";
    }*/

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signUpGET(User user){
        return "Login";
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public String signUpPOST(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "Login";
        }
        User exists = userService.findByEmail(user.getEmail()); //skilar user object fyrir notanda sem er nú þegar til
        if (exists == null) {
            userService.save(user);
            return "Home";
        }
        if (exists != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        return "Login";
        //TODO birta villuskilaboð um að það sé til notandi með þetta e-mail.
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(Model model){
        model.addAttribute("user", new User());
        return "fragments/Login :: login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@Valid User user,
                            BindingResult result,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response){
        if (result.hasErrors()) {
            return "Login";
        }

        boolean success = userService.loginUser(user);
        if (success) {
            Cookie c = new Cookie("user", user.getUsername());
            response.addCookie(c);
            return "redirect:/login/profile";
        }
        return "redirect:/login";
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
}
//TODO: logout - button on profile page
//TODO: get username on user/profile page
