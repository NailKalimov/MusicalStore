package store.service;

import store.entity.Album;

import java.util.List;

public interface AlbumService {

    List<Album> getAllAlbumsReleaseDateBefore(int year);
}
