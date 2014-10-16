/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.interfaceCharts;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.*;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 *
 * @author Madimetja
 */
public class IndicatorData {
    
    private final ADX adx;
    private final ATR atr;
    private final DirectionalIndex di;
    private final EMA ema;
    private final MACD macd;
    private final NDI ndi;
    private final NDM ndm;
    private final PDI pdi;
    private final PDM pdm;
    private final RSI rsi;
    private final SMA sma;
    private final MarketEntryAttemptBook book;
    
    public IndicatorData(MarketEntryAttemptBook _book) throws NotEnoughDataException
    {
        this.book = _book;
        this.adx = ADX.getInstance(book, 14);
        this.atr = ATR.getInstance(book, 14);
        this.di = DirectionalIndex.getInstance(_book, 14);
        this.ema = EMA.getInstance(book, 14);
        this.macd = MACD.getInstance(book);
        this.ndi = NDI.getInstance(book, 14);
        this.ndm = NDM.getInstance(book, 14);
        this.pdi = PDI.getInstance(book, 14);
        this.pdm = PDM.getInstance(book, 14);
        this.rsi = RSI.getInstance(book, 14);
        this.sma= SMA.getInstance(book, 14);
    }
    
    public Vector<MarketIndicator> getIndicators()
    {
        Vector<MarketIndicator> result = new Vector<>();
        
        result.add(adx);
        result.add(atr);
        result.add(di);
        result.add(ema);
        result.add(macd);
        result.add(ndi);
        result.add(ndm);
        result.add(pdi);
        result.add(pdm);
        result.add(rsi);
        result.add(sma);
        
        return result;
    }
}
