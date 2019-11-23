package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

@Embeddable
public class ChannelOwner {

    private String name;
    private String email;
    private final String itunesNs = "http://www.itunes.com/dtds/podcast-1.0.dtd";

    public String getName() {
        return name;
    }

    @XmlElement(namespace = itunesNs, name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement(namespace = itunesNs, name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

}
