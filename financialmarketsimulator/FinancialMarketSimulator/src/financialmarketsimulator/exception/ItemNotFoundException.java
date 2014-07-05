package financialmarketsimulator.exception;

/**
 * @brief This class handles all ItemNotFoundExceptions. Be it the item is 
 * within a data structure, or it is within anything else and cannot be located, 
 * this class can be raised as the exception to indicate that the item could not
 * be found.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        super("Item Not Found Exception");
    }
}
