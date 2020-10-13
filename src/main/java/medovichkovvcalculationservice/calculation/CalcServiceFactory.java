package medovichkovvcalculationservice.calculation;

import medovichkovvcalculationservice.calculation.service.CalculationService;
import medovichkovvcalculationservice.calculation.service.impl.CakeCalculationService;
import medovichkovvcalculationservice.calculation.service.impl.SquareCalculationService;

/**
 * Factory for different type of calculation services
 *
 * @author ivand on 30.09.2020
 */
public abstract class CalcServiceFactory {

    public static CalculationService getCalcService(ServiceType serviceType) {
        switch (serviceType) {
            case SQUARE:
                return new SquareCalculationService();
            case CAKE:
                return new CakeCalculationService();
            default:
                throw new IllegalStateException(String.format("Calculation service for type %s doesn`t exist", serviceType));
        }
    }
}
