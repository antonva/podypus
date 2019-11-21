package is.hi.hbv501g.team21.Podypus.Services.Implementations;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Rss;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchItem;
import is.hi.hbv501g.team21.Podypus.Services.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssServiceImplementation implements RssService {

    @Autowired
    public RssServiceImplementation() {
        super();
    }

    public Channel parseFeed(String feedUrl) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(feedUrl, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                StringReader rdr = new StringReader(response.getBody());
                JAXBContext ctx = JAXBContext.newInstance(Rss.class);
                Unmarshaller unmarshaller = ctx.createUnmarshaller();
                //unmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
                Rss r = (Rss) unmarshaller.unmarshal(rdr);

                //Save the entities to db
                
                return r.channel;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Channel();
    }

    public List<Channel> parseManyFeeds(List<SearchItem> si) {
        List<Channel> pl = new ArrayList<>();
        for (SearchItem search : si) {
            Channel p = parseFeed(search.getFeedUrl());
            pl.add(p);
        }
        return pl;
    }
}
