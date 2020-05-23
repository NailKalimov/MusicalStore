package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.entity.Track;

import java.util.Optional;

public interface TrackRepo extends JpaRepository<Track, Long> {
}
