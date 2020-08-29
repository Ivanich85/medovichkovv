package medovichkovvcalculationservice.service;

import org.springframework.util.Assert;

/**
 * @author ivand on 23.07.2020
 */
public class ServiceUtils {

    public static <T> void notNull(T object, String varName) {
        Assert.notNull(object, String.format("%s can not be null", varName));
    }
}
