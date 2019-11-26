package is.hi.hbv501g.team21.Podypus.Services.Implementations;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.UserEpisode;
import is.hi.hbv501g.team21.Podypus.Persistences.Repositories.PodcastRepository;
import is.hi.hbv501g.team21.Podypus.Services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PodcastServiceImplementation implements PodcastService {

        private PodcastRepository podcastRepository;

    @Autowired
    public PodcastServiceImplementation(PodcastRepository podcastRepository) {

        this.podcastRepository = podcastRepository;
    }

    @Override
    public Channel save(Channel channel) {

        return podcastRepository.save(channel);
    }

    @Override
    public void delete(Channel channel) {

        podcastRepository.delete(channel);
    }

    @Override
    public List<Channel> findAll() {

        return podcastRepository.findAll();
    }

    @Override
    public Optional<Channel> findById(long id) {

        return podcastRepository.findById(id);
    }

    @Override
    public Channel findByTitle(String title) {

        return podcastRepository.findByTitle(title);
    }

    @Override
    public UserEpisode getUserEpisodeById(User u, Long episode_id) {
        return podcastRepository.findByEpisodeId(episode_id, u.getUser_id());
    }

    @Override
    public void toggleUserEpisodePlayed(User u, Long episode_id) {
        UserEpisode ue = this.getUserEpisodeById(u, episode_id);
        ue.setPlayed(!ue.isPlayed());
    }

    @Override
    public void updatePlaybackPosition(User u, Long episode_id) {
        UserEpisode ue = this.getUserEpisodeById(u, episode_id);

    }

    @Override
    public int getPlaybackPosition(User u, Long episode_id) {
        UserEpisode ue = this.getUserEpisodeById(u, episode_id);
        return ue.getPlaybackPosition();
    }
}
