package store.controller;

import org.springframework.web.bind.annotation.*;
import store.dao.ArtistDAO;
import store.entity.Artist;

import java.util.List;

@RestController
public class ArtistController {
    private final ArtistDAO artistDAO;

    public ArtistController(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    @GetMapping(path = "/artists/{id}")
    public Artist getById(@PathVariable(name = "id") Long id) {
        return artistDAO.getEntityById(id);
    }

    @GetMapping(path = "/artists/all")
    public List<Artist> getAll() {
        return artistDAO.getAll();
    }

    @GetMapping(path = "/artists/delete/{id}")
    public boolean deleteById(@PathVariable(name = "id") Long id) {
        return artistDAO.deleteById(id);
    }

    @PostMapping(path = "/artists/update")
    public void updateArtist(@RequestBody Artist artist) {
        artistDAO.update(artist);
    }

    @PostMapping(path = "/artists/add")
    public void addArtist(@RequestBody Artist artist) {
        artistDAO.create(artist);
    }
}
