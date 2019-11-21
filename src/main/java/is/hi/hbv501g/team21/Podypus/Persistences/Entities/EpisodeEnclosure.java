package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;

@Embeddable
public class EpisodeEnclosure {

    private String type;
    private String length;
    private String url;

    public String getType() {
        return type;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    @XmlAttribute
    public void setLength(String length) {
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    @XmlAttribute
    public void setUrl(String url) {
        this.url = url;
    }
}
