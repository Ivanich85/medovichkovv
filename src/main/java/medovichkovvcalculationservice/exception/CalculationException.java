package medovichkovvcalculationservice.exception;

/**
 * @author ivand on 07.10.2020
 */
public class CalculationException extends RuntimeException {

    public CalculationException(String errorMessage) {
        super(errorMessage);
    }
}
