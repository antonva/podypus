package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Wrappers.SearchQuery;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchResult;
import is.hi.hbv501g.team21.Podypus.Services.PodcastService;
import is.hi.hbv501g.team21.Podypus.Services.RssService;
import is.hi.hbv501g.team21.Podypus.Services.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SearchController {
    private SearchService searchService;
    private RssService rssService;
    private PodcastService podcastService;

    public SearchController(PodcastService podcastService, SearchService searchService, RssService rssService) {
        this.searchService = searchService;
        this.podcastService = podcastService;
        this.rssService = rssService;
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResult> searchForPodcast(@Valid @RequestBody SearchQuery query, BindingResult result, Model model) {
        SearchResult s = new SearchResult();

        if (result.hasErrors()) {
            //TODO: Better error handling.
            System.out.println("Error in search.");
        } else {
            s = searchService.searchByTitle(query.getTerm());
        }
        return ResponseEntity.ok().body(s);
    }
}
