package financialmarketsimulator.indicators;

import static java.lang.Math.abs;

/**
 * @brief ADX (Average Directional Index).
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class ADX {

    private double currentADX;
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
    }
}
