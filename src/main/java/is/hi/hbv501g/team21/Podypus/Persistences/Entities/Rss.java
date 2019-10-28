package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rss")
public class Rss {
    @XmlElement(name = "channel")
    public Podcast podcast;

    @XmlAttribute
    public String version;
}
