package store.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.repository.TrackRepository;
import store.entity.Track;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TrackController {
    private final TrackRepository trackRepository;

    @GetMapping(path = "/tracks/{id}")
    public Optional<Track> getById(@PathVariable(name = "id") Long id) {
        return trackRepository.findById(id);
    }

    @GetMapping(path = "/tracks/all")
    public List<Track> getAll() {
        return trackRepository.findAll();
    }

    @GetMapping(path = "/tracks/delete/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        trackRepository.deleteById(id);
    }

//    @PostMapping(path = "/tracks/update")
//    public void updateTrack(@RequestBody Track track) {
//        trackDAO.update(track);
//    }

    @PostMapping(path = "/tracks/add")
    public void addTrack(@RequestBody Track track) {
        trackRepository.save(track);
    }
}
