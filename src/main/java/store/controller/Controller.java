package store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import store.entity.Album;

import java.util.List;

public interface Controller<E, K> {
    @GetMapping(path = "/{id}")
    E getAlbumById(@PathVariable(name = "id") K id);

    @GetMapping(path = "/all")
    List<E> getAllAlbum();

    @GetMapping(path = "/delete/{id}")
    boolean deleteAlbumById(@PathVariable(name = "id") K id);

    @PostMapping(path = "/update")
    void updateAlbum(@RequestBody E album);

    @PostMapping(path = "/add")
    void addAlbum(@RequestBody E album);
}
