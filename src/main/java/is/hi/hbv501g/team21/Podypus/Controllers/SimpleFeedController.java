package is.hi.hbv501g.team21.Podypus.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SimpleFeedController {

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feedHandler(Model model) {
        return "Feed";
    }

    @RequestMapping(value = "/feed", method = RequestMethod.POST)
    public String feedHandler(@Valid String url, BindingResult result, Model model) {
        return "Feed";
    }

}
