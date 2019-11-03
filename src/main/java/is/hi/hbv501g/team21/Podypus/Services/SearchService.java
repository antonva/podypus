package is.hi.hbv501g.team21.Podypus.Services;

import is.hi.hbv501g.team21.Podypus.Persistences.Entities.SearchResult;

import java.util.List;

public interface SearchService {
    SearchResult searchByTitle(String query);
    SearchResult searchByCategory(String query);
    SearchResult searchByArtist(String query);
}
