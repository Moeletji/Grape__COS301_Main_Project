package financialmarketsimulator.exception;

/**
 * @brief This exception class handles cases where an order is made without the
 * required values, or if an order is modified and made to not have the required
 * values. If such happens, then this class will be raised instead as the
 * exception.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class OrderHasNoValuesException extends Exception {

    public OrderHasNoValuesException() {
        super("Order Has No Values Exception");
    }

}
