package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "rss")
public class Rss {
    @XmlElement(name = "channel")
    public Podcast podcast;

    @XmlAttribute
    public String version;

    @XmlAttribute(name = "xmlns:atom")
    public String atom;

    @XmlAttribute(name = "xmlns:cc")
    public String cc;

    @XmlAttribute(name = "xmlns:content")
    public String content;

    @XmlAttribute(name = "xmlns:itunes")
    public String itunes;

    @XmlAttribute(name = "xmlns:media")
    public String media;

    @XmlAttribute(name = "xmlns:rdf")
    public String rdf;
}
