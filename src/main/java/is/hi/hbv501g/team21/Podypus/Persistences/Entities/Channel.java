package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entity class for a podcast channel
 * Created with JAXB & stored in a repository
 */
//TODO: Refactor to 'Channel'
@XmlRootElement(name = "channel")
@Entity
@Table(name = "channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long channel_id;
    public long getChannel_id() {return channel_id;}

    @ManyToMany(mappedBy = "channels")
    private Set<User> users =  new HashSet<>();

    private final String atomNs = "http://www.w3.org/2005/Atom";
    private final String itunesNs = "http://www.itunes.com/dtds/podcast-1.0.dtd";

    private String title;
    private String pubDate;
    private String lastBuildDate;
    private String generator;
    private String link;
    private String language;
    private String copyright;
    private String docs;
    private String managingEditor;
    private String description;
    //itunes prefix
    private String summary;
    private String imageUrl;
    private String explicit;
    private String type;
    private String keywords;


    @OneToOne
    private ChannelOwner owner;
    @OneToOne
    private ChannelImage image;

    @OneToMany(
            mappedBy = "channel",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ChannelCategory> category;

    @OneToMany(
            mappedBy = "channel",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Episode> episodeList = new ArrayList<>();

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

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getLink() {
        return link;
    }

    @XmlAttribute(namespace = atomNs, name = "href")
    public void setLink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

    public String getManagingEditor() {
        return managingEditor;
    }

    public void setManagingEditor(String managingEditor) {
        this.managingEditor = managingEditor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    @XmlElement(namespace = itunesNs, name = "summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @XmlElement(namespace = itunesNs, name = "image")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getExplicit() {
        return explicit;
    }

    public List<ChannelCategory> getCategory() {
        return category;
    }

    @XmlElement(namespace = itunesNs, name = "category")
    public void setCategory(List<ChannelCategory> category) {
        this.category = category;
    }
    public ChannelOwner getOwner() {
        return owner;
    }

    @XmlElement(namespace = itunesNs, name = "owner")
    public void setOwner(ChannelOwner owner) {
        this.owner = owner;
    }


    @XmlElement(namespace = itunesNs, name = "explicit")
    public void setExplicit(String explicit) {
        this.explicit = explicit;
    }

    public String getType() {
        return type;
    }

    @XmlElement(namespace = itunesNs, name = "type")
    public void setType(String type) {
        this.type = type;
    }


    public ChannelImage getImage() {
        return image;
    }

    public void setImage(ChannelImage image) {
        this.image = image;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    @XmlElement(name="item")
    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public String getKeywords() {
        return keywords;
    }

    @XmlElement(namespace = itunesNs, name = "keywords")
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        int i = this.episodeList.size();
        String s = "Title: " + this.title + "\n" +
                "Number of episodes: " + i + "\n" +
                "Publication Date: " + this.pubDate + "\n" +
                "Last Build Date: " + this.lastBuildDate + "\n" +
                "Generator: " + this.generator + "\n" +
                "Link: " + this.link + "\n" +
                "Language: " + this.language + "\n" +
                "Copyright: " + this.copyright + "\n" +
                "Docs: " + this.docs + "\n" +
                "Managing Editor: " + this.managingEditor + "\n" +
                "Description: " + this.description + "\n" +
                "Image Url: " + this.imageUrl + "\n" +
                "Explicit: " + this.explicit + "\n" +
                "Summary: " + this.summary + "\n" +
                "Owner Name: " + this.owner.getName() + "\n" +
                "Owner Email: " + this.owner.getEmail() + "\n" +
                //"Image title: " + this.image.getTitle() + "\n" +
                //"Image url: " + this.image.getUrl() + "\n" +
                "Type: " + this.type + "\n";
        if (this.category != null) {
            s = s + "Categories:\n";
            for (ChannelCategory t : this.category) {
                s = s + "\t" + t.text + "\n";
            }
        }
        if (this.keywords != null) {
            s = s + "Keywords:\n";
            String[] kwarr  = this.keywords.split(",");
            for (String kw : kwarr) {
                s = s + "\t" + kw + "\n";
            }
        }
        return s;
    }
}
