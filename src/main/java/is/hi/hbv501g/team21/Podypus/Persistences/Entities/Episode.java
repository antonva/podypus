package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Entity class for a podcast episode
 * Created with JAXB & stored in a repository
 */
@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long episode_id;

    // Podypus data
    @ManyToOne(fetch = FetchType.LAZY)
    private Channel channel;


    @OneToMany(mappedBy = "episode", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<UserEpisode> userEpisodes;


    // XML Namespaces hardcoded.
    @Transient
    private final String atomNs = "http://www.w3.org/2005/Atom\n";
    @Transient
    private final String itunesNs = "http://www.itunes.com/dtds/podcast-1.0.dtd" ;

    // Embedded episode entitities
    @Embedded
    private EpisodeEnclosure enclosure;
    @Embedded
    private EpisodeGuid guid;
    @Embedded
    private EpisodeImage image;

    // Episode data
    @Column(length = 2048)
    private String title;
    private String pubDate;
    private boolean guidIsPermanent;
    private String link;
    @Column(length=10485760)
    private String description;
    private String contentEncoded;
    private String duration;
    private String explicit;
    @Column(length=10485760)
    private String keywords;
    @Column(length=10485760)
    private String subtitle;
    @Column(length=10485760)
    private String summary;
    private String episode; // Episode number
    private String episodeType;

    public Episode() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public EpisodeGuid getGuid() {
        return guid;
    }

    public void setGuid(EpisodeGuid guid) {
        this.guid = guid;
    }

    public boolean isGuidIsPermanent() {
        return guidIsPermanent;
    }

    public void setGuidIsPermanent(boolean guidIsPermanent) {
        this.guidIsPermanent = guidIsPermanent;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public EpisodeImage getImage() {
        return image;
    }

    @XmlElement(namespace = itunesNs, name="image")
    public void setImageUrl(EpisodeImage image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentEncoded() {
        return contentEncoded;
    }

    public void setContentEncoded(String contentEncoded) {
        this.contentEncoded = contentEncoded;
    }

    public String getDuration() {
        return duration;
    }

    @XmlElement(namespace = itunesNs)
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getExplicit() {
        return explicit;
    }

    @XmlElement(namespace = itunesNs)
    public void setExplicit(String explicit) {
        this.explicit = explicit;
    }

    public String getKeywords() {
        return keywords;
    }

    @XmlElement(namespace = itunesNs)
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getEpisode() {
        return episode;
    }

    @XmlElement(namespace = itunesNs, name = "episode")
    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getEpisodeType() {
        return episodeType;
    }

    @XmlElement(namespace = itunesNs)
    public void setEpisodeType(String episodeType) {
        this.episodeType = episodeType;
    }

    public EpisodeEnclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(EpisodeEnclosure enclosure) {
        this.enclosure = enclosure;
    }

    /* Turn Pubdate string into ISO representation for HTML */
    public String getIsoPubDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
        Date pd = sdf.parse(this.getPubDate());
        SimpleDateFormat iso = new SimpleDateFormat("yyyy-MM-dd");
        return iso.format(pd);
    }

    @Override
    public String toString() {
        String s = "Title: " + this.title + "\n" +
                "Publication Date: " + this.pubDate + "\n" +
                "Link: " + this.link + "\n" +
                //"Description: " + this.description + "\n" +
                "Duration: " + this.duration + "\n" +
                "Explicit: " + this.explicit + "\n" +
                //"Owner Name: " + this.owner.getName() + "\n" +
                //"Owner Email: " + this.owner.getEmail() + "\n" +
                //"Image title: " + this.image.getTitle() + "\n" +
                //"Image url: " + this.image.getUrl() + "\n" +
                "Keywords: " + this.keywords + "\n" +
                //"Subtitle: " + this.subtitle + "\n" +
                "Episode #: " + this.episode + "\n" +
                "Episode Type: " + this.episodeType;

        if (this.enclosure != null) {
            s = s + "\nEnclosure url: " +  this.enclosure.getUrl();
            s = s + "\nEnclosure type: " +  this.enclosure.getType();
            s = s + "\nEnclosure length: " +  this.enclosure.getLength();
        }
        if (this.image != null) {
            s = s + "Image Url: " + this.image.getHref() + "\n";
        }

        if (this.guid != null) {
            s = s + "\nguid is permanent: " +  this.guid.getIsPermaLink();
            s = s + "\nguid: " +  this.guid.getGuid();
        }
        return s;
    }

    public Set<UserEpisode> getUserEpisodes() {
        return userEpisodes;
    }

    public void setUserEpisodes(Set<UserEpisode> userEpisodes) {
        this.userEpisodes = userEpisodes;
    }

    public Long getEpisode_id() {
        return episode_id;
    }
}
