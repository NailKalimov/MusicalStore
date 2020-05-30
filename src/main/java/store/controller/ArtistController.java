package store.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.entity.Artist;
import store.service.ArtistService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("artists")
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping(path = "{id}")
    public Optional<Artist> getArtistById(@PathVariable(name = "id") Long id) {
        return artistService.getById(id);
    }

    @GetMapping
    public List<Artist> getAllArtists() {
        return artistService.getAll();
    }

    @DeleteMapping(path = "{id}")
    public void deleteArtistById(@PathVariable Long id) {
        artistService.deleteById(id);
    }

    @PostMapping()
    public Artist addArtist(@RequestBody Artist artist) {
        artistService.save(artist);
        return artist;
    }

    @PutMapping(path = "{id}")
    public Artist updateAlbum(@PathVariable Long id, @RequestBody Artist artist) throws NotFoundException {
        return artistService.update(id, artist);
    }

}
