package medovichkovvcalculationservice.entities;

/**
 * @author ivand on 12.07.2020
 */

import lombok.*;
import medovichkovvcalculationservice.enums.IngredientQtyType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Component`s ingredient
 */
@Entity
@Table(name="MC_INGREDIENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredientSeq")
    @SequenceGenerator(name = "ingredientSeq", sequenceName = "MC_INGREDIENT_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPONENT_ID", nullable = false)
    private Component component;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "QUANTITY", nullable = false)
    private Double weight;

    @Column(name = "QUANTITY_TYPE", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private IngredientQtyType type;

    @Column(name = "COST", nullable = false)
    private BigDecimal cost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return component.equals(that.component) &&
                name.equals(that.name) &&
                weight.equals(that.weight) &&
                type == that.type &&
                cost.equals(that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(component, name, weight, type, cost);
    }
}