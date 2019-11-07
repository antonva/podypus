package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class ChannelImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;
    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
