package is.hi.hbv501g.team21.Podypus.Services;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Podcast;

import java.util.List;
import java.util.Optional;

public interface PodcastService {
    Podcast save(Podcast podcast);
    void delete(Podcast podcast);
    List<Podcast> findAll();
    Optional<Podcast> findById(long id);
    List<Podcast> findByTitle(String title);
}
