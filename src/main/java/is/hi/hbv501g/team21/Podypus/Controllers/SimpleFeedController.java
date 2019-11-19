package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.*;
import is.hi.hbv501g.team21.Podypus.Services.PodcastService;
import is.hi.hbv501g.team21.Podypus.Services.RssService;
import is.hi.hbv501g.team21.Podypus.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SimpleFeedController {

    private SearchService searchService;
    private RssService rssService;
    private PodcastService podcastService;
    private List<Channel> p;

    @Autowired
    public SimpleFeedController(SearchService searchService, RssService rssService,
                                PodcastService podcastService) {
        this.searchService = searchService;
        this.rssService = rssService;
        this.podcastService = podcastService;
        this.p = new ArrayList<>();
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feedHandler(Model model) {
        model.addAttribute("query", new SearchQuery());
        return "Feed";
    }

    @RequestMapping(value = "/feed", method = RequestMethod.POST)
    public String feedSubmit(@Valid SearchQuery query, BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            System.out.println("Oh nooeees");
        } else {
            model.addAttribute("query", query);
            SearchResult s = searchService.searchByTitle(query.getTerm());
            List<SearchItem> si = s.getResults();
            p = rssService.parseManyFeeds(si);
            model.addAttribute("results", s);
            if (!p.isEmpty()) {
                Channel px = p.get(0);
                System.out.println("channel string: "+px.toString());
                if (!p.get(0).getEpisodeList().isEmpty()) {
                    Episode ex = px.getEpisodeList().get(0);
                    System.out.println("Episode string: "+ex.toString());
                }
            }
        }
        return "Feed";
    }

    @RequestMapping(value = "/subscribe/{nr}", method = RequestMethod.GET)
    public String addPodcast(@PathVariable("nr") int nr, Model model) {
        Channel channel = p.get(nr);
        model.addAttribute("podcast", channel);
        podcastService.save(channel);

        model.addAttribute("podcasts", podcastService.findAll());
        return "Home";
    }

}
