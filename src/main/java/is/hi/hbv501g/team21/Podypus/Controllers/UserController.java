package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Wrappers.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import is.hi.hbv501g.team21.Podypus.Services.UserService;

import javax.servlet.http.Cookie;
import java.net.URISyntaxException;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        User exists = userService.findByEmail(user.getEmail()); //skilar user object fyrir notanda sem er nú þegar til
        User uname = userService.findByUsername(user.getUsername());
        if (uname != null) {
            return ResponseEntity.badRequest().body("{'error': 'user exists'}\n");
        }
        if (exists == null) {
            userService.save(user);
            return ResponseEntity.ok().body("{'ok': 'signup successful'}\n");
        }
        if (exists != null) {
            return ResponseEntity.badRequest().body("{'error': 'user exists'}\n");
        }
        return ResponseEntity.badRequest().body("{'error': 'something went wrong'}\n");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm lf) throws URISyntaxException {
        System.out.println(lf.getUsername());
        User loggedInUser = userService.loginUser(lf);
        if (loggedInUser == null) {
            return ResponseEntity.badRequest().body("{'error': 'user does not exist or wrong password'}\n");
        } else {
            Cookie c = new Cookie("user", loggedInUser.getUsername());
            //response.addCookie(c);
            return ResponseEntity.ok(loggedInUser.getUsername());

        }
    }

    //TODO:
    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.badRequest().body("{'error': 'not implemented yet'}\n");
    }

    //TODO:
    @RequestMapping(value = "/login/changePassword", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute("user") User user) {
        System.out.println("in changePassword");
        User exists = userService.findByEmail(user.getEmail()); //returns a User object with that e-mail address that has been entered.
        if (exists != null) {
            userService.resetPassword(exists.getEmail(), user.getPassword());
            return "redirect:/";
        }
        else return "fragments/Login :: notExists";
    }
}

