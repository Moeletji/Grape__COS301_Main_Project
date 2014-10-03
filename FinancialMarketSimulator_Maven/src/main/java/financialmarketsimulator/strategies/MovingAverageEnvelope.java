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
public class MovingAverageEnvelope extends MarketStrategy{

    public static enum STRATEGY_TYPE {

        SHORT_TERM, MEDIUM_TERM, LONG_TERM
    }
    //percent values for the envelope
    private final double short_term = 2.5;
    private final double medium_term = 5.0;
    private final double long_term = 10.0;

    private double percentage;
    //preiod for moving average
    private int num_days = 10;
    
    private STRATEGY_TYPE type;

    //closing price
    private double closingPrice;
    
    private Vector<Double> pastSMAValues;
    private Vector<Double> pastEMAValues;

    private EMA ema;
    private SMA sma;
    
    MarketEntryAttemptBook book;

    public MovingAverageEnvelope(MarketEntryAttemptBook _book) throws NotEnoughDataException {
        super("MovingAverageEnvelope");
        if (_book != null)
        {
            this.book = _book;
            //ema = new EMA(num_days);
            sma = new SMA(book, num_days);
            pastSMAValues = new Vector<Double>();
            type = MovingAverageEnvelope.STRATEGY_TYPE.MEDIUM_TERM;
            percentage = this.medium_term;
        }
        else throw new NotEnoughDataException();
    }
    
    public MovingAverageEnvelope(MarketExchange exchange, MarketEntryAttemptBook _book, MovingAverageEnvelope.STRATEGY_TYPE _type) throws NotEnoughDataException {
        super("MovingAverageEnvelope");
        
        if (_book != null)
        {
            this.book = _book;
            //ema = new EMA(num_days);
            sma = new SMA(book, num_days);
            pastSMAValues = new Vector<Double>();
            
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
    
    /**
     * 
     * @param percentage the value to determine the size of the envelope
     * @return the upper envelope value
     * @throws NotEnoughDataException 
     */
    private double calculateSMAUpperEvelope() throws NotEnoughDataException
    {
        return getSMA() + (getSMA()*percentage/100);
    }
    
    /**
     * 
     * @param percentage the value to determine the size of the envelope
     * @return the lower envelope value
     * @throws NotEnoughDataException 
     */
    private double calculateSMALowerEvelope()throws NotEnoughDataException
    {
        return getSMA() - (getSMA()*percentage/100);
    }
    
    /**
     * @brief calculate the simple moving average using the sma object
     * @return the simple moving average
     * @throws NotEnoughDataException 
     */
    public double getSMA() throws NotEnoughDataException
    {
        pastSMAValues.add(sma.calculateSMA());
        return pastSMAValues.lastElement();
    }
    
    public double getPreviousClosingPrice()
    {
        int size = pastSMAValues.size();
        return (pastSMAValues.isEmpty()?0.0:(pastSMAValues.size() >1)?pastSMAValues.get(size-2):pastSMAValues.lastElement());
    }
    public void setClosingPrice(double closing)
    {
        //this.closingPrice = book.getMatchedOrders().lastElement().getPrice();
        this.closingPrice = closing;
    }
    
    public double getClosingPrice()
    {
        //return book.getMatchedOrders().lastElement().getPrice();
        return this.book.getLastTradePrice();
    }
    
    public double getPercentage()
    {
        return this.percentage;
    }
    
    public STRATEGY_TYPE getType()
    {
        return this.type;
    }
    
    /**
     * @return The market entry attempt generated
     * @throws financialmarketsimulator.exception.NotEnoughDataException
     * @brief generate a market event depending on whether the instrument is
     * oversold/overbought
     */
    @Override
    public SignalMessage trade() throws NotEnoughDataException{
        //Implement one trade instance here, infinite loop is in MarketParticipant
        if (pastSMAValues.isEmpty())
            //throw new NotEnoughDataException();
        
        if (this.getClosingPrice() == this.calculateSMALowerEvelope() || (Math.abs(this.getClosingPrice() - this.calculateSMALowerEvelope())<0.05))
        {
            /*if (this.getPreviousClosingPrice() > this.getClosingPrice())
            {
                //sell
                book.placeOrder(new MarketEntryAttempt());
            }
            else*/ if (this.getPreviousClosingPrice() < this.getClosingPrice())
            {
                //buy
                //MarketEntryAttempt buy = new MarketEntryAttempt(1,1,"", MarketEntryAttempt.SIDE.BID);
                //book.placeOrder(buy);
                this.signalDetails.setSignal(SIGNAL.BID);
            }
        }
        else if (this.getClosingPrice() == this.calculateSMAUpperEvelope() || (Math.abs(this.getClosingPrice() - this.calculateSMAUpperEvelope())<0.05))
        {
            if (this.getPreviousClosingPrice() > this.getClosingPrice())
            {
                //sell
                MarketEntryAttempt sell = new MarketEntryAttempt(999,999,"", MarketEntryAttempt.SIDE.OFFER);
                book.placeOrder(sell);
                this.signalDetails.setSignal(SIGNAL.OFFER);
            }
            /*else if (this.getPreviousClosingPrice() < this.getClosingPrice())
            {
                //sell
                book.placeOrder(new MarketEntryAttempt());
            }*/
        }
        else
        {
            this.signalDetails.setSignal(SIGNAL.DO_NOTHING);
        }
        this.signalDetails.setVolaility(NORMAL);
        return this.signalDetails;
    }
}
