package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.*;
import is.hi.hbv501g.team21.Podypus.Persistences.Wrappers.ChannelId;
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
import java.util.Optional;

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

    @RequestMapping(value = "/channel", method = RequestMethod.POST)
    public @ResponseBody ModelAndView channelDetails(@Valid @RequestBody ChannelId channel_id, HttpServletRequest request) {
        boolean authenticated = userService.isAuthenticated(request);
        System.out.println(channel_id.getChannel_id());
        if (authenticated) {
            ModelAndView mav = new ModelAndView("fragments/Channel.html :: channelDetails");
            Optional<Channel> ou = podcastService.findById(channel_id.getChannel_id());
            if (ou.isPresent()) {
                mav.addObject("channel", ou.get());
                return mav;
            }
        }
        return null;

    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody ModelAndView listSubscribedChannels(HttpServletRequest request) {
        boolean authenticated = userService.isAuthenticated(request);
        if (authenticated) {
            ModelAndView mav = new ModelAndView("fragments/Channel.html :: channelList");
            User u  = userService.findByUsername(request.getCookies()[0].getValue());
            mav.addObject("chanlist", u.getChannels().toArray());
            return mav;
        }
        return null;
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public @ResponseBody String addPodcast(@Valid @RequestBody SubscribeUrl s, HttpServletRequest request,
                                                 BindingResult result, Model model) {
        boolean authenticated = userService.isAuthenticated(request);
        if (authenticated) {
            Cookie[] clist = request.getCookies();
            if (clist != null && clist.length > 0) {
                Cookie c = clist[0];
                Channel channel = rssService.parseFeed(s.getUrl());
                Channel existingChannel = podcastService.findByTitle(channel.getTitle());
                User u = userService.findByUsername(c.getValue());
                // Handle Existing Podcasts
                if ( existingChannel != null) {
                    u.setChannels(existingChannel);
                    for (Episode episode: existingChannel.getEpisodeList()) {
                        u.addEpisode(episode);
                    }
                } else {
                    podcastService.save(channel);
                    u.setChannels(channel);
                    for (Episode episode: channel.getEpisodeList()) {
                        u.addEpisode(episode);
                    }
                }
                userService.save(u);
                return "{\"success\":1}";
            }
        }
        return "{\"success\":0}";
    }

}
