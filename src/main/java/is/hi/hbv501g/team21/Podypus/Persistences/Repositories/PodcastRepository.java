package is.hi.hbv501g.team21.Podypus.Persistences.Repositories;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.UserEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PodcastRepository extends JpaRepository<Channel, Long> {

    Channel save(Channel channel);
    void delete(Channel channel);
    List<Channel> findAll();
    Channel findByTitle(String title);
    Optional<Channel> findById(long id);
    @Query("SELECT ue FROM UserEpisode ue WHERE ue.episode.episode_id = ?1 AND ue.user.user_id = ?2")
    UserEpisode findByEpisodeId(Long episode_id, Long user_id);

    @Query("SELECT ue FROM UserEpisode ue WHERE ue.channel.channel_id = ?1 AND ue.user.user_id = ?2")
    List<UserEpisode> findByChannelId(Long channel_id, Long user_id);

    @Query(
            value = "SELECT channel_id FROM episodes WHERE episode_id = ?1",
            nativeQuery = true
    )
    Long findChannelIdByEpisodeId(Long episode_id);
}
