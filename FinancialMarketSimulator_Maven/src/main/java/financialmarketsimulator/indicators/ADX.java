package financialmarketsimulator.indicators;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import static java.lang.Math.abs;
import static java.lang.Math.abs;

/**
 * @brief ADX (Average Directional Index).
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class ADX extends MarketIndicator{

    /**
   * The total number of periods to generate data for.
   */
  public static final int TOTAL_PERIODS = 100;

  /**
    * The number of periods to average together.
    */
  public static final int PERIODS_AVERAGE = 30;

    /*public static void main(String[] args) {
      double[] closePrice = new double[TOTAL_PERIODS];
      double[] out = new double[TOTAL_PERIODS];
      MInteger begin = new MInteger();
      MInteger length = new MInteger();

      for (int i = 0; i < closePrice.length; i++) {
        closePrice[i] = (double) i;
      }

      Core c = new Core();
      RetCode retCode = c.sma(0, closePrice.length - 1, closePrice, PERIODS_AVERAGE, begin, length, out);

      if (retCode == RetCode.Success) {
        System.out.println("Output Begin:" + begin.value);
        System.out.println("Output Begin:" + length.value);

        for (int i = begin.value; i < length.value; i++) {
          StringBuilder line = new StringBuilder();
          line.append("Period #");
          line.append(i+1);
          line.append(" close= ");
          line.append(closePrice[i]);
          line.append(" mov avg=");
          line.append(out[i]);
          System.out.println(line.toString());
      }
    }
    else {
      System.out.println("Error");
    }
  }*/
    
    private double currentADX;
    private double previousADX;
    private double todaysHigh;
    private double todaysLow;
    private double prevClosing;
    private MarketEntryAttemptBook book;
    private final int numDays;
    
    public ADX(MarketEntryAttemptBook _book, int _numDays)
    {
        super("Average Directional Index");
        book = _book;
        numDays = _numDays;
        todaysHigh = book.getHighestTradePrice(numDays);
        todaysLow = book.getLowestTradePrice(numDays);
        prevClosing = book.getLastTradePrice(); //Might need to be changed
    }
    
    /*public ADX(double _high, double _low, double _closing)
    {
        todaysHigh = _high;
        todaysLow = _low;
        prevClosing = _closing;
    }*/
    
    /**
     * @brief Calculates and returns the average directional movement value
     * @param prevPDI The previous positive directional indicator value
     * @param prevNDI The previous negative directional indicator value
     * @param currPDM The current positive directional movement value
     * @param currNDM The current negative directional movement value
     * @param prevPDM The previous positive directional movement value
     * @param prevNDM The previous negative directional movement value
     * @return Returns the current ADX value
     */
    public double calulateADX(double prevPDI, double prevNDI, double currPDM, double currNDM, double prevPDM, double prevNDM) throws NotEnoughDataException
    {
        previousADX = currentADX;
        EMA ema = new EMA(book,14);
        //PDI pdi = new PDI(todaysHigh, todaysLow, prevClosing);
        //NDI ndi = new NDI(todaysHigh, todaysLow, prevClosing);
        PDI pdi = new PDI(book, numDays);
        NDI ndi = new NDI(book, numDays);
        double currVal;
        double prevVal;
        
        pdi.setPreviousValue(prevPDI);
        ndi.setPreviousValue(prevNDI);
        
        //Set values.
        currVal = abs(pdi.calculatePDI(currPDM, prevPDM) - ndi.calculateNDI(currNDM, prevNDM))/abs(pdi.calculatePDI(currPDM, prevPDM) + ndi.calculateNDI(currNDM, prevNDM));
        prevVal = abs(pdi.getPrevValue() - ndi.getPrevValue())/abs(pdi.getPrevValue() + ndi.getPrevValue());
        
        ema.setCurrentPrice(currVal);
        ema.setPreviousEMAValue(prevVal);
        
        //Calculate ADX value
        currentADX = (100 * ema.calculateEMA()); 
        return currentADX;
    }
    
    public double getADX()
    {
        return this.currentADX;
    }

    @Override
    public Double calculateIndicator() throws NotEnoughDataException {
        return this.calulateADX(this.previousADX, this.previousADX, this.currentADX, this.currentADX, this.previousADX, this.previousADX);
    }
}
