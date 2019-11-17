package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_episodes")
public class UserEpisode {

    @EmbeddedId
    private UserEpisodeId id;

    private int playbackPosition;


    private boolean played;

    public UserEpisode() {

    }

    public int getPlaybackPosition() {
        return playbackPosition;
    }

    public void setPlaybackPosition(int playbackPosition) {
        this.playbackPosition = playbackPosition;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }
}
