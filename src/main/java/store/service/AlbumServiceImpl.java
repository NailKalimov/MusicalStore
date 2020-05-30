package store.service;

import javassist.NotFoundException;
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
    public Album update(Long id, Album album) throws NotFoundException {
        Optional<Album> albumOptionalFromDB = albumRepository.findById(id);
        if (!albumOptionalFromDB.isPresent()) {
            System.out.println("Album not found");
            throw new NotFoundException("No found Album");
        }
        if (album.getArtists() == null)
            album.setArtists(albumOptionalFromDB.get().getArtists());
        if (album.getTrackList() == null)
            album.setTrackList(albumOptionalFromDB.get().getTrackList());
        album.setId(id);
        albumRepository.save(album);
        return album;
    }
}
