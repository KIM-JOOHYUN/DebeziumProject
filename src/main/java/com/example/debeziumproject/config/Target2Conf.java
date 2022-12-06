package com.example.debeziumproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
import java.util.HashMap;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:application.yml"})
@EnableJpaRepositories(
        basePackages = "com.example.debeziumproject.repository2",
        entityManagerFactoryRef = "target2EntityManager",
        transactionManagerRef = "target2TranscationManager"
)
public class Target2Conf {
    @Autowired
    private Environment env;

    @Bean
    @ConfigurationProperties(prefix="spring.datasource2")
    public DataSource target2DataSource() {
        //DataSourceBuilder.create().build();
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean target2EntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(target2DataSource());
        em.setPackagesToScan("com.example.debeziumproject.entity");

        HibernateJpaVendorAdapter hbAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(hbAdapter);

         // database entity package path
        em.setJpaPropertyMap(jpaProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager target2TranscationManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(target2EntityManager().getObject());
        return transactionManager;
    }

    private HashMap<String, Object> jpaProperties() {
        HashMap<String, Object> properties = new HashMap<>();

        final String rootPath = "spring.jpa.properties.hibernate";

//        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", env.getProperty(rootPath + ".show_sql"));
        properties.put("hibernate.format_sql", env.getProperty(rootPath + ".format_sql"));
        properties.put("hibernate.enable_lazy_load_no_trans",
                env.getProperty(rootPath + ".enable_lazy_load_no_trans"));

        return properties;
    }
}
