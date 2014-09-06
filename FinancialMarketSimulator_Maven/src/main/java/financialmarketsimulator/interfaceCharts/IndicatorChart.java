/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.interfaceCharts;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketIndicator;
import java.awt.event.ActionEvent;
import org.jfree.data.time.Millisecond;

/**
 *
 * @author Madimetja
 */
public class IndicatorChart extends DynamicLineChart{
    
    /**
     * Indicator object
     */
    private final MarketIndicator indicator;
    
    /**
     * @throws financialmarketsimulator.exception.NotEnoughDataException
     * @brief Dynamic line Chart Constructor for Market Indicators
     * @param _indicator An instance of the Market indicator to be graphed
     * @param _chartTitle The title of the chart to be created
     * @param _item The name of the item to be graphed, i.e the item represented by the line in the graph.
     */
    public IndicatorChart(MarketIndicator _indicator, String _chartTitle, String _item) throws NotEnoughDataException
    {
        super(_chartTitle,_item);
        this.indicator = _indicator;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        try {
            this.lastValue = this.indicator.calculateIndicator();
        } catch (NotEnoughDataException ex) {
            ex.printStackTrace();
        }
        
        final Millisecond now = new Millisecond();
        this.series.add(new Millisecond(), this.lastValue);
       
        System.out.println("Current Time in Milliseconds = " + now.toString()+", Current " + this.item + " : "+this.lastValue);
    }
}
