package medovichkovvcalculationservice.error;

/**
 * @author ivand on 07.10.2020
 */
public class CalculationError extends RuntimeException {

    private CalculationError(String errorMessage) {
        super(errorMessage);
    }

    public static CalculationError create(String errorMessage) {
        return new CalculationError(errorMessage);
    }
}
