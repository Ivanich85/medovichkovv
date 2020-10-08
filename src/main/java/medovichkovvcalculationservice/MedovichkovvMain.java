package medovichkovvcalculationservice;

import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.repository.RecipeRepository;
import medovichkovvcalculationservice.service.impl.RecipeServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author ivand on 12.07.2020
 */
public class MedovichkovvMain {
    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring/app-config.xml");
        RecipeRepository repository = (RecipeRepository) ac.getBean("recipeRepository");
        RecipeServiceImpl service = (RecipeServiceImpl) ac.getBean("recipeServiceImpl");
        List<Recipe> recipes = service.getAll();
        ac.close();
    }
}
