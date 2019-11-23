package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.*;
import is.hi.hbv501g.team21.Podypus.Services.PodcastService;
import is.hi.hbv501g.team21.Podypus.Services.RssService;
import is.hi.hbv501g.team21.Podypus.Services.SearchService;
import is.hi.hbv501g.team21.Podypus.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PodcastController {

    private RssService rssService;
    private UserService userService;
    private PodcastService podcastService;
    private List<Channel> p;

    @Autowired
    public PodcastController(RssService rssService,
                             UserService userService,
                             PodcastService podcastService) {
        this.rssService = rssService;
        this.userService = userService;
        this.podcastService = podcastService;
        this.p = new ArrayList<>();
    }

    @RequestMapping(value = "/feed", method = RequestMethod.GET)
    public String feedHandler(Model model) {
        model.addAttribute("query", new SearchQuery());
        return "Feed";
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public @ResponseBody String addPodcast(@Valid @RequestBody SubscribeUrl s, HttpServletRequest request,
                                                 BindingResult result, Model model) {
        Cookie[] clist = request.getCookies();
        if (clist != null && clist.length > 0) {
            Cookie c  = clist[0];
            Channel channel = rssService.parseFeed(s.getUrl());
            if (podcastService.findByTitle(channel.getTitle()).size() > 0) {
                // TODO handle if podcast channel exists already
            } else {
                podcastService.save(channel);
            }
            User u = userService.findByUsername(c.getValue());
            u.setChannels(channel);
            userService.save(u);
            return "{\"success\":1}";
        }
        return "{\"success\":0}";
    }

}
