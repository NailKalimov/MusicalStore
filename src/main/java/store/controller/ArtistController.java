package store.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.entity.Artist;
import store.service.ArtistService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping(path = "/artists/{id}")
    public Optional<Artist> getById(@PathVariable(name = "id") Long id) {
        return artistService.getById(id);
    }

    @GetMapping(path = "/artists/all")
    public List<Artist> getAll() {
        return artistService.getAll();
    }

    @GetMapping(path = "/artists/delete/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        artistService.deleteById(id);
    }

    @PostMapping(path = "/artists/add")
    public void addArtist(@RequestBody Artist artist) {
        artistService.save(artist);
    }

//    @PostMapping(path = "/artists/update")
//    public void updateArtist(@RequestBody Artist artist) {
//        artistDAO.update(artist);
//    }
}
