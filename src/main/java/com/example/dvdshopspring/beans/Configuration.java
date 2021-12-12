package com.example.dvdshopspring.beans;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.hibernate.HikariConfigurationUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    private String url;
    private String user;
    private String password;

    public Configuration(@Value("${db.url}") String url,
                         @Value("${db.user}") String user,
                         @Value("${db.password}") String password){
        this.url = url;
        this.user = user;
        this.password = password;

    }

    @Bean
    public HikariDataSource hikariDataSource (){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }
}
