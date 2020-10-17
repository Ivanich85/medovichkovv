package medovichkovvcalculationservice.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ivand on 18.10.2020
 */
@Configuration("databaseConfig")
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/medovichkovv")
                .username("postgres")
                .password("postgres")
                .build();
    }
}
