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

    public List<Album> getAllAlbumsWithReleaseDateBefore(int year) {
        return albumRepository.findAll().stream().filter(album -> album.getReleaseDate() < year).collect(Collectors.toList());
    }

    public Optional<Album> getByName(String name) {
        return albumRepository.findByAlbumName(name);
    }

    public Optional<Album> getById(Long id) {
        return albumRepository.findById(id);
    }

    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    public void deleteById(Long id) {
        albumRepository.deleteById(id);
    }

    public void save(Album album) {
        albumRepository.save(album);
    }
}
