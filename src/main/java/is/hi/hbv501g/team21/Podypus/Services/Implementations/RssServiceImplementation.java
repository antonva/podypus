package is.hi.hbv501g.team21.Podypus.Services.Implementations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Podcast;
import is.hi.hbv501g.team21.Podypus.Services.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RssServiceImplementation implements RssService {

    @Autowired
    public RssServiceImplementation() {
        super();
    }

    public Podcast parseFeed(String feedUrl) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(feedUrl, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                StringReader rdr = new StringReader(response.getBody());
                JAXBContext ctx = JAXBContext.newInstance(Podcast.class);
                Unmarshaller umsh = ctx.createUnmarshaller();
                Podcast p = (Podcast) umsh.unmarshal(rdr);
                System.out.println(p.getTitle());
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Podcast();
    }
}
