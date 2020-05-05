import dao.*;
import entity.Album;
import entity.Artist;
import entity.Genre;
import entity.Track;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JsonConverterTest {
    public static final String FILE_NAME = "testSerialize";
    JsonConverter converter = new JsonConverter();

    @BeforeAll
    public void setUp() {
        Album album = new Album();
        album.setAlbumName("Звезда по имени Солнце");
        album.setReleaseDate("1989");

        Album album1 = new Album();
        album1.setAlbumName("Гранатовый альбом");
        album1.setReleaseDate("1998");

        Album album2 = new Album();
        album2.setAlbumName("Новый альбом");
        album2.setReleaseDate("2012");

        Album album3 = new Album();
        album3.setAlbumName("Hajime pt.3");
        album3.setReleaseDate("2013");

        Artist kino = new Artist();
        kino.setArtistName("группа Кино");
        kino.setAlbums(Collections.singleton(album));

        Artist splin = new Artist();
        splin.setArtistName("группа Сплин");
        splin.setAlbums(Collections.singleton(album1));

        Artist noize = new Artist();
        noize.setArtistName("NoizeMC");
        noize.setAlbums(Collections.singleton(album2));

        Artist myiagi = new Artist();
        myiagi.setArtistName("Miyagi");
        myiagi.setAlbums(Collections.singleton(album3));

        Artist endshp = new Artist();
        endshp.setArtistName("Эндшпиль");
        endshp.setAlbums(Collections.singleton(album3));

        Artist tumani = new Artist();
        tumani.setArtistName("TumaniYO");

        Genre rRap = new Genre();
        rRap.setGenreName("русский рэп");

        Genre rRock = new Genre();
        rRock.setGenreName("русский рок");

        Track track = new Track();
        track.setTrackName("Пачка сигарет");
        track.setAlbum(album);
        track.setArtists(Collections.singleton(kino));
        track.setPlayTime(Duration.parse("PT4M28S"));
        track.setGenre(rRock);

        Track track1 = new Track();
        track1.setTrackName("Выхода нет");
        track1.setAlbum(album1);
        track1.setArtists(Collections.singleton(splin));
        track1.setPlayTime(Duration.parse("PT3M47S"));
        track1.setGenre(rRock);

        Track track2 = new Track();
        track2.setTrackName("Вселенная бесконечна?");
        track2.setAlbum(album2);
        track2.setArtists(Collections.singleton(noize));
        track2.setPlayTime(Duration.parse("PT4M20S"));
        track2.setGenre(rRap);

        Track track3 = new Track();
        track3.setTrackName("Fuck the money");
        track3.setAlbum(album3);
        track3.setArtists(Arrays.asList(myiagi, endshp, tumani));
        track3.setPlayTime(Duration.parse("PT4M11S"));
        track3.setGenre(rRap);

        Track track4 = new Track();
        track4.setTrackName("Колизей");
        track4.setAlbum(album3);
        track4.setArtists(Arrays.asList(myiagi, endshp));
        track4.setPlayTime(Duration.parse("PT4M27S"));
        track4.setGenre(rRap);

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(EntityManagerFactorySingleton.FILE_NAME);
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(track);
        em.persist(track1);
        em.persist(track2);
        em.persist(track3);
        em.persist(track4);
        em.getTransaction().commit();
        em.close();
        entityManagerFactory.close();
    }

    @Test
    public void testSerializeGenreByJacksonNotNull() {
        GenreController genreController = new GenreController();
        assertNotNull(converter.convertObjectToString(genreController.getEntityById(2L)));
    }

    @Test
    public void testSerializeArtistByJacksonNotNull() {
        ArtistController artistController = new ArtistController();
        assertNotNull(converter.convertObjectToString(artistController.getEntityById(1L)));
    }

    @Test
    public void testSerializeAlbumByJacksonNotNull() {
        AlbumController albumController = new AlbumController();
        assertNotNull(converter.convertObjectToString(albumController.getEntityById(1L)));
    }

    @Test
    public void testSerializeGenreInFile() {
        GenreController genreController = new GenreController();
        List<Genre> genre = genreController.getAll();
        genre.forEach(o -> o.setGenreName("testSerializeGenreInFile"));
        converter.convertObjectToJsonFile(genre, FILE_NAME);
    }

    @Test
    public void testConvertJSONFileToListOfOjectsAndUploadingInDB() {
        List<Genre> genre = converter.convertJSONFileToListOfOjects(FILE_NAME, Genre.class);
        GenreController genreController = new GenreController();
        genre.forEach(o -> o.setGenreName("testConvertJSONFileToListOfOjectsAndUploadingInDB"));
        genre.forEach(genreController::update);
    }

    @Test
    public void testConvertStringToObjectAndUploadingInDB() {
        TrackController trackController = new TrackController();
        String str = converter.convertObjectToString(trackController.getEntityById(1L));
        Track track = converter.convertStringToObject(str, Track.class);
        track.setTrackName("convertStringToObjectTest");
        trackController.update(track);
    }
}
