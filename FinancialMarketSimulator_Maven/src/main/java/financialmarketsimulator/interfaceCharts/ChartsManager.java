/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.interfaceCharts;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.StockManager;
import java.util.Vector;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Madimetja
 */
public class ChartsManager {
    
    /**
     * Members for all the different types of charts that can be created
    */
    private final Vector<MultiLineChart> indicatorMulitLineChartContainer;
    private final Vector<MultiLineChart> priceMovementMultiLineChartContainer;
    private final IndicatorData indicatorData;
    private final ChartData chartData;
    private final MarketEntryAttemptBook book;
    private final StockManager manager;
    
    public ChartsManager(MarketEntryAttemptBook _book, StockManager _man) throws NotEnoughDataException
    {
        this.book = _book;
        this.indicatorMulitLineChartContainer = new Vector<>();
        this.priceMovementMultiLineChartContainer = new Vector<>();
        this.indicatorData = new IndicatorData(this.book);
        this.manager = _man;
        this.chartData = new ChartData();
    }
    
    public boolean createNewIndicatorMultiLineChart(String title, int minYAxisValue, int maxYAxisValue)
    {   
        try
        {
            IndicatorMultiLineChart imc = new IndicatorMultiLineChart(this.indicatorData.getIndicators(), minYAxisValue, maxYAxisValue);
            this.indicatorMulitLineChartContainer.add(imc);
            imc.pack();
            RefineryUtilities.centerFrameOnScreen(imc);
            imc.setVisible(true);
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
    
    public boolean createNewPriceMovementMultiLineChart(Vector<StockManager> items, Vector<String> itemNames, String title, int minYAxisValue, int maxYAxisValue)
    {
        try
        {   PriceMultiLineChart pmc = new PriceMultiLineChart(items, itemNames, minYAxisValue, maxYAxisValue);
            this.priceMovementMultiLineChartContainer.add(pmc);
            pmc.pack();
            RefineryUtilities.centerFrameOnScreen(pmc);
            pmc.setVisible(true);
            return true;
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
