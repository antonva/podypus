package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Embeddable
public class EpisodeGuid {
    private String isPermaLink;

    private String guid;

    public String getIsPermaLink() {
        return isPermaLink;
    }

    @XmlAttribute
    public void setIsPermaLink(String isPermaLink) {
        this.isPermaLink = isPermaLink;
    }

    public String getGuid() {
        return guid;
    }

    @XmlValue
    public void setGuid(String guid) {
        this.guid = guid;
    }

}
