package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import java.util.List;

public class ItunesSearchResult {
    private String resultCount;
    private List<ItunesSearchItem> results;

    public ItunesSearchResult() {
        super();
    }

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public List<ItunesSearchItem> getResults() {
        return results;
    }

    public void setResults(List<ItunesSearchItem> results) {
        this.results = results;
    }
}
