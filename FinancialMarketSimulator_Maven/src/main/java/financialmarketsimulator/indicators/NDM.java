package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 * @brief Negative Directional Movement
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class NDM extends MarketIndicator{
    
    private final MarketEntryAttemptBook book; 
    private final int numDays;
    private final Vector<Double> NMDValues;
    private Double upMove;
    private Double downMove;
    private Double result;
    
    public NDM(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Negative Directional Movement");
        this.book = _book;
        this.numDays = _numDays;
        this.NMDValues = new Vector<>();
    }
    
    /**
     * @todo MUST USE YESTERDAY's CLOSING HIGH AND LOW VALUES, INSTEAD OF THE FIRST ELEMENT!!!!
     * @return Double value representing the current NDM
     */
    public Double calculateNDM()
    {
        upMove = this.book.getHighestTradePrice(numDays) - this.book.getFirstTradePrice();
        downMove = this.book.getFirstTradePrice() - this.book.getLowestTradePrice(numDays);
        
        if( downMove > upMove && downMove > 0 )
            result = downMove;
        else
            result = 0.0;
        
        this.NMDValues.add(result);
        return result;
    }
    
    public Vector<Double> getNDMValues()
    {
        this.calculateNDM();
        return this.NMDValues;
    }

    @Override
    public Double calculateIndicator()  {
        return this.calculateNDM();
    }
}
