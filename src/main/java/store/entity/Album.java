package store.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Album.class)
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String albumName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ARTISTS_AND_ALBUMS",
            joinColumns = @JoinColumn(name = "ALBUM"),
            inverseJoinColumns = @JoinColumn(name = "ARTIST")
    )
    private List<Artist> artists;

    private int releaseDate;

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Track> trackList;

    @Override
    public String toString() {
        return "Album(" + albumName +
                ", Artists: " + artists +
                ", Release Date: " + releaseDate + ")";
    }
}
