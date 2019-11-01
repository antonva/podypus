package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;

/**
 * Entity class for a podcast episode
 * Created with JAXB & stored in a repository
 */
@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Podcast podcast;

    private String atomNs = "http://www.w3.org/2005/Atom\n";
    private String itunesNs = "http://www.itunes.com/dtds/podcast-1.0.dtd" ;

    private String title;
    private String pubDate;
    private String guid;
    private boolean guidIsPermanent;
    private String link;
    private String imageUrl;

    //@Column(name = "description", columnDefinition = "TEXT")
    //private String description;
    //private String contentEncoded;
    private String enclosureLength;
    private String enclosureType;
    private String enclosureUrl;
    private String duration;
    private String explicit;
    private String keywords;
    //private String subtitle;
    private String summary;
    private int episode; // Episode number
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    /*
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
    */
    public String getEnclosureLength() {
        return enclosureLength;
    }

    public void setEnclosureLength(String enclosureLength) {
        this.enclosureLength = enclosureLength;
    }

    public String getEnclosureType() {
        return enclosureType;
    }

    public void setEnclosureType(String enclosureType) {
        this.enclosureType = enclosureType;
    }

    public String getEnclosureUrl() {
        return enclosureUrl;
    }

    public void setEnclosureUrl(String enclosureUrl) {
        this.enclosureUrl = enclosureUrl;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getExplicit() {
        return explicit;
    }

    public void setExplicit(String explicit) {
        this.explicit = explicit;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    /*
    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    */
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public String getEpisodeType() {
        return episodeType;
    }

    public void setEpisodeType(String episodeType) {
        this.episodeType = episodeType;
    }

    @Override
    public String toString() {
        String s = "Title: " + this.title + "\n" +
                "Publication Date: " + this.pubDate + "\n" +
                "Link: " + this.link + "\n" +
                //"Description: " + this.description + "\n" +
                "Duration: " + this.duration + "\n" +
                "Image Url: " + this.imageUrl + "\n" +
                "Explicit: " + this.explicit + "\n" +
                //"Owner Name: " + this.owner.getName() + "\n" +
                //"Owner Email: " + this.owner.getEmail() + "\n" +
                //"Image title: " + this.image.getTitle() + "\n" +
                //"Image url: " + this.image.getUrl() + "\n" +
                "Keywords: " + this.keywords + "\n" +
                //"Subtitle: " + this.subtitle + "\n" +
                "Episode #: " + this.episode + "\n" +
                "Episode Type: " + this.episodeType;
        return s;
    }

}
