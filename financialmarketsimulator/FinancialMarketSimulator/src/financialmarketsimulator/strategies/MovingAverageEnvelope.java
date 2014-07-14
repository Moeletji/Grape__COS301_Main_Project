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
    
    private double calculateSMAUpperEvelope(double percentage) throws NotEnoughDataException
    {
        return getSMA()+ (getSMA()*percentage/100);
    }
    
    private double calculateSMALowerEvelope(double percentage) throws NotEnoughDataException
    {
        return getSMA()+ (getSMA()*percentage/100);
    }
    
    private double getSMA() throws NotEnoughDataException
    {
        return sma.calculateSMA();
    }

    public void generateMarketEntryAttempt()
    {
        
    }
    
    public void setClosingPrice(double closing)
    {
        this.closingPrice = closing;
    }
    
    public double getClosingPrice()
    {
        return closingPrice;
    }
}
