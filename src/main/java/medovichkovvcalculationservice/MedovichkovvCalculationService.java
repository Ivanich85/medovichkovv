package medovichkovvcalculationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author ivand on 17.10.2020
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MedovichkovvCalculationService {
    public static void main(String[] args) {
        SpringApplication.run(MedovichkovvCalculationService.class, args);
    }
}
