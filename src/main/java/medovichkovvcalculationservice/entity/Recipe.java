package medovichkovvcalculationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medovichkovvcalculationservice.enums.PrivacyType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author ivand on 12.07.2020
 */

/**
 * Recipe
 */
@Entity
@Table(name = "MC_RECIPE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipeSeq")
    @SequenceGenerator(name = "recipeSeq", sequenceName = "MC_RECIPE_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "recipe")
    private List<Component> components = new ArrayList<>();

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SQUARE", nullable = false)
    private BigDecimal square;

    @Column(name = "CAKE", nullable = false)
    private Integer cakes;

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "FAVORITE")
    private boolean isFavorite;

    @Column(name = "PRIVACY", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PrivacyType privacyType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return isFavorite == recipe.isFavorite &&
                userId.equals(recipe.userId) &&
                name.equals(recipe.name) &&
                Objects.equals(square, recipe.square) &&
                Objects.equals(cakes, recipe.cakes) &&
                privacyType == recipe.privacyType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, square, cakes, isFavorite, privacyType);
    }
}
