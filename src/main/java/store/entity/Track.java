package store.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Track.class)
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String trackName;

    @ManyToMany(fetch = FetchType.LAZY, cascade ={CascadeType.PERSIST})
    @JoinTable(name = "TRACKS_AND_ARTISTS",
            joinColumns = @JoinColumn(name = "TRACK"),
            inverseJoinColumns = @JoinColumn(name = "ARTIST"))
    private List<Artist> artists;

    private String playTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "album")
    private Album album;
}
