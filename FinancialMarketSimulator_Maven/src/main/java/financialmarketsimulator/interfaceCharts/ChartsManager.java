/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.interfaceCharts;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 *
 * @author Madimetja
 */
public class ChartsManager {
    
    /**
     * Members for all the different types of charts that can be created
    */
    Vector<MultiLineChart> indicatorMulitLineChartContainer;
    Vector<MultiLineChart> priceMovementMultiLineChartContainer;
    
    public ChartsManager()
    {
        this.indicatorMulitLineChartContainer = new Vector<>();
        this.priceMovementMultiLineChartContainer = new Vector<>();
    }
    
    public boolean createNewIndicatorMultiLineChart(Vector<MarketIndicator> items, Vector<String> itemNames, String title, int minYAxisValue, int maxYAxisValue)
    {   
        try
        {
            this.indicatorMulitLineChartContainer.add(new IndicatorMultiLineChart(items, itemNames, minYAxisValue, maxYAxisValue));
            return true;
        }
        catch(NotEnoughDataException e)
        {
            e.printStackTrace();
            return false;
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean createNewPriceMovementMultiLineChart(Vector<MarketEntryAttemptBook> items, Vector<String> itemNames, String title, int minYAxisValue, int maxYAxisValue)
    {
        try
        {
            this.indicatorMulitLineChartContainer.add(new PriceMultiLineChart(items, itemNames, minYAxisValue, maxYAxisValue));
            return true;
        }
        catch(NotEnoughDataException e)
        {
            e.printStackTrace();
            return false;
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
