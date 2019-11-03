package is.hi.hbv501g.team21.Podypus.Services.Implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchResult;
import is.hi.hbv501g.team21.Podypus.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ItunesSearchServiceImplementation implements SearchService {

    @Autowired
    public ItunesSearchServiceImplementation() {
       super();
    }

    @Override
    public SearchResult searchByTitle(String query) {
        String attribute = "&attribute=titleTerm";
        try {
            SearchResult isr = this.searchItunes(query, attribute);
            return isr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new SearchResult();
    }

    @Override
    public SearchResult searchByCategory(String query) {
        String attribute = "&attribute=genreIndex";
        //TODO: Map iTunes podcast genres down to an enum and do a string conversion.
        return null;
    }

    @Override
    public SearchResult searchByArtist(String query) {
        String attribute = "&attribute=artistTerm";
        try {
            SearchResult isr = this.searchItunes(query, attribute);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String itunesUrlEncode(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException x) {
            throw new RuntimeException(x.getCause());
        }
    }

    private SearchResult searchItunes(String querystring, String attribute) throws IOException {
        String ITUNES_SEARCH_URL = "https://itunes.apple.com/search?entity=podcast&term=";
        querystring = ITUNES_SEARCH_URL + this.itunesUrlEncode(querystring) + attribute;
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
            SearchResult sr = mapper.readValue(content.toString(), SearchResult.class);
            return sr;
        }
        else {
            return new SearchResult();
        }
    }
}
