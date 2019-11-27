package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.*;
import is.hi.hbv501g.team21.Podypus.Persistences.Wrappers.ChannelId;
import is.hi.hbv501g.team21.Podypus.Persistences.Wrappers.EpisodeWrapper;
import is.hi.hbv501g.team21.Podypus.Persistences.Wrappers.LoginForm;
import is.hi.hbv501g.team21.Podypus.Persistences.Wrappers.SubscribeUrl;
import is.hi.hbv501g.team21.Podypus.Services.PodcastService;
import is.hi.hbv501g.team21.Podypus.Services.RssService;
import is.hi.hbv501g.team21.Podypus.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @RequestMapping("/")
    public String Home(Model model, HttpServletRequest request) {
        boolean authenticated = userService.isAuthenticated(request);
        if (authenticated) {
            User u  = userService.findByUsername(request.getCookies()[0].getValue());
            List<Channel> lc = new ArrayList<>();
            lc.addAll(u.getChannels());
            model.addAttribute("chanlist", lc);
        }
        model.addAttribute("loginform", new LoginForm());
        model.addAttribute("user", new User());
        model.addAttribute("authenticated", authenticated);
        return "Index";
    }

    @RequestMapping(value = "/channel", method = RequestMethod.POST)
    public @ResponseBody ModelAndView channelDetails(@Valid @RequestBody ChannelId channel_id, HttpServletRequest request) {
        boolean authenticated = userService.isAuthenticated(request);
        if (authenticated) {
            ModelAndView mav = new ModelAndView("fragments/Channel.html :: channelDetails");
            User u = userService.getUserFromCookie(request);
            List<UserEpisode> ue = podcastService.getUserEpisodesByChannelId(u, channel_id.getChannel_id());
            Optional<Channel> oc = podcastService.findById(channel_id.getChannel_id());
            if (oc.isPresent()) {

                mav.addObject("channel",oc.get());
                mav.addObject("usereps",ue);
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
            List<Channel> lc = new ArrayList<>();
            lc.addAll(u.getChannels());
            mav.addObject("chanlist", lc);
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
            User u = userService.getUserFromCookie(request);
            if (u != null) {
                Channel channel = rssService.parseFeed(s.getUrl());
                Channel existingChannel = podcastService.findByTitle(channel.getTitle());
                // Handle Existing Podcasts
                if ( existingChannel != null) {
                    u.setChannels(existingChannel);
                    for (Episode episode: existingChannel.getEpisodeList()) {
                        u.addEpisode(episode, existingChannel);
                    }
                } else {
                    podcastService.save(channel);
                    u.setChannels(channel);
                    for (Episode episode: channel.getEpisodeList()) {
                        u.addEpisode(episode, channel);
                    }
                }
                userService.save(u);
                return "{\"success\":1}";
            }
        }
        return "{\"success\":0}";
    }

    /* Update the playback position on a specific episode id */
    @RequestMapping(value = "/update-playback-pos", method = RequestMethod.POST)
    public @ResponseBody String updatePlaybackPosition(@Valid @RequestBody EpisodeWrapper e, HttpServletRequest request) {
        boolean authenticated = userService.isAuthenticated(request);
        if (authenticated) {
            User u = userService.getUserFromCookie(request);
            if (u != null) {
                podcastService.updatePlaybackPosition(u, e.getId(), e.getPos());
                if (e.isEnded()) {
                    podcastService.setUserEpisodePlayed(u, e.getId());
                    podcastService.updatePlaybackPosition(u, e.getId(), 0);
                }
                return "{\"success\":1}";
            }
        }
        return "{\"success\":0}";
    }

    /* Update the playback position on a specific episode id */
    @RequestMapping(value = "/get-playback-pos", method = RequestMethod.POST)
    public @ResponseBody String getPlaybackPosition(@Valid @RequestBody EpisodeWrapper e, HttpServletRequest request) {
        boolean authenticated = userService.isAuthenticated(request);
        if (authenticated) {
            User u = userService.getUserFromCookie(request);
            if (u != null) {
                float pos = podcastService.getPlaybackPosition(u, e.getId());
                return "{\"success\": 1, \"pos\": " + pos + "}";
            }
        }
        return "{\"success\":0}";
    }
}
