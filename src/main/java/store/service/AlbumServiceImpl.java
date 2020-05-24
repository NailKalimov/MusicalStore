package store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dao.AlbumDAO;
import store.entity.Album;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumDAO albumDAO;

    public AlbumServiceImpl(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    @Override
    public void add(Album album) {
        if (album.getAlbumName().contains("шансон")) {
            System.out.println("---------Шансону тут не место!--------");
        } else {
            albumDAO.create(album);
        }
    }

    @Override
    public void update(Album album) {

    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Album getAll() {
        return null;
    }

    @Override
    public List<Album> getById(Long id) {
        return null;
    }
}
