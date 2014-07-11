package financialmarketsimulator.indicators;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;
import static java.lang.Math.abs;

/**
 * @brief ADX (Average Directional Index).
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class ADX {

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
    
    
    
    
    
    
    /*private double currentADX;
    private double previousADX;
    
    public ADX()
    {
    }
    
    public double calulateADX()
    {
        previousADX = currentADX;
        EMA ema = new EMA(14);
        PDM pdm = new PDM();
        NDM ndm = new NDM();
        double currVal;
        double prevVal;
        
        //Set current and previous PDM and NDM values
        //pdm.setCurrValue(); //This values are zero at the moment
        //ndm.setCurrValue(); //This values are zero at the moment
        
        //Set values.
        currVal = abs(pdm.getCurrValue() - ndm.getCurrValue())/abs(pdm.getCurrValue() + ndm.getCurrValue());
        prevVal = abs(pdm.getPrevValue() - pdm.getPrevValue())/abs(pdm.getPrevValue() + ndm.getPrevValue());
        
        ema.setCurrentPrice(currVal);
        ema.setPreviousEMAValue(prevVal);
        
        //Calculate ADX value
        currentADX = (100 * ema.calculateEMA()); 
        return currentADX;
    }*/
}
