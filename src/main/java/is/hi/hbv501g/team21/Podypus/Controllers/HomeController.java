package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class HomeController {

    private PodcastService podcastService;

    @Autowired
    public HomeController(PodcastService podcastService) {
        this.podcastService = podcastService;
    }

    @RequestMapping("/")
    public String Home(Model model) {
        model.addAttribute("podcasts", podcastService.findAll());
        return "Index";
    }

    @RequestMapping(value = "/addpodcast", method = RequestMethod.GET)
    public String addPodcastForm(Model model) {
        model.addAttribute("podcast", new Channel());
        return "add-podcast";
    }

    @RequestMapping(value = "/addpodcast", method = RequestMethod.POST)
    public String addPodcast(@Valid @ModelAttribute("podcast") Channel channel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            //model.addAttribute("error");
            return "add-podcast";
        }

        model.addAttribute("podcast", channel);
        podcastService.save(channel);
        model.addAttribute("podcasts", podcastService.findAll());
        return "Home";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePodcast(@PathVariable("id") long id, Model model) {
        Channel channel = podcastService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Podcast ID")
        );
        podcastService.delete(channel);

        model.addAttribute("podcasts", podcastService.findAll());
        return "Home";
    }

    @RequestMapping("/home")
    public String homeTest(Model model){
        model.addAttribute("podcasts", podcastService.findAll());
        return "Home";
    }
}

