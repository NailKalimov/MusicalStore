package store.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Album.class)
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String albumName;

    @ManyToMany(mappedBy = "albums", fetch = FetchType.EAGER)
    private List<Artist> artists;

    private int releaseDate;

    @OneToMany(mappedBy = "album", fetch = FetchType.EAGER)
    private List<Track> trackList;

    @Override
    public String toString() {
        return "Album(" + albumName +
                ", Artists: " + artists +
                ", Release Date: " + releaseDate + ")";
    }
}
