package store.service;

import store.entity.Album;

import java.util.List;

public interface AlbumService {

    void add(Album album);

    void update(Album album);

    boolean delete(Long id);

    Album getAll();

    List<Album> getById(Long id);

}
