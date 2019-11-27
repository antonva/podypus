package is.hi.hbv501g.team21.Podypus.Services.Implementations;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.UserEpisode;
import is.hi.hbv501g.team21.Podypus.Persistences.Repositories.PodcastRepository;
import is.hi.hbv501g.team21.Podypus.Persistences.Repositories.UserEpisodeRepository;
import is.hi.hbv501g.team21.Podypus.Services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PodcastServiceImplementation implements PodcastService {

    private PodcastRepository podcastRepository;
    private UserEpisodeRepository userEpisodeRepository;

    @Autowired
    public PodcastServiceImplementation(PodcastRepository podcastRepository,
                                        UserEpisodeRepository userEpisodeRepository) {

        this.podcastRepository = podcastRepository;
        this.userEpisodeRepository = userEpisodeRepository;
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
    public List<UserEpisode> getUserEpisodesByChannelId(User u, Long c) {
        return podcastRepository.findByChannelId(c, u.getUser_id());
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
    public void updatePlaybackPosition(User u, Long episode_id, int pos) {
        UserEpisode ue = this.getUserEpisodeById(u, episode_id);
        ue.setPlaybackPosition(pos);
        userEpisodeRepository.save(ue);

    }

    @Override
    public int getPlaybackPosition(User u, Long episode_id) {
        UserEpisode ue = this.getUserEpisodeById(u, episode_id);
        Long c_id = podcastRepository.findChannelIdByEpisodeId((long) 325);
        List<UserEpisode> userEpisodes = podcastRepository.findByChannelId(c_id, u.getUser_id());
        //return ue.getPlaybackPosition();
        return 0;
    }
}
