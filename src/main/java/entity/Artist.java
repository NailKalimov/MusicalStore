package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Artist {
    @Id
    @Column(name = "id_artist")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    Long artistId;

    @Getter
    @Setter
    private String artistName;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ARTISTS_AND_ALBUMS",
            joinColumns = @JoinColumn(name = "ARTIST"),
            inverseJoinColumns = @JoinColumn(name = "ALBUM")
    )
    private Collection<Album> albums;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "artists")
    private Collection<Track> tracks;

    @Override
    public String toString() {
        return "Name: " + artistName;
    }
}
