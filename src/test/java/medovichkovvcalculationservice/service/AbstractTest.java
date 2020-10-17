package medovichkovvcalculationservice.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Configuration for tests
 *
 * @author ivand on 22.07.2020
 */
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(locations = {"classpath:spring/test-config.xml"})
public abstract class AbstractTest {

}
