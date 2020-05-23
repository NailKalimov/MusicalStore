//package TestConfiguration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.persistence.EntityManager;
//import java.util.Objects;
//import java.util.Properties;
//
//@Configuration
//public class Config {
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public EntityManager em() {
//        return Objects.requireNonNull(entityManagerFactory().getObject()).createEntityManager();
//    }
//
//
//
//    @Bean("entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
//        entityManagerFactory.setPackagesToScan("store.entity");
//        entityManagerFactory.setJpaProperties(hibernateJpaProperties());
//        return entityManagerFactory;
//    }
//
//    private Properties hibernateJpaProperties() {
//        Properties props = new Properties();
//        props.put(org.hibernate.cfg.Environment.DRIVER, env.getProperty("spring.datasource.driverClassName"));
//        props.put(org.hibernate.cfg.Environment.URL, env.getProperty("spring.datasource.url"));
//        props.put(org.hibernate.cfg.Environment.SHOW_SQL, env.getProperty("spring.jpa.show-sql"));
//        props.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
//        props.put(org.hibernate.cfg.Environment.USER, env.getProperty("spring.datasource.username"));
//        props.put(org.hibernate.cfg.Environment.PASS, env.getProperty("spring.datasource.password"));
//        return props;
//    }
//}