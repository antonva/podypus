package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_episodes")
public class UserEpisode implements Serializable {

    @EmbeddedId
    UserEpisodeId userEpisodeId;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("episode_id")
    @JoinColumn(name = "episode_id")
    private Episode episode;

    @ManyToOne
    @MapsId("channel_id")
    @JoinColumn(name = "channel_id")
    private Channel channel;

    private float playbackPosition;
    private boolean played;

    public UserEpisode() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public float getPlaybackPosition() {
        return playbackPosition;
    }

    public void setPlaybackPosition(float playbackPosition) {
        this.playbackPosition = playbackPosition;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public Channel getChannel() {
        return channel;
    }
}
