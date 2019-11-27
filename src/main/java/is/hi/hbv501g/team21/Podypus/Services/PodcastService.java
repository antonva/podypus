package is.hi.hbv501g.team21.Podypus.Services;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.UserEpisode;

import java.util.List;
import java.util.Optional;

public interface PodcastService {
    Channel save(Channel channel);
    void delete(Channel channel);
    List<Channel> findAll();
    Optional<Channel> findById(long id);
    Channel findByTitle(String title);
    UserEpisode getUserEpisodeById(User u, Long episode_id);
    void toggleUserEpisodePlayed(User u, Long episode_id);
    void updatePlaybackPosition(User u, Long episode_id);
    int getPlaybackPosition(User u, Long episode_id);
}
