package store.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.repository.ArtistRepo;
import store.entity.Artist;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ArtistController {
    private final ArtistRepo artistRepo;

    @GetMapping(path = "/artists/{id}")
    public Optional<Artist> getById(@PathVariable(name = "id") Long id) {
        return artistRepo.findById(id);
    }

    @GetMapping(path = "/artists/all")
    public List<Artist> getAll() {
        return artistRepo.findAll();
    }

    @GetMapping(path = "/artists/delete/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
        artistRepo.deleteById(id);
    }

//    @PostMapping(path = "/artists/update")
//    public void updateArtist(@RequestBody Artist artist) {
//        artistDAO.update(artist);
//    }

    @PostMapping(path = "/artists/add")
    public void addArtist(@RequestBody Artist artist) {
        artistRepo.save(artist);
    }
}
