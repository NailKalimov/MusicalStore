import UtilityClass.DbInitialState;
import entity.Album;
import entity.Genre;
import dao.AlbumDAO;
import dao.ArtistDAO;
import dao.GenreDAO;
import dao.TrackDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
    static EntityManager em = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        AlbumDAO albumDAO = new AlbumDAO(em);
        ArtistDAO artistDAO = new ArtistDAO(em);
        GenreDAO genreDAO = new GenreDAO(em);
        TrackDAO trackDAO = new TrackDAO(em);
        DbInitialState.setUp();

        //Получим из БД данные о треке по его id
        System.out.println(trackDAO.getEntityById(1L));

        //Удалим исполнителя по id
        artistDAO.deleteById(2L);

        //Создадим новый жанр
        Genre newGenre = new Genre();
        newGenre.setGenreName("Created Genre");
        genreDAO.create(newGenre);

        //Обновим информацию о существующем альбоме по его id
        Album albumForUpdate = new Album();
        albumForUpdate.setAlbumId(1L);
        albumForUpdate.setAlbumName("Звезда по имени Солнце(updated)");
        albumDAO.update(albumForUpdate);
    }
}
