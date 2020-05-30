package store.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Artist.class)
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String artistName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ARTISTS_AND_ALBUMS",
            joinColumns = @JoinColumn(name = "ARTIST"),
            inverseJoinColumns = @JoinColumn(name = "ALBUM")
    )
    private List<Album> albums;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TRACKS_AND_ARTISTS",
            joinColumns = @JoinColumn(name = "ARTIST"),
            inverseJoinColumns = @JoinColumn(name = "TRACK"))
    private List<Track> tracks;

    @Override
    public String toString() {
        return "Name: " + artistName;
    }
}
