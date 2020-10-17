package medovichkovvcalculationservice.testconfiguration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ivand on 18.10.2020
 */
@Configuration("testDatabaseConfig")
public class TestDataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/medovichkovv-test")
                .username("postgres")
                .password("postgres")
                .build();
    }
}
