package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAttribute;

@Embeddable
public class ChannelItunesImage {
    private String imageHref;

    public String getImageHref() {
        return imageHref;
    }

    @XmlAttribute(name = "href")
    public void setImageHref(String href) {
        this.imageHref = href;
    }
}
