package com.noduco.RestWithCamel.config;

import org.apache.camel.component.sql.SqlComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/noduco?useSSL=false");
        dataSource.setPassword("rootpass");
        dataSource.setUsername("root");

        return dataSource;

    }

    @Bean(name = "sql")
    public SqlComponent getSqlComponent(@Autowired DataSource dataSource) {
        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setDataSource(dataSource);
        return sqlComponent;
    }

}
