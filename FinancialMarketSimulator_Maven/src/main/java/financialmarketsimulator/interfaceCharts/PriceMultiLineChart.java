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
import java.awt.event.ActionEvent;
import java.util.Vector;
import org.jfree.data.time.Millisecond;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author Madimetja
 */
public class PriceMultiLineChart extends MultiLineChart{
    
    private final Vector<StockManager> prices;
    private final Vector<String> priceNames;
    
    public PriceMultiLineChart(Vector<StockManager> _prices, Vector<String> _priceNames, int minYAxisValue, int maxYAxis) throws NotEnoughDataException
    {
        super("Security Values", minYAxisValue, maxYAxis);
        this.prices = _prices;
        this.priceNames = _priceNames;

        for (String ind : this.priceNames) {
            this.serieses.add(new XYSeries(ind));
        }
    }

    /*public PriceMultiLineChart(Vector<StockManager> man, Vector<String> manNames, String stock_Prices, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        double v1 = 0;
        i = i + 750;
        try {

            XYSeries ser;
            StockManager man;
            double value;
            final Millisecond now = new Millisecond();
            for (int k = 0; k < this.serieses.size(); k++) {
                ser = this.serieses.get(k);
                man = this.prices.get(k);
                value = man.getOrderList().getLastTradePrice();
                ser.add(i, value);
                //System.out.print("Current Time in Milliseconds = " + now.toString() + ", Current " + this.priceNames.get(k) + " : " + value);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
