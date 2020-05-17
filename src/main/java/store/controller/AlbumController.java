package store.controller;

import org.springframework.web.bind.annotation.*;
import store.dao.AlbumDAO;
import store.entity.Album;

import java.util.List;

@RestController
public class AlbumController {
    private final AlbumDAO albumDAO;

    public AlbumController(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    @GetMapping(path = "/albums/{id}")
    public Album getAlbumById(@PathVariable(name = "id") Long id) {
        return albumDAO.getEntityById(id);
    }

    @GetMapping(path = "/albums/all")
    public List<Album> getAllAlbum() {
        return albumDAO.getAll();
    }

    @GetMapping(path = "/albums/delete/{id}")
    public boolean deleteAlbumById(@PathVariable(name = "id") Long id) {
        return albumDAO.deleteById(id);
    }

    @PostMapping(path = "/albums/update")
    public void updateAlbum(@RequestBody Album album) {
        albumDAO.update(album);
    }

    @PostMapping(path = "/albums/add")
    public void addAlbum(@RequestBody Album album) {
        albumDAO.create(album);
    }
}
