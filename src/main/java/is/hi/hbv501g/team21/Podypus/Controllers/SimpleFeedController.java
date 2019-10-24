package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class SimpleFeedController {

    //TODO: This needs to be part of a iTunes specific search service.
    private final String ITUNES_SEARCH_URL = "https://itunes.apple.com/search?entity=podcast&term=";
    //TODO: This needs to be part of a generic search service.
    public static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException x) {
            throw new RuntimeException(x.getCause());
        }
    }

    @RequestMapping(value="/feed", method = RequestMethod.GET)
    public String feedHandler(Model model) {
        model.addAttribute("query", new SearchQuery());
        return "Feed";
    }

    @RequestMapping(value = "/feed", method = RequestMethod.POST)
    public String feedSubmit(@Valid SearchQuery query, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Oh nooeees");
        }
        else {
            model.addAttribute("query", query);
            System.out.println(ITUNES_SEARCH_URL + urlEncode(query.getTerm()));
        }
        return "Feed";
    }

}
