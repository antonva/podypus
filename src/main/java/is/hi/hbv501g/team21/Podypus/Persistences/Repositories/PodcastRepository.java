package is.hi.hbv501g.team21.Podypus.Persistences.Repositories;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PodcastRepository extends JpaRepository<Podcast, Long> {

    Podcast save(Podcast podcast);
    void delete(Podcast podcast);
    List<Podcast> findAll();
    List<Podcast> findByTitle(String title);
    Optional<Podcast> findById(long id);
}
