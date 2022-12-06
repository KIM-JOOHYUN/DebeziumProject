package com.example.debeziumproject.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:application.yml"})
@EnableJpaRepositories(
        basePackages = "com.example.debeziumproject.repository",
        entityManagerFactoryRef = "target1EntityManager",
        transactionManagerRef = "target1TranscationManager"
)
public class Target1Conf {
    @Autowired
    private Environment env;
    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSourceProperties Target1Properties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource target1DataSource(DataSourceProperties properties) {
        //DataSourceBuilder.create().build();
        return properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean target1EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter hbAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(hbAdapter);

        em.setDataSource(target1DataSource(Target1Properties()));
        em.setPackagesToScan("com.example.debeziumproject.entity"); // database entity package path
        em.setJpaProperties(jpaProperties());

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager target1TranscationManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();

        final String rootPath = "spring.jpa.properties.hibernate";

        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty(rootPath + ".hbm2ddl.auto"));
        properties.setProperty("hibernate.dialect", env.getProperty(rootPath + ".dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty(rootPath + ".show_sql"));
        properties.setProperty("hibernate.format_sql", env.getProperty(rootPath + ".format_sql"));
        properties.setProperty("hibernate.enable_lazy_load_no_trans",
                env.getProperty(rootPath + ".enable_lazy_load_no_trans"));

        return properties;
    }
}
