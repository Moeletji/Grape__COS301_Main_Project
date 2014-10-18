package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.NORMAL;
import java.util.Vector;

/**
 *
 * @brief Moving Average Envelope Strategy
 * This strategy is intended for identifying trend changes in the market. 
 *
 */
public abstract class MovingAverageEnvelope extends MarketStrategy{
    
    public static enum STRATEGY_TYPE {

        SHORT_TERM, MEDIUM_TERM, LONG_TERM
    }
    //percent values for the envelope
    protected final double short_term = 2.5;
    protected final double medium_term = 5.0;
    protected final double long_term = 10.0;

    protected double percentage;
    //preiod for moving average
    protected int num_days = 10;
    
    protected STRATEGY_TYPE type;

    //closing price
    protected double closingPrice;
    
    protected Vector<Double> pastSMAValues;
    protected Vector<Double> pastEMAValues;
    
    protected MarketEntryAttemptBook book;

    public MovingAverageEnvelope(MarketEntryAttemptBook _book,MovingAverageEnvelope.STRATEGY_TYPE _type) throws NotEnoughDataException {
        super("Moving Average Envelope");
        if (_book != null)
        {
            this.book = _book;
            pastSMAValues = new Vector<Double>();
            type = _type;
             switch (_type)
            {
                case SHORT_TERM : percentage = short_term;
                                  break;
                case MEDIUM_TERM : percentage = medium_term;
                                  break;
                case LONG_TERM : percentage = long_term;
                                  break;
                default: percentage = medium_term;
                        break;      
            }
        }
        else throw new NotEnoughDataException();
    }
    
    public void setClosingPrice(double closing)
    {
        this.closingPrice = closing;
    }
    
    public double getClosingPrice()
    {
        return this.book.getLastTradePrice();
    }
    
    public void setPercentage(double _percentage)
    {
        this.percentage = _percentage;
    }
    
    public double getPercentage()
    {
        return this.percentage;
    }
    
    public void setType(MovingAverageEnvelope.STRATEGY_TYPE _type)
    {
        this.type =  _type;
        
        switch (_type)
        {
            case SHORT_TERM : percentage = short_term;
                break;
            case MEDIUM_TERM : percentage = medium_term;
                break;
            case LONG_TERM : percentage = long_term;
                break;
            default: percentage = medium_term;
                break;      
            }
    }
    
    public STRATEGY_TYPE getType()
    {
        return this.type;
    }

}
