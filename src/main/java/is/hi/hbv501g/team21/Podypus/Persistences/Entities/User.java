package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users", uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
//@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @NotNull
    @Column(name="username", unique=true)
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<UserEpisode> episodes;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_channels",
            joinColumns = { @JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="channel_id")}
    )
    private Set<Channel> channels = new HashSet<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Channel channel) {
        this.channels.add(channel);
    }

    public long getUser_id() {return user_id;}

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() { return this.username; }

    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword(){return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserEpisode> getEpisodes() {
        return episodes;
    }

    public void addEpisode(Episode episode, Channel channel) {
        UserEpisode ue = new UserEpisode();
        ue.setEpisode(episode);
        ue.setUser(this);
        ue.setPlayed(false);
        ue.setPlaybackPosition(0);
        ue.userEpisodeId = new UserEpisodeId();
        ue.userEpisodeId.setEpisodeId(episode.getEpisode_id());
        ue.userEpisodeId.setUserId(this.getUser_id());
        ue.userEpisodeId.setChannelId(channel.getChannel_id());
        this.episodes.add(ue);
    }
}
