package medovichkovvcalculationservice;

/**
 * @author ivand on 22.10.2020
 */
public class SecurityUtils {
    public static final Long USER_ID = 1000L;

    public static Long getCurrentUser() {
        return USER_ID;
    }
}
