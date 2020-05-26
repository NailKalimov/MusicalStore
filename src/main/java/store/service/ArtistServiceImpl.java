package store.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import store.entity.Artist;
import store.repository.ArtistRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    @Override
    public Optional<Artist> getById(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public List<Artist> getAll() {
        return artistRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        artistRepository.deleteById(id);
    }

    @Override
    public void save(Artist artist) {
        artistRepository.save(artist);
    }
}
