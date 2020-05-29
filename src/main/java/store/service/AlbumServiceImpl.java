package store.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import store.entity.Album;
import store.repository.AlbumRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public List<Album> getAllAlbumsWithReleaseDateBefore(int year) {
        return albumRepository.findAll().stream().filter(album -> album.getReleaseDate() < year).collect(Collectors.toList());
    }

    @Override
    public Optional<Album> getByName(String name) {
        return albumRepository.findByAlbumName(name);
    }

    @Override
    public Optional<Album> getById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public void save(Album album) {
        albumRepository.save(album);
    }

    @Override
    public boolean update(Album album) {
        Optional<Album> albumOptionalFromDB = albumRepository.findById(album.getId());
        if (!albumOptionalFromDB.isPresent()) {
            System.out.println("Album not found");
            return false;
        }
        if (album.getArtists() == null)
            album.setArtists(albumOptionalFromDB.get().getArtists());
        if (album.getTrackList() == null)
            album.setTrackList(albumOptionalFromDB.get().getTrackList());
        albumRepository.save(album);
        return true;
    }
}
