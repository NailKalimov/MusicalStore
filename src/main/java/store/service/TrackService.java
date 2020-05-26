package store.service;

import store.entity.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    public Optional<Track> getById(Long id);

    public List<Track> getAll();

    public void deleteById(Long id);

    public void save(Track track);
}
