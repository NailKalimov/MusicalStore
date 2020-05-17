package store.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Artist.class)
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    Long id;

    @Getter
    @Setter
    private String artistName;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ARTISTS_AND_ALBUMS",
            joinColumns = @JoinColumn(name = "ARTIST"),
            inverseJoinColumns = @JoinColumn(name = "ALBUM")
    )
    private List<Album> albums;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "artists")
    private List<Track> tracks;

    @Override
    public String toString() {
        return "Name: " + artistName;
    }
}
