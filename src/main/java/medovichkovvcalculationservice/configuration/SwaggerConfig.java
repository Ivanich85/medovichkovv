package medovichkovvcalculationservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author ivand on 12.01.2021
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api()  {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(createInfo());
    }
    
    private ApiInfo createInfo() {
        Contact contact = new Contact("Ivan Dmitriev", "", "ivan.dmitriev85@gmail.com");

        return new ApiInfo("Medovichkovv calculation service",
                "Save, edit and recalculate recipes",
                "1.0",
                "No terms",
                contact,
                "All rights reserved by Medovichkovv Corporation",
                "license@smth.com",
                new ArrayList<>());
    }
}
