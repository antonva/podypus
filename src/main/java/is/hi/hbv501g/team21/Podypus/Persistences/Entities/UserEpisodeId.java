package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserEpisodeId implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "episode_id")
    private Long episodeId;


    public UserEpisodeId() {
    }

    public UserEpisodeId(Long userId, Long episodeId) {
        this.userId = userId;
        this.episodeId = episodeId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEpisodeId)) return false;
        UserEpisodeId that = (UserEpisodeId) o;
        return Objects.equals(getEpisodeId(), that.getEpisodeId()) && Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getEpisodeId());
    }
}
