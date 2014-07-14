package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import java.util.ArrayList;

/**
 *
 * @brief Moving Average Envelope Strategy
 */
public class MovingAverageEnvelope {

    //percent values for the envelope
    private final double short_term = 2.5;
    private final double medium_term = 5;
    private final double long_term = 10;

    //preiod for moving average
    private int num_days = 10;

    //closing price
    private double closingPrice;
    
    private ArrayList<Double> pastSMAValues;
    private ArrayList<Double> pastEMAValues;

    private EMA ema;
    private SMA sma;
    
    MarketEntryAttemptBook book;

    public MovingAverageEnvelope(MarketEntryAttemptBook _book) throws NotEnoughDataException {
        if (_book != null)
        {
            this.book = _book;
            //ema = new EMA(num_days);
            sma = new SMA(num_days, book);
        }
        else throw new NotEnoughDataException();
    }
    
    /**
     * 
     * @param percentage the value to determine the size of the envelope
     * @return the upper envelope value
     * @throws NotEnoughDataException 
     */
    private double calculateSMAUpperEvelope(double percentage) throws NotEnoughDataException
    {
        return getSMA() + (getSMA()*percentage/100);
    }
    
    /**
     * 
     * @param percentage the value to determine the size of the envelope
     * @return the lower envelope value
     * @throws NotEnoughDataException 
     */
    private double calculateSMALowerEvelope(double percentage) throws NotEnoughDataException
    {
        return getSMA() - (getSMA()*percentage/100);
    }
    
    /**
     * @brief calculate the simple moving average using the sma object
     * @return the simple moving average
     * @throws NotEnoughDataException 
     */
    private double getSMA() throws NotEnoughDataException
    {
        return sma.calculateSMA();
    }
    
    /**
     * @brief generate a market event depending on whether the instrument is
     * oversold/overbought
     */
    public void generateMarketEntryAttempt()
    {
        
    }
    
    public void setClosingPrice(double closing)
    {
        //this.closingPrice = book.getMatchedOrders().lastElement().getPrice();
        this.closingPrice = closing;
    }
    
    public double getClosingPrice()
    {
        //return book.getMatchedOrders().lastElement().getPrice();
        return closingPrice;
    }
}
