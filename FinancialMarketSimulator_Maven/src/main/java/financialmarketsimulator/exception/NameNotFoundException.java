package financialmarketsimulator.exception;

/**
 * @brief This exception class handles cases where a name is searched but cannot
 * be found. If it does so happen that a name is searched and cannot be found,
 * then this exception class is thrown as a result.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class NameNotFoundException extends Exception {

    public NameNotFoundException() {
        super("Name Not Found Exception");
    }
}
