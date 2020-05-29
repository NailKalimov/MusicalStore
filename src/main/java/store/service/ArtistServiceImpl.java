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

    @Override
    public boolean update(Artist artist) {
        Optional<Artist> artistOptionalFromDB = artistRepository.findById(artist.getId());
        if (!artistOptionalFromDB.isPresent()) {
            System.out.println("--------- Artist not found");
            return false;
        }
        if (artist.getAlbums() == null)
            artist.setAlbums(artistOptionalFromDB.get().getAlbums());
        if (artist.getTracks() == null)
            artist.setTracks(artistOptionalFromDB.get().getTracks());
        artistRepository.save(artist);
        return true;
    }
}
