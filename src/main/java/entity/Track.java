package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Duration;
import java.util.Collection;

@Entity
@ToString
public class Track {
    @Id
    @Column(name = "id_track")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    Long trackId;

    @Getter
    @Setter
    private String trackName;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "TRACKS_AND_ARTISTS",
            joinColumns = @JoinColumn(name = "TRACK"),
            inverseJoinColumns = @JoinColumn(name = "ARTIST")
    )
    private Collection<Artist> artists;

    @Getter
    @Setter
    private Duration playTime;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "album")
    private Album album;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre")
    private Genre genre;

}
