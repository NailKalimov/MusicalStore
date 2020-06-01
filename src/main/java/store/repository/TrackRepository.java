package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.entity.Track;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
