package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Genre {
    @Id
    @Column(name = "id_genre")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Setter
    @Getter
    Long genreId;

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
