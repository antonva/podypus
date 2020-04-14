package is.hi.hbv501g.team21.Podypus.Persistences.Wrappers;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.Channel;

public class ChannelResponse {
    private long id;
    private String title;
    private String name;
    private String pubDate;
    private String lastBuildDate;
    private String generator;
    private String link;
    private String language;
    private String copyright;
    private String docs;
    private String managingEditor;
    private String description;
    private String summary;
    private String imageUrl;
    private String explicit;
    private String type;
    private String keywords;

    public ChannelResponse(Channel c) {
        this.id = c.getChannel_id();
        this.title = c.getTitle();
        this.name = c.getOwner().getName();
        this.pubDate = c.getPubDate();
        this.lastBuildDate = c.getLastBuildDate();
        this.generator = c.getGenerator();
        this.link = c.getLink();
        this.language = c.getLanguage();
        this.copyright = c.getCopyright();
        this.docs = c.getDocs();
        this.managingEditor= c.getManagingEditor();
        this.description = c.getDescription();
        this.summary = c.getSummary();
        if (c.getImage().getChannelImageUrl() == null) {
            if (c.getImageUrl().getImageHref() == null) {
                this.imageUrl = "podypus";
            } else {
                this.imageUrl = c.getImageUrl().getImageHref();
            }
        } else {
            this.imageUrl = c.getImage().getChannelImageUrl();
        }
        this.explicit = c.getExplicit();
        this.type = c.getType();
        this.keywords = c.getKeywords();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private String category;
}
