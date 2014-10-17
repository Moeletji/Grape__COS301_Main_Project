/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */
package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import static financialmarketsimulator.market.MarketStrategy.VOLATILITY.NORMAL;
import java.util.Vector;

/**
 *
 * @author Moeletji
 */
public class MovingAverageEnvelopeEMA extends MovingAverageEnvelope{
    
    private EMA ema;
    
    private static MovingAverageEnvelopeEMA instance = null;
    
    private MovingAverageEnvelopeEMA(MarketEntryAttemptBook _data,MovingAverageEnvelope.STRATEGY_TYPE _type) throws NotEnoughDataException
    {
        super(_data,_type);
        ema = EMA.getInstance(book, num_days);
        pastEMAValues = new Vector<Double>();
        type = _type;
    }
    
    public static MovingAverageEnvelopeEMA getInstance(MarketEntryAttemptBook _book,MovingAverageEnvelope.STRATEGY_TYPE _type)
    {
       if (instance == null)
       {
           try{
               instance = new MovingAverageEnvelopeEMA(_book, _type);
           } catch (NotEnoughDataException ex) {
               System.out.println("Moving Average Envelope(using Exponential Moving Average)");
           }
       }
       return instance;
    }
    
    /**
     * 
     * @param percentage the value to determine the size of the envelope
     * @return the upper envelope value
     * @throws NotEnoughDataException 
     */
    private double calculateEMAUpperEvelope() throws NotEnoughDataException
    {
        return getEMA() + (getEMA()*percentage/100);
    }
    
    /**
     * 
     * @param percentage the value to determine the size of the envelope
     * @return the lower envelope value
     * @throws NotEnoughDataException 
     */
    private double calculateEMALowerEvelope()throws NotEnoughDataException
    {
        return getEMA() - (getEMA()*percentage/100);
    }
    
    /**
     * @brief calculate the simple moving average using the ema object
     * @return the simple moving average
     * @throws NotEnoughDataException 
     */
    public double getEMA() throws NotEnoughDataException
    {
        pastEMAValues.add(ema.calculateEMA());
        return pastEMAValues.lastElement();
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
        if (pastEMAValues.isEmpty())
            //throw new NotEnoughDataException();
        
        if (this.getClosingPrice() >= this.calculateEMALowerEvelope() || (Math.abs(this.getClosingPrice() - this.calculateEMALowerEvelope())<0.05))
        {
            /*if (this.getPreviousClosingPrice() > this.getClosingPrice())
            {
                //sell
            }
            else*/ if (this.getClosingPrice()< this.getClosingPrice())
            {
                //buy
                this.signalDetails.setSignal(SIGNAL.BID);
            }
        }
        else if (this.getClosingPrice() >= this.calculateEMAUpperEvelope() || (Math.abs(this.getClosingPrice() - this.calculateEMAUpperEvelope())<0.05))
        {
            if (this.getClosingPrice() > this.getClosingPrice())
            {
                //sell
                this.signalDetails.setSignal(SIGNAL.OFFER);
            }
            /*else if (this.getPreviousClosingPrice() < this.getClosingPrice())
            {
                //sell
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
