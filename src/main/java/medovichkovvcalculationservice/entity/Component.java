package medovichkovvcalculationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medovichkovvcalculationservice.enums.ComponentType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author ivand on 10.08.2020
 */
@Entity
@Table(name = "MC_COMPONENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Component implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "componentSeq")
    @SequenceGenerator(name = "componentSeq", sequenceName = "MC_COMPONENT_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RECIPE_ID", nullable = false)
    private Recipe recipe;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ComponentType type;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @OneToMany(mappedBy = "component", fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return recipe.equals(component.recipe) &&
                name.equals(component.name) &&
                type == component.type &&
                quantity.equals(component.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, name, type, quantity);
    }
}
