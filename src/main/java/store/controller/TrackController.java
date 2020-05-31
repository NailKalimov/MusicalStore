package store.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import store.entity.Track;
import store.service.TrackService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "tracks")
public class TrackController {
    private final TrackService trackService;

    @GetMapping(path = "{id}")
    public Optional<Track> getById(@PathVariable(name = "id") Long id) {
        return trackService.getById(id);
    }

    @GetMapping
    public List<Track> getAll() {
        return trackService.getAll();
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable Long id) {
        trackService.deleteById(id);
    }

    @PostMapping()
    public Track addTrack(@RequestBody Track track) {
        trackService.save(track);
        return track;
    }

    @PutMapping(path = "{id}")
    public Track updateTrack(@PathVariable Long id, @RequestBody Track track) throws NotFoundException {
        return trackService.update(id, track);
    }

    @GetMapping(path = "/tracks/deleteAll")
    public void deleteAll() {
        trackService.deleteAll();
    }

    @GetMapping(path = "/tracks/insertTestData")
    public void insertTestData() {
        trackService.insertTestData();
    }



}
