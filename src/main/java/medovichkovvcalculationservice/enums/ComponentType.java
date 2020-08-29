package medovichkovvcalculationservice.enums;

import lombok.Getter;

/**
 * Defines type of a component
 **/
public enum ComponentType {
    CREAM("Крем"),
    CUSTARD("Заварной крем"),
    CAKE("Корж"),
    FRUIT("Фрукт"),
    PACK("Упаковка");

    @Getter
    private String desc;

    private ComponentType(String desc) {
        this.desc = desc;
    }
}
