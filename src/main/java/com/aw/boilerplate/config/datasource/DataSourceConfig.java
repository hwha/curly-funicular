package com.aw.boilerplate.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    @Primary
    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

    @Bean
    public DataSource routingDataSource() {
        final DataSource writerDatasource = writerDatasource();
        final DataSource readDataSource = readerDataSource();
        return new RoutingDataSource(writerDatasource, readDataSource);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource writerDatasource() {
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource readerDataSource() {
        return new HikariDataSource();
    }
}