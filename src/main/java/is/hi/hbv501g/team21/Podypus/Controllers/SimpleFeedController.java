package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchQuery;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchResult;
import is.hi.hbv501g.team21.Podypus.Services.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class SimpleFeedController {

    private SearchService searchService;

    public SimpleFeedController(SearchService searchService) {
        this.searchService = searchService;
    }
    @RequestMapping(value="/feed", method = RequestMethod.GET)
    public String feedHandler(Model model) {
        model.addAttribute("query", new SearchQuery());
        return "Feed";
    }

    @RequestMapping(value = "/feed", method = RequestMethod.POST)
    public String feedSubmit(@Valid SearchQuery query, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            System.out.println("Oh nooeees");
        }
        else {
            model.addAttribute("query", query);
            SearchResult s = searchService.searchByTitle(query.getTerm());
            model.addAttribute("results", s);
        }
        return "Feed";
    }

}
