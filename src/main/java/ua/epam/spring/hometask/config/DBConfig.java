package ua.epam.spring.hometask.config;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class DBConfig {
    // jdbc:h2:mem:testdb
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("database/create-db.sql")
                .addScript("database/insert-data.sql")
                .build();
        return db;
    }

    @Bean
    public JdbcTemplate getNamedParameterJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @PostConstruct
    public void startDBManager() throws Exception {
        System.out.println("hey");
        Class.forName("org.h2.Driver");

        //h2
        DatabaseManagerSwing.main(new String[]{"--url", "jdbc:h2:mem:testdb", "--user", "sa", "--password", ""});

        System.out.println("hey");

    }

}