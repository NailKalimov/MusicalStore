package store.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.entity.Track;
import store.service.TrackService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TrackController {
    private final TrackService trackService;

    @GetMapping(path = "/tracks/{id}")
    public Optional<Track> getById(@PathVariable(name = "id") Long id) {
        return trackService.getById(id);
    }

    @GetMapping(path = "/tracks/all")
    public List<Track> getAll() {
        return trackService.getAll();
    }

    @GetMapping(path = "/tracks/delete/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        trackService.deleteById(id);
    }


    @PostMapping(path = "/tracks/add")
    public void addTrack(@RequestBody Track track) {
        trackService.save(track);
    }

//    @PostMapping(path = "/tracks/update")
//    public void updateTrack(@RequestBody Track track) {
//        trackDAO.update(track);
//    }

}
