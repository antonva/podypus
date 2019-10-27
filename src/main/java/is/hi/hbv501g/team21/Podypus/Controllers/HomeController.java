package is.hi.hbv501g.team21.Podypus.Controllers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Podcast;
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
        return "Home";
    }

    @RequestMapping(value = "/addpodcast", method = RequestMethod.GET)
    public String addPodcastForm(Model model) {
        model.addAttribute("podcast", new Podcast());
        return "add-podcast";
    }

    @RequestMapping(value = "/addpodcast", method = RequestMethod.POST)
    public String addPodcast(@Valid @ModelAttribute("podcast") Podcast podcast, BindingResult result, Model model) {
        if (result.hasErrors()) {
            //model.addAttribute("error");
            return "add-podcast";
        }

        model.addAttribute("podcast", podcast);
        podcastService.save(podcast);
        model.addAttribute("podcasts", podcastService.findAll());
        return "Home";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePodcast(@PathVariable("id") long id, Model model) {
        Podcast podcast = podcastService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Podcast ID")
        );
        podcastService.delete(podcast);

        model.addAttribute("podcasts", podcastService.findAll());
        return "Home";
    }
}

