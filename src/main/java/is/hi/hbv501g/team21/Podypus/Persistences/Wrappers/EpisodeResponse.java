package is.hi.hbv501g.team21.Podypus.Persistences.Wrappers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Episode;
import is.hi.hbv501g.team21.Podypus.Persistences.Entities.UserEpisode;

public class EpisodeResponse {
    private long id;

    private long channel_id;
    private double playbackPos;
    private boolean played;


    // Episode enclosure data
    private String enclosure_type;
    private String enclosure_length;
    private String enclosure_url; // Podcast file


    // Episode data
    private String guid;
    private String image;
    private String title;
    private String pubDate;
    private boolean guidIsPermanent;
    private String link;
    private String description;
    private String contentEncoded;
    private String duration;
    private String explicit;
    private String keywords;
    private String subtitle;
    private String summary;
    private String episode; // Episode number

    public EpisodeResponse(UserEpisode e) {
        Episode ep = e.getEpisode();
        this.id = ep.getEpisode_id();
        this.channel_id  = e.getChannel().getChannel_id();
        this.playbackPos = e.getPlaybackPosition();
        this.played = e.isPlayed();
        this.enclosure_type = ep.getEnclosure().getType();
        this.enclosure_length = ep.getEnclosure().getLength();
        this.enclosure_url = ep.getEnclosure().getUrl();
        this.guid = ep.getGuid().getGuid();
        if (ep.getImage() != null && ep.getImage().getHref() != null) {
            this.image = ep.getImage().getHref();
        } else if (e.getChannel().getImageUrl() != null){
            this.image = e.getChannel().getImageUrl().getImageHref();
        } else {
            this.image = e.getChannel().getImage().getChannelImageUrl();
        }
        this.title = ep.getTitle();
        this.pubDate = ep.getPubDate();
        this.guidIsPermanent = ep.isGuidIsPermanent();
        this.link = ep.getLink();
        this.description = ep.getDescription();
        this.contentEncoded = ep.getContentEncoded();
        this.duration = ep.getDuration();
        this.explicit = ep.getExplicit();
        this.keywords = ep.getKeywords();
        this.subtitle = ep.getSubtitle();
        this.summary = ep.getSummary();
        this.episode = ep.getEpisode(); // Episode number
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }

    public double getPlaybackPos() {
        return playbackPos;
    }

    public void setPlaybackPos(double playbackPos) {
        this.playbackPos = playbackPos;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public String getEnclosure_type() {
        return enclosure_type;
    }

    public void setEnclosure_type(String enclosure_type) {
        this.enclosure_type = enclosure_type;
    }

    public String getEnclosure_length() {
        return enclosure_length;
    }

    public void setEnclosure_length(String enclosure_length) {
        this.enclosure_length = enclosure_length;
    }

    public String getEnclosure_url() {
        return enclosure_url;
    }

    public void setEnclosure_url(String enclosure_url) {
        this.enclosure_url = enclosure_url;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getEpisodeType() {
        return episodeType;
    }

    public void setEpisodeType(String episodeType) {
        this.episodeType = episodeType;
    }

    private String episodeType;
}
