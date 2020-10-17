package medovichkovvcalculationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Recipe ingredients
 *
 * @author ivand on 15.10.2020
 */
@Entity
@Table(name = "MC_RECIPE_INGREDIENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipeIngrSeq")
    @SequenceGenerator(name = "recipeIngrSeq", sequenceName = "MC_RECIPE_INGREDIENT_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "INGREDIENT_ID", nullable = false)
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "COMPONENT_ID", nullable = false)
    private Component component;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return ingredient.equals(that.ingredient) &&
                component.equals(that.component) &&
                quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, component, quantity);
    }
}
