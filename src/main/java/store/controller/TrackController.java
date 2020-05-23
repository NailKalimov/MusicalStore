package store.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.repository.TrackRepo;
import store.entity.Track;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TrackController {
    private final TrackRepo trackRepo;

    @GetMapping(path = "/tracks/{id}")
    public Optional<Track> getById(@PathVariable(name = "id") Long id) {
        return trackRepo.findById(id);
    }

    @GetMapping(path = "/tracks/all")
    public List<Track> getAll() {
        return trackRepo.findAll();
    }

    @GetMapping(path = "/tracks/delete/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        trackRepo.deleteById(id);
    }

//    @PostMapping(path = "/tracks/update")
//    public void updateTrack(@RequestBody Track track) {
//        trackDAO.update(track);
//    }

    @PostMapping(path = "/tracks/add")
    public void addTrack(@RequestBody Track track) {
        trackRepo.save(track);
    }
}
