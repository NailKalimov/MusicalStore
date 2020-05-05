package entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Genre.class)
@Entity
public class Genre extends AbstractIdentityObject {
    @Getter
    @Setter
    private String genreName;

    @Getter
    @Setter
    @OneToMany(mappedBy = "genre", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Track> tracks;

    @Override
    public String toString() {
        return "Genre: " + genreName;
    }
}
