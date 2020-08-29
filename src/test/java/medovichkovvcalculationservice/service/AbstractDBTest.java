package medovichkovvcalculationservice.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Configuration for DB tests
 *
 * @author ivand on 22.07.2020
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/test-config.xml"})
@Sql(scripts = {"classpath:database/create_tables.sql", "classpath:database/populate"}, config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractDBTest {

    protected static final Long userId = 1L;
    protected static final Long recipeId = 100L;
    protected static final Long componentId = 1000L;
    protected static final Long ingredientId = 10000L;

    protected static final List<Long> EXPECTED_IDS = List.of(recipeId);
}
