package is.hi.hbv501g.team21.Podypus.Persistences.Repositories;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.User;
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
    List<Channel> findByTitle(String title);
    Optional<Channel> findById(long id);
    @Modifying
    @Query(value = "insert into user_channels (user_id, channel_id) values (:user_id, :channel_id)",
            nativeQuery = true)
    boolean saveChannel(@Param("user_id") Long user_id, @Param("channel_id") Long channel_id);
}
