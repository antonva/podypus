package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;

@Entity
public class EpisodeEnclosure {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
