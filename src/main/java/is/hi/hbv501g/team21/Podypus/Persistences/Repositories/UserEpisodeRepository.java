package is.hi.hbv501g.team21.Podypus.Persistences.Repositories;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.UserEpisode;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.UserEpisodeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEpisodeRepository extends JpaRepository<UserEpisode, Long> {
    UserEpisode save(UserEpisode userEpisode);
    void delete(UserEpisode userEpisode);
    List<UserEpisode> findAll();
}
