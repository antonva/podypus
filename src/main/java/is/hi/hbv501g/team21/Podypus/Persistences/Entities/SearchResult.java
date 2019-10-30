package is.hi.hbv501g.team21.Podypus.Persistences.Entities;

import java.util.List;

public class SearchResult {

    private String msg;
    private String resultCount;
    private List<SearchItem> results;

    public SearchResult() {
        super();
    }

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }

    public List<SearchItem> getResults() {
        return results;
    }

    public void setResults(List<SearchItem> results) {
        this.results = results;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
