package storeTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import store.controller.AlbumController;
import store.dao.AlbumDAO;
import store.dao.AlbumDAOImpl;
import store.entity.Album;
import store.entity.Artist;
import store.entity.Track;

import javax.persistence.EntityManager;
import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.Properties;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        AlbumControllerGlobalMockMvcTest.Config.class,
        AlbumController.class,
})
@WebAppConfiguration
@EnableWebMvc
@TestPropertySource("classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumControllerGlobalMockMvcTest {
    private MockMvc mockMvc;
    @Autowired
    private EntityManager em;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    AlbumDAO albumDAO;

    @BeforeAll
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Album album = new Album();
        album.setAlbumName("Звезда по имени Солнце");
        album.setReleaseDate(1989);
        Album album1 = new Album();
        album1.setAlbumName("Гранатовый альбом");
        album1.setReleaseDate(1998);
        Artist kino = new Artist();
        kino.setArtistName("группа Кино");
        kino.setAlbums(Collections.singletonList(album));
        Track track = new Track();
        track.setTrackName("Пачка сигарет");
        track.setAlbum(album);
        track.setArtists(Collections.singletonList(kino));
        track.setPlayTime(Duration.parse("PT4M28S"));
        em.getTransaction().begin();
        em.persist(track);
        em.persist(album1);
        em.getTransaction().commit();
    }

    @AfterAll
    public void postTest() {
        em.close();
    }

    @Test
    public void testRequestFor_GetAllEntities() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/albums/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testRequestFor_GetEntityById_AndComparisonId() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders
                .get("/albums/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testRequestFor_DeleteEntityById() throws Exception {
        Long id = 2L;
        mockMvc.perform(MockMvcRequestBuilders
                .get("/albums/delete/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void testRequestFor_UpdateAlbum() throws Exception {
        Long id = 1L;
        ObjectMapper mapper = new ObjectMapper();
        Album album = albumDAO.getEntityById(id);
        album.setAlbumName("Test Update Method in Controller");
        String json = mapper.writeValueAsString(album);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/albums/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testRequstFor_AddAlbum() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Album album = new Album();
        album.setAlbumName("Test Create Method in Controller");
        album.setReleaseDate(2020);
        String json = mapper.writeValueAsString(album);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/albums/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Configuration
    static class Config {
        @Autowired
        private Environment env;

        @Bean
        public EntityManager em() {
            return Objects.requireNonNull(entityManagerFactory().getObject()).createEntityManager();
        }

        @Bean
        public AlbumDAO albumDAO(EntityManager em) {
            return new AlbumDAOImpl(em);
        }


        @Bean("entityManagerFactory")
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
            entityManagerFactory.setPackagesToScan("store.entity");
            entityManagerFactory.setJpaProperties(hibernateJpaProperties());
            return entityManagerFactory;
        }

        private Properties hibernateJpaProperties() {
            Properties props = new Properties();
            props.put(org.hibernate.cfg.Environment.DRIVER, env.getProperty("spring.datasource.driverClassName"));
            props.put(org.hibernate.cfg.Environment.URL, env.getProperty("spring.datasource.url"));
            props.put(org.hibernate.cfg.Environment.SHOW_SQL, "true");
            props.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
            props.put(org.hibernate.cfg.Environment.USER, env.getProperty("spring.datasource.username"));
            props.put(org.hibernate.cfg.Environment.PASS, env.getProperty("spring.datasource.password"));
            return props;
        }
    }
}
