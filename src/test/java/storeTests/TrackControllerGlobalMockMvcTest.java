package storeTests;

import TestConfiguration.Config;
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
import store.controller.TrackController;
import store.dao.TrackDAO;
import store.dao.TrackDAOImpl;
import store.entity.Album;
import store.entity.Artist;
import store.entity.Track;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.Objects;
import java.util.Properties;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        Config.class,
        TrackController.class
})
@WebAppConfiguration
@EnableWebMvc
@TestPropertySource("classpath:application.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrackControllerGlobalMockMvcTest {
    private MockMvc mockMvc;
    @Autowired
    EntityManagerFactory entityManagerFactory;
    @Autowired
    private EntityManager em;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    TrackDAO trackDAO;

    @BeforeAll
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Album album = new Album();
        album.setAlbumName("Звезда по имени Солнце");
        album.setReleaseDate(1989);
        Artist kino = new Artist();
        kino.setArtistName("группа Кино");
        kino.setAlbums(Collections.singletonList(album));
        Track track = new Track();
        track.setTrackName("Пачка сигарет");
        track.setAlbum(album);
        track.setArtists(Collections.singletonList(kino));
        track.setPlayTime("4m28s");
        Track track1 = new Track();
        track1.setTrackName("Выхода нет");
        track1.setPlayTime("3m47s");
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(track);
        em.persist(track1);
        em.getTransaction().commit();
    }

    @AfterAll
    public void postTest() {
        em.close();
    }

    @Test
    public void testRequestFor_GetAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/tracks/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testRequestFor_GetById() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders
                .get("/tracks/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testRequestFor_DeleteById_AndExpectResponse() throws Exception {
        Long id = 2L;
        mockMvc.perform(MockMvcRequestBuilders
                .get("/tracks/delete/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    public void testRequestFor_UpdateTrack() throws Exception {
        Track track = trackDAO.getEntityById(1L);
        track.setTrackName("Test Update Method in Controller");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(track);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/tracks/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testRequestFor_AddTrack() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Track track = new Track();
        track.setTrackName("Test Add Method in Controller");
        track.setPlayTime("some times");
        String json = objectMapper.writeValueAsString(track);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/tracks/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print()).andExpect(status().isOk());
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
        public TrackDAO trackDAO(EntityManager em) {
            return new TrackDAOImpl(em);
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
