import entity.Album;
import entity.Artist;
import entity.Genre;
import entity.Track;
import dao.AlbumController;
import dao.ArtistController;
import dao.GenreController;
import dao.TrackController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Duration;
import java.time.Year;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        AlbumController albumController = new AlbumController();
        ArtistController artistController = new ArtistController();
        GenreController genreController = new GenreController();
        TrackController trackController = new TrackController();

        setUpDB();
        //Получим из БД данные о треке по его id
        Track track = trackController.getEntityById(1L);
        System.out.println(track);

        //Удалим исполнителя по id
        artistController.deleteById(2L);

        //Создадим новый жанр
        Genre newGenre = new Genre();
        newGenre.setGenreName("Created Genre");
        genreController.create(newGenre);

        //Обновим информацию о существующем альбоме по его id
        Album albumForUpdate = new Album();
        albumForUpdate.setAlbumId(1L);
        albumForUpdate.setAlbumName("Звезда по имени Солнце(updated)");
        albumForUpdate.setTrackList(Collections.singletonList(new Track()));
        albumController.update(albumForUpdate);

    }

    /**
     * Заполнение базы данными
     */
    public static void setUpDB(){
        EntityManagerFactory entityManagerFactory; //= Persistence.createEntityManagerFactory("entityManager");
        Album album = new Album();
        album.setAlbumName("Звезда по имени Солнце");
        album.setReleaseDate(Year.of(1989));

        Album album1 = new Album();
        album1.setAlbumName("Гранатовый альбом");
        album1.setReleaseDate(Year.of(1998));

        Album album2 = new Album();
        album2.setAlbumName("Новый альбом");
        album2.setReleaseDate(Year.of(2012));

        Album album3 = new Album();
        album3.setAlbumName("Hajime pt.3");
        album3.setReleaseDate(Year.of(2013));

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

        entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(track);
        em.persist(track1);
        em.persist(track2);
        em.persist(track3);
        em.persist(track4);
        em.getTransaction().commit();
        em.close();
    }
}
