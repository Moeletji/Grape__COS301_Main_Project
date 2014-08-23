package financialmarketsimulator.exception;

/**
 * @brief This exception class is thrown when a name for a stock already exists
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class StockAlreadyExistsException extends Exception{
    
    public StockAlreadyExistsException(){
        super("Stock Already Exists Exception");
    }
}
