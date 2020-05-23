package store.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.repository.AlbumRepo;
import store.entity.Album;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AlbumController {
    private final AlbumRepo albumRepo;

    @GetMapping(path = "/albums/{id}")
    public Optional<Album> getAlbumById(@PathVariable(name = "id") Long id) {
        return albumRepo.findById(id);
    }

    @GetMapping(path = "/albums/all")
    public List<Album> getAllAlbum() {
        return albumRepo.findAll();
    }

    @GetMapping(path = "/albums/delete/{id}")
    public void deleteAlbumById(@PathVariable(name = "id") Long id) {
        albumRepo.deleteById(id);
    }

//    @PostMapping(path = "/albums/update")
//    public void updateAlbum(@RequestBody Album album) {
//        albumDAO.update(album);
//    }

    @PostMapping(path = "/albums/add")
    public void addAlbum(@RequestBody Album album) {
        albumRepo.save(album);
    }
}
