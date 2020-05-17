package store.controller;

import org.springframework.web.bind.annotation.*;
import store.dao.TrackDAO;
import store.entity.Track;

import java.util.List;

@RestController
public class TrackController {
    private final TrackDAO trackDAO;

    public TrackController(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @GetMapping(path = "/tracks/{id}")
    public Track getById(@PathVariable(name = "id") Long id) {
        return trackDAO.getEntityById(id);
    }

    @GetMapping(path = "/tracks/all")
    public List<Track> getAll() {
        return trackDAO.getAll();
    }

    @GetMapping(path = "/tracks/delete/{id}")
    public boolean deleteById(@PathVariable(name = "id") Long id) {
        return trackDAO.deleteById(id);
    }

    @PostMapping(path = "/tracks/update")
    public void updateTrack(@RequestBody Track track) {
        trackDAO.update(track);
    }

    @PostMapping(path = "/tracks/add")
    public void addTrack(@RequestBody Track track) {
        trackDAO.create(track);
    }
}
