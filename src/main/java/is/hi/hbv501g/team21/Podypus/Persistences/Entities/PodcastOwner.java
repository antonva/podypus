/*package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "podcastowners")
public class PodcastOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;

    public String getName() {
        return name;
    }

    @XmlElement(name = "itunes:name")
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement(name = "itunes:email")
    public void setEmail(String email) {
        this.email = email;
    }

}
*/