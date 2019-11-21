package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

@Embeddable
public class ChannelImage {

    private String channelImageUrl;
    private String channelImageTitle;

    public String getChannelImageUrl() {
        return channelImageUrl;
    }

    @XmlElement(name="url")
    public void setChannelImageUrl(String channelImageUrl) {
        this.channelImageUrl = channelImageUrl;
    }

    public String getChannelImageTitle() {
        return channelImageTitle;
    }

    @XmlElement(name="title")
    public void setChannelImageTitle(String channelImageTitle) {
        this.channelImageTitle = channelImageTitle;
    }
}
