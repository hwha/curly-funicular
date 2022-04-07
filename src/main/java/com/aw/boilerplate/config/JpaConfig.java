package com.aw.boilerplate.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaProperties jpaProperties, HibernateProperties hibernateProperties) {

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
        entityManagerFactory.setPersistenceUnitName("entityManagerUnit");

        String[] splited = this.getClass().getPackageName().split("\\.");
        String targetPackage = String.join(".", Arrays.copyOfRange(splited, 0, splited.length - 2)) + ".**";
        entityManagerFactory.setPackagesToScan(targetPackage);

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        jpaVendorAdapter.setShowSql(jpaProperties.isShowSql());
        if (jpaProperties.getDatabase() != null) {
            jpaVendorAdapter.setDatabase(jpaProperties.getDatabase());
        }
        if (jpaProperties.getDatabasePlatform() != null) {
            jpaVendorAdapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
        }
        var properties= jpaProperties.getProperties();
        properties.put("hibernate.physical_naming_strategy", hibernateProperties.getNaming().getPhysicalStrategy());
        properties.put("hibernate.implicit_naming_strategy", hibernateProperties.getNaming().getImplicitStrategy());
        properties.put("hibernate.hbm2ddl.auto",hibernateProperties.getDdlAuto());
        jpaProperties.setOpenInView(jpaProperties.getOpenInView());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setJpaPropertyMap(properties);
        entityManagerFactory.afterPropertiesSet();

        return entityManagerFactory;
    }
}