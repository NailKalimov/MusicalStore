//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import store.entity.Album;
//import store.entity.Artist;
//import store.entity.Track;
//
//import javax.persistence.EntityManager;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Optional;
//
//
//@DataJpaTest
//public class TestJPA {
//
//    @Autowired
//    private EntityManager em;
//
//    @Test
//    void initIt() {
//        Album album = new Album();
//        album.setAlbumName("Звезда по имени Солнце");
//        album.setReleaseDate(1989);
//
//        Album album1 = new Album();
//        album1.setAlbumName("Гранатовый альбом");
//        album1.setReleaseDate(1998);
//
//        Album album2 = new Album();
//        album2.setAlbumName("Новый альбом");
//        album2.setReleaseDate(2012);
//
//        Album album3 = new Album();
//        album3.setAlbumName("Hajime pt.3");
//        album3.setReleaseDate(2013);
//
//        Artist kino = new Artist();
//        kino.setArtistName("группа Кино");
//        kino.setAlbums(Collections.singletonList(album));
//
//        Artist splin = new Artist();
//        splin.setArtistName("группа Сплин");
//        splin.setAlbums(Collections.singletonList(album1));
//
//        Artist noize = new Artist();
//        noize.setArtistName("NoizeMC");
//        noize.setAlbums(Collections.singletonList(album2));
//
//        Artist myiagi = new Artist();
//        myiagi.setArtistName("Miyagi");
//        myiagi.setAlbums(Collections.singletonList(album3));
//
//        Artist endshp = new Artist();
//        endshp.setArtistName("Эндшпиль");
//        endshp.setAlbums(Collections.singletonList(album3));
//
//        Artist tumani = new Artist();
//        tumani.setArtistName("TumaniYO");
//
//        Track track = new Track();
//        track.setTrackName("Пачка сигарет");
//        track.setAlbum(album);
//        track.setArtists(Collections.singletonList(kino));
//        track.setPlayTime("4m28s");
//
//        Track track1 = new Track();
//        track1.setTrackName("Выхода нет");
//        track1.setAlbum(album1);
//        track1.setArtists(Collections.singletonList(splin));
//        track1.setPlayTime("3m47s");
//
//        Track track2 = new Track();
//        track2.setTrackName("Вселенная бесконечна?");
//        track2.setAlbum(album2);
//        track2.setArtists(Collections.singletonList(noize));
//        track2.setPlayTime("4m20s");
//
//        Track track3 = new Track();
//        track3.setTrackName("Fuck the money");
//        track3.setAlbum(album3);
//        track3.setArtists(Arrays.asList(myiagi, endshp, tumani));
//        track3.setPlayTime("4m11s");
//
//        Track track4 = new Track();
//        track4.setTrackName("Колизей");
//        track4.setAlbum(album3);
//        track4.setArtists(Arrays.asList(myiagi, endshp));
//        track4.setPlayTime("4m27s");
//
//        em.getTransaction().begin();
//        em.persist(track);
//        em.persist(track1);
//        em.persist(track2);
//        em.persist(track3);
//        em.persist(track4);
//        em.getTransaction().commit();
//        em.close();
//    }
//}
