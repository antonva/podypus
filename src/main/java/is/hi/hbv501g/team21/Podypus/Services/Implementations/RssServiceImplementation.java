package is.hi.hbv501g.team21.Podypus.Services.Implementations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Podcast;
import is.hi.hbv501g.team21.Podypus.Services.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = null;
                dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(String.valueOf(response));
                doc.getDocumentElement().normalize();
                System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
                NodeList nList = doc.getElementsByTagName("channel");
                System.out.println("----------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Podcast();
    }
}
