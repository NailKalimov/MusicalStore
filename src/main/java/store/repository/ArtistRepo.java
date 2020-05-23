package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.entity.Artist;

import java.util.Optional;

public interface ArtistRepo extends JpaRepository<Artist, Long> {
    Optional<Artist> findByArtistName(String name);
}
