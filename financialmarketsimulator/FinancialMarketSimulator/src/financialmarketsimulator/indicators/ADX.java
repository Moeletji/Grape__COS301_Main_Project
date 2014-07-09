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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Current period directional movements
     */
    private double upMove;
    private double downMove;
    private double currentHigh;
    private double currentLow;
    private double prevHigh;
    private double prevLow;
    private double prevClosing;
    private double posDM;
    private double negDM;
    private PDI pdi;
    private NDI ndi;
    private double averageTrueRange;
    private final int numberOfDays = 14;

    public ADX(double _todaysHigh, double _todaysLow, double _yersterdaysHigh, double _yersterdaysLow, double _prevClosing) {
        currentHigh = _todaysHigh;
        currentLow = _todaysLow;
        prevHigh = _yersterdaysHigh;
        prevLow = _yersterdaysLow;
        prevClosing = _prevClosing;
    }

    /**
     * @brief calculates the Average Directional Index
     * @return returns the average directional index
     */
    public double calculateADX() {
        double adxValue;
        double posDI = 0.0;
        double negDI = 0.0;
        
        
        upMove = currentHigh - prevHigh;
        downMove = currentLow -prevLow;
        
        

        if (upMove > downMove && upMove > 0) {
            posDM = upMove;
        } else {
            posDM = 0;
        }

        if (downMove > upMove && downMove > 0) {
            negDM = downMove;
        } else {
            negDM = 0;
        }

        //****************************
        //Must complete from here down
        //****************************
        
        //PDI = new PDI(currentHigh, prevHigh, currentHigh, currentLow, prevClosing);
        //NDI = new NDI(currentHigh, prevHigh, currentHigh, currentLow, prevClosing);
        
        ATR atr = new ATR(currentHigh, currentLow, prevClosing);
        averageTrueRange = atr.calculateATR();
        
        double posVal = posDM/averageTrueRange;
        double negVal = negDM/averageTrueRange;
        
        EMA ema = new EMA(numberOfDays);
        
        ema.setCurrentPrice(currentLow);
        ema.setPreviousEMAValue(prevLow);
        
        posDI = 100*ema.calculateEMA();
        negDI = 100*ema.calculateEMA();
        
        adxValue = 100* abs(((posDI - negDI)/(posDI + negDI)));
        return adxValue;
    }
}
