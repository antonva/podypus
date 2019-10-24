package is.hi.hbv501g.team21.Podypus.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.ItunesSearchResult;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchQuery;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.ItunesSearchItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;

@Controller
public class SimpleFeedController {

    //TODO: This needs to be part of a generic search service.
    private static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException x) {
            throw new RuntimeException(x.getCause());
        }
    }

    private static ItunesSearchResult searchItunes(String querystring) throws IOException {
        URL url = new URL(querystring);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        if (status == 200) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            con.disconnect();
            ObjectMapper mapper = new ObjectMapper();
            ItunesSearchResult sr = mapper.readValue(content.toString(), ItunesSearchResult.class);
            return sr;
        }
        else {
            return new ItunesSearchResult();
        }
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
            //TODO: This needs to be part of a iTunes specific search service.
            String ITUNES_SEARCH_URL = "https://itunes.apple.com/search?entity=podcast&term=";
            ItunesSearchResult s = this.searchItunes(ITUNES_SEARCH_URL + urlEncode(query.getTerm()));
            model.addAttribute("results", s);
        }
        return "Feed";
    }

}
