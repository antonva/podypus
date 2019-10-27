package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;


@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Podcast podcast;

    public Episode() {
    }

    private String title;
    private String pubDate;
    private String guid;
    private boolean guidIsPermanent;
    private String link;
    private String imageUrl;
    private String description;
    private String contentEncoded;
    private String enclosureLength;
    private String enclosureType;
    private String enclosureUrl;
    private String duration;
    private String explicit;
    private String keywords;
    private String subtitle;
    private String summary;
    private String episodeType;
}
