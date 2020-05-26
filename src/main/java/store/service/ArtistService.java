package store.service;

import store.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    public Optional<Artist> getById(Long id);

    public List<Artist> getAll();

    public void deleteById(Long id);

    public void save(Artist artist);
}
