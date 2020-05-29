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
    public Optional<Artist> getArtistById(@PathVariable(name = "id") Long id) {
        return artistService.getById(id);
    }

    @GetMapping(path = "/artists/all")
    public List<Artist> getAllArtists() {
        return artistService.getAll();
    }

    @DeleteMapping(path = "/artists/delete/{id}")
    public void deleteArtistById(@PathVariable Long id) {
        artistService.deleteById(id);
    }

    @PostMapping(path = "/artists/add")
    public void addArtist(@RequestBody Artist artist) {
        artistService.save(artist);
    }

    @PutMapping(path = "/artists/update")
    public void updateAlbum(@RequestBody Artist artist) {
        artistService.update(artist);
    }

}
