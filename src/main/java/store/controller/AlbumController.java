package store.controller;

import org.springframework.web.bind.annotation.*;
import store.dao.AlbumDAO;
import store.entity.Album;

import java.time.Year;
import java.util.List;

@RestController
public class AlbumController implements Controller<Album, Long> {
    private AlbumDAO albumDAO;

    public AlbumController(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    @Override
    @GetMapping(path = "/albums/{id}")
    public Album getAlbumById(@PathVariable(name = "id") Long id) {
        return albumDAO.getEntityById(id);
    }

    @Override
    @GetMapping(path = "/albums/all")
    public List<Album> getAllAlbum() {
        return albumDAO.getAll();
    }

    @Override
    @GetMapping(path = "/albums/delete/{id}")
    public boolean deleteAlbumById(@PathVariable(name = "id") Long id) {
        return albumDAO.deleteById(id);
    }

    @Override
    @PostMapping(path = "/albums/update")
    public void updateAlbum(@RequestBody Album album) {
        albumDAO.update(album);
    }

    @Override
    @PostMapping(path = "/albums/add")
    public void addAlbum(@RequestBody Album album) {
        albumDAO.create(album);
    }
}
