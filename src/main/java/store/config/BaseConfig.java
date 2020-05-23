package store.config;

import store.dao.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Objects;
import java.util.Properties;

@Configuration
public class BaseConfig {
    private final Environment env;

    public BaseConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public EntityManager em() {
        return Objects.requireNonNull(entityManagerFactory().getObject()).createEntityManager();
    }

    @Bean
    public AlbumDAO albumDAO(EntityManager em) {
        return new AlbumDAOImpl(em);
    }

    @Bean
    public ArtistDAO artistDAO(EntityManager em) {
        return new ArtistDAOImpl(em);
    }

    @Bean
    public TrackDAO trackDAO(EntityManager em) {
        return new TrackDAOImpl(em);
    }

    @Bean
    public JpaTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;
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
        props.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty("spring.jpa.hibernate.ddl-auto"));
        props.put(org.hibernate.cfg.Environment.USER, env.getProperty("spring.datasource.username"));
        props.put(org.hibernate.cfg.Environment.PASS, env.getProperty("spring.datasource.password"));
        return props;
    }

}
