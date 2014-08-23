package financialmarketsimulator.exception;

/**
 * @brief This class handles exceptions where a new name is to be assigned to an
 * item, but the same name has already been assigned to another item. In such a
 * case, the name will not be assigned to the new item and this exception class
 * will be raised instead.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class NameAlreadyExistsException extends Exception {

    public NameAlreadyExistsException() {
        super("Name Already Exists Exception");
    }

}
