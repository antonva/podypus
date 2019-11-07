package is.hi.hbv501g.team21.Podypus.Persistences.Repositories;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PodcastRepository extends JpaRepository<Channel, Long> {

    Channel save(Channel channel);
    void delete(Channel channel);
    List<Channel> findAll();
    List<Channel> findByTitle(String title);
    Optional<Channel> findById(long id);
}
