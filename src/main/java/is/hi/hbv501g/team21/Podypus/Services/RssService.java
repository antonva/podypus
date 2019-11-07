package is.hi.hbv501g.team21.Podypus.Services;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchItem;

import java.util.List;

public interface RssService {
    public Channel parseFeed(String feedUrl);

    public List<Channel> parseManyFeeds(List<SearchItem> si);
}
