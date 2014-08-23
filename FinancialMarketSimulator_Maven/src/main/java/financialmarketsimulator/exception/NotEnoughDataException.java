//Error reading included file Templates/Classes/Templates/Licenses/license-Financial Market Simulator Licence.txt
package financialmarketsimulator.exception;

/**
 * @brief This class handles when an exception is thrown regarding the lack of data being parsed.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class NotEnoughDataException extends Exception {
    
    public NotEnoughDataException()
    {
        super("Not Enough Data Exception");
    }
}
