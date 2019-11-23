package is.hi.hbv501g.team21.Podypus.Services.Implementations;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
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
    public List<Channel> findByTitle(String title) {

        return podcastRepository.findByTitle(title);
    }
}
