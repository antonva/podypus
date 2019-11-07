package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@Entity
public class EpisodeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String href;

    public String getHref() {
        return href;
    }

    @XmlAttribute
    public void setHref(String href) {
        this.href = href;
    }
}
