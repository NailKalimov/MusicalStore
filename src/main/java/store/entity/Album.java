package store.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Album.class)
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    Long id;

    @Getter
    @Setter
    private String albumName;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "albums", fetch = FetchType.EAGER)
    private List<Artist> artists;

    @Getter
    @Setter
    private int releaseDate;

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
