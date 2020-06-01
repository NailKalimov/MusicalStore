package store.service;

import store.entity.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    Optional<Track> getById(Long id);

    List<Track> getAll();

    void deleteById(Long id);

    void save(Track track);

    void insertTestData();

    void deleteAll();

    boolean update(Track track);
}
