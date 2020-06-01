package store.service;

import store.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Optional<Artist> getById(Long id);

    List<Artist> getAll();

    void deleteById(Long id);

    void save(Artist artist);

    boolean update(Artist artist);
}
