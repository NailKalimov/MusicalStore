package store.service;

import javassist.NotFoundException;
import store.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Optional<Artist> getById(Long id);

    List<Artist> getAll();

    void deleteById(Long id);

    void save(Artist artist);

    Artist update(Long id, Artist artist) throws NotFoundException;
}
