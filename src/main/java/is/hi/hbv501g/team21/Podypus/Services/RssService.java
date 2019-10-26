package is.hi.hbv501g.team21.Podypus.Services;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Podcast;

public interface RssService {
    public Podcast parseFeed(String feedUrl);
}
