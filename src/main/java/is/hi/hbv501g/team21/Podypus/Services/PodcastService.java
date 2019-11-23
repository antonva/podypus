package is.hi.hbv501g.team21.Podypus.Services;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PodcastService {
    Channel save(Channel channel);
    void delete(Channel channel);
    List<Channel> findAll();
    Optional<Channel> findById(long id);
    List<Channel> findByTitle(String title);
    boolean saveChannel(User user, Channel channel);
}
