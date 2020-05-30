package store.controller;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.entity.Album;
import store.service.AlbumService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("albums")
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping(path = "/name/{name}")
    public Optional<Album> getAlbumByName(@PathVariable(name = "name") String name) {
        return albumService.getByName(name);
    }

    @GetMapping(path = "/release_date_before/{year}")
    public List<Album> getAllAlbumsWithReleaseDateBefore(@PathVariable(name = "year") int year) {
        return albumService.getAllAlbumsWithReleaseDateBefore(year);
    }

    @GetMapping(path = "/{id}")
    public Optional<Album> getAlbumById(@PathVariable(name = "id") Long id) {
        return albumService.getById(id);
    }

    @GetMapping()
    public List<Album> getAllAlbums() {
        return albumService.getAll();
    }

    @DeleteMapping("{id}")
    public void deleteAlbumById(@PathVariable Long id) {
        albumService.deleteById(id);
    }

    @PostMapping
    public Album addAlbum(@RequestBody Album album) {
        albumService.save(album);
        return album;
    }

    @PutMapping("{id}")
    public Album updateAlbum(@PathVariable Long id, @RequestBody Album album) throws NotFoundException {
        return albumService.update(id, album);
    }
}
