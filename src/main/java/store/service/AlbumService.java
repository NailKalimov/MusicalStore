package store.service;

import javassist.NotFoundException;
import store.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    List<Album> getAllAlbumsWithReleaseDateBefore(int year);

    Optional<Album> getByName(String name);

    Optional<Album> getById(Long id);

    List<Album> getAll();

    void deleteById(Long id);

    void save(Album album);

    Album update(Long id, Album album) throws NotFoundException;
}
