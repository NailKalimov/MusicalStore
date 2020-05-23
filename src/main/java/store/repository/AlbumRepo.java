package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.entity.Album;

import java.util.Optional;

public interface AlbumRepo extends JpaRepository<Album, Long> {
    Optional<Album> findByAlbumName(String name);
}