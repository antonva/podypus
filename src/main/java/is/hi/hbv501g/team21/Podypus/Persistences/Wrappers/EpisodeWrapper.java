package is.hi.hbv501g.team21.Podypus.Persistences.Wrappers;

public class EpisodeWrapper {
    private Long id;
    private float pos;

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    private boolean ended;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPos() {
        return pos;
    }

    public void setPos(float pos) {
        this.pos = pos;
    }

}
