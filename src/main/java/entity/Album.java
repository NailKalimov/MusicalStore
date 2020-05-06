package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Year;
import java.util.List;

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
    @ManyToMany(mappedBy = "albums", fetch = FetchType.EAGER)
    private List<Artist> artists;

    @Getter
    @Setter
    private Year releaseDate;

    @Getter
    @Setter
    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    private List<Track> trackList;

    @Override
    public String toString() {
        return "Album(" + albumName +
                ", Artists: " + artists +
                ", Release Date: " + releaseDate + ")";
    }
}
