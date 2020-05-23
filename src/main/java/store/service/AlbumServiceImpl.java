package store.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import store.entity.Album;
import store.repository.AlbumRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepo albumRepo;

    public List<Album> getAllAlbumsReleaseDateBefore(int year) {
        return albumRepo.findAll().stream().filter(album -> album.getReleaseDate() < year).collect(Collectors.toList());
    }
}
