package is.hi.hbv501g.team21.Podypus.Services.Implementations;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Podcast;
import is.hi.hbv501g.team21.Podypus.Persistences.Repositories.PodcastRepository;
import is.hi.hbv501g.team21.Podypus.Services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PodcastServiceImplementation implements PodcastService {

        private PodcastRepository repository;

    @Autowired
    public PodcastServiceImplementation(PodcastRepository podcastRepository) {

        this.repository = podcastRepository;
    }

    @Override
    public Podcast save(Podcast podcast) {

        return repository.save(podcast);
    }

    @Override
    public void delete(Podcast podcast) {

        repository.delete(podcast);
    }

    @Override
    public List<Podcast> findAll() {

        return repository.findAll();
    }

    @Override
    public Optional<Podcast> findById(long id) {

        return repository.findById(id);
    }

    @Override
    public List<Podcast> findByTitle(String title) {

        return repository.findByTitle(title);
    }
}
