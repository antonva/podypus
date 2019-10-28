package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "channel")
@Entity
@Table(name = "podcasts")
public class Podcast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
    private String[] categoryText;
    private String imageUrl;
    private String explicit;
    private String ownerName;
    private String ownerEmail;
    @OneToMany(
            mappedBy = "podcast",
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

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String[] getCategoryText() {
        return categoryText;
    }

    public void setCategoryText(String[] categoryText) {
        this.categoryText = categoryText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getExplicit() {
        return explicit;
    }

    public void setExplicit(String explicit) {
        this.explicit = explicit;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    @XmlElement(name="item")
    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
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
                "Owner Name: " + this.ownerName + "\n" +
                "Owner Email: " + this.ownerEmail + "\n" +
                "Categories:\n";
        if (this.categoryText != null) {
            for (String t : this.categoryText) {
                s = s + "\t" + t + "\n";
            }
        }
        return s;
    }
}
