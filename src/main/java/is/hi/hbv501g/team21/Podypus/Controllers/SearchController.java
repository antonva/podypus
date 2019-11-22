package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchQuery;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchResult;
import is.hi.hbv501g.team21.Podypus.Services.RssService;
import is.hi.hbv501g.team21.Podypus.Services.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

    private SearchService searchService;
    private RssService rssService;

    public SearchController(SearchService searchService, RssService rssService) {
        this.searchService = searchService;
        this.rssService = rssService;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchForPodcast(Model model) {
        return "fragments/Search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody ModelAndView searchForPodcast(@Valid @RequestBody SearchQuery query, BindingResult result, Model model) {
        ModelAndView mav = new ModelAndView("fragments/Search :: searchResults");
        SearchResult s = new SearchResult();

        if (result.hasErrors()) {
            //TODO: Better error handling.
            @Controller
            public class SearchController {
            System.out.println("Error in search.");
        } else {
            s = searchService.searchByTitle(query.getTerm());
            List<Channel> p = rssService.parseManyFeeds(s.getResults());
            System.out.println(p.get(0));
            System.out.println(p.get(0).getEpisodeList().get(0));

            mav.addObject("results", s);
            mav.addObject("channel", p.get(0));
        }
        return mav;
    }
}
