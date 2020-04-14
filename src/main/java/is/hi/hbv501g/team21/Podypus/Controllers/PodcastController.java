package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.*;
import is.hi.hbv501g.team21.Podypus.Persistences.Wrappers.*;
import is.hi.hbv501g.team21.Podypus.Services.PodcastService;
import is.hi.hbv501g.team21.Podypus.Services.RssService;
import is.hi.hbv501g.team21.Podypus.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok().body("{'ok': 'nothing to see here, move along'}");
    }

    // Returns the user's episodes for a specified channel id
    @PostMapping("/episodes")
    public  ResponseEntity<Map<Long, EpisodeResponse>> channel(@RequestBody ChannelId channel_id) {
        boolean authenticated = userService.isAuthenticated(channel_id.getUsername());
        if (authenticated) {
            User u = userService.findByUsername(channel_id.getUsername());
            Optional<Channel> oc = podcastService.findById(channel_id.getChannel_id());
            List<EpisodeResponse> ue = new ArrayList<>();
            Map<Long, EpisodeResponse> m = new HashMap<Long, EpisodeResponse>();
            if (oc.isPresent()) {
                for (UserEpisode e: podcastService.getUserEpisodesByChannelId(u, channel_id.getChannel_id())) {
                    System.out.println(e.getEpisode().getTitle());
                    ue.add(new EpisodeResponse(e));
                }
                return ResponseEntity.ok().body(m);
            }
        }
        //TODO: Return a bogus error message
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/subscriptions")
    public ResponseEntity<List<ChannelResponse>> listSubscribedChannels(@RequestBody String user) {
        System.out.println(user);
        boolean authenticated = userService.isAuthenticated(user);
        if (authenticated) {
            User u  = userService.findByUsername(user);
            List<ChannelResponse> lc = new ArrayList<>();
            //TODO: Refactor to simpler channel list without UserEpisodes et al
            //TODO: I.e. make it more json/android friendly
            for (Channel c: u.getChannels()) {
                lc.add(new ChannelResponse(c));
            }
            return ResponseEntity.ok().body(lc);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody SubscribeUrl s){
        boolean authenticated = userService.isAuthenticated(s.getUsername());
        if (authenticated) {
            User u = userService.findByUsername(s.getUsername());
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
                return ResponseEntity.ok().body("{'ok': 'subscribed to channel}\n");
            }
        }
        return ResponseEntity.badRequest().body("{'error': 'not authenticated'}\n");
    }

    /* Update the playback position on a specific episode id */
    @PostMapping("/update-playback-pos")
    public ResponseEntity<String> updatePlaybackPosition(@Valid @RequestBody EpisodeWrapper e){
        boolean authenticated = userService.isAuthenticated(e.getUsername());
        if (authenticated) {
            User u = userService.findByUsername(e.getUsername());
            if (u != null) {
                podcastService.updatePlaybackPosition(u, e.getId(), e.getPos());
                if (e.isEnded()) {
                    podcastService.setUserEpisodePlayed(u, e.getId());
                    podcastService.updatePlaybackPosition(u, e.getId(), 0);
                }
                return ResponseEntity.ok().body("{'ok': 'updated'}\n");
            }
        }
        return ResponseEntity.badRequest().body("{'error': 'unauthenticated'}\n");
    }

    /* Get the playback position on a specific episode id */
    @PostMapping("/get-playback-pos")
    public ResponseEntity<String> getPlaybackPosition(@Valid @RequestBody EpisodeWrapper e){
        boolean authenticated = userService.isAuthenticated(e.getUsername());
        if (authenticated) {
            User u = userService.findByUsername(e.getUsername());
            if (u != null) {
                float pos = podcastService.getPlaybackPosition(u, e.getId());
                return ResponseEntity.ok().body("{'ok': {'pos': " + pos + "}\n");
            }
        }
        return ResponseEntity.badRequest().body("{'error': 'unauthenticated'}\n");
    }
}
