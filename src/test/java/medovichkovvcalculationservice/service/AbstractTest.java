package medovichkovvcalculationservice.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * Configuration for tests
 *
 * @author ivand on 22.07.2020
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:spring/test-config.xml"})
@Sql(scripts = {"classpath:database/create_tables.sql", "classpath:database/populate"}, config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractTest {

    protected static final Long userId = 1L;
    protected static final Long recipeId = 100L;
    protected static final Long componentId = 1000L;
    protected static final Long ingredientId = 10000L;

    protected static final List<Long> EXPECTED_IDS = List.of(recipeId);
}
