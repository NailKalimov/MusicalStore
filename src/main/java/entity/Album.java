package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Year;
import java.util.Collection;

@Entity
public class Album {
    @Id
    @Column(name = "id_album")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    Long albumId;

    @Getter
    @Setter
    private String albumName;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "albums")
    private Collection<Artist> artists;

    @Getter
    @Setter
    private Year releaseDate;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album", fetch = FetchType.EAGER)
    private Collection<Track> trackList;

    @Override
    public String toString() {
        return "Album(" + albumName +
                ", Artists" + artists +
                ", Release Date: " + releaseDate.toString() + ")";
    }
}
