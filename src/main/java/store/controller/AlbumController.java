package store.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import store.entity.Album;
import store.service.AlbumService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping(path = "/albums/name/{name}")
    public Optional<Album> getAlbumByName(@PathVariable(name = "name") String name) {
        return albumService.getByName(name);
    }

    @GetMapping(path = "/albums/release_date_before/{year}")
    public List<Album> getAllAlbumsWithReleaseDateBefore(@PathVariable(name = "year") int year) {
        return albumService.getAllAlbumsWithReleaseDateBefore(year);
    }

    @GetMapping(path = "/albums/{id}")
    public Optional<Album> getAlbumById(@PathVariable(name = "id") Long id) {
        return albumService.getById(id);
    }

    @GetMapping(path = "/albums/all")
    public List<Album> getAllAlbums() {
        return albumService.getAll();
    }

    @DeleteMapping("/albums/delete/{id}")
    public void deleteAlbumById(@PathVariable Long id) {
        albumService.deleteById(id);
    }

    @PostMapping(path = "/albums/add")
    public void addAlbum(@RequestBody Album album) {
        albumService.save(album);
    }

    @PutMapping(path = "/albums/update")
    public void updateAlbum(@RequestBody Album album) {
        albumService.update(album);
    }
}
