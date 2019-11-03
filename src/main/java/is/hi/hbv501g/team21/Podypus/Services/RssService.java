package is.hi.hbv501g.team21.Podypus.Services;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Podcast;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchItem;

import java.util.List;

public interface RssService {
    public Podcast parseFeed(String feedUrl);

    public List<Podcast> parseManyFeeds(List<SearchItem> si);
}
