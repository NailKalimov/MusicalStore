package entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Album.class)
public class Album extends AbstractIdentityObject {
    @Getter
    @Setter
    private String albumName;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "albums")
    private Collection<Artist> artists;

    @Getter
    @Setter
    private String releaseDate;

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
