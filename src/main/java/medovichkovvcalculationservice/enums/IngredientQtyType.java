package medovichkovvcalculationservice.enums;

import lombok.Getter;

/**
 * Defines type of quantity for an ingredient
 *
 * GRAM - grams (ex. 100 grams)
 * PIECE - qty of pieces (ex. 1 cake)
 * PACK - common type for package (ex. 1 box, or 1 mount)
 **/
@Getter
public enum IngredientQtyType {
    GRAM("Грамм"),
    PIECE("Штука"),
    PACK("Упаковка");

    private final String description;

    IngredientQtyType(String description) {
        this.description = description;
    }
}
