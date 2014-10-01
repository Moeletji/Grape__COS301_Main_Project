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
import java.util.Vector;
import org.jfree.data.time.Millisecond;
import org.jfree.data.xy.XYSeries;

/**
 *
 * @author Madimetja
 */
public class IndicatorMultiLineChart extends MultiLineChart {

    private final Vector<MarketIndicator> indicators;
    private final Vector<String> indicatorNames;

    public IndicatorMultiLineChart(Vector<MarketIndicator> _indicators, Vector<String> _indicatorNames, int minYAxisValue, int maxYAxis) throws NotEnoughDataException {
        super("Market Indicators", minYAxisValue, maxYAxis);
        this.indicators = _indicators;
        this.indicatorNames = _indicatorNames;

        for (String ind : this.indicatorNames) {
            this.serieses.add(new XYSeries(ind));
        }
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        double v1 = 0;
        i = i + 750;
        try {

            XYSeries ser;
            MarketIndicator ind;
            double value;
            final Millisecond now = new Millisecond();
            for (int k = 0; k < this.serieses.size(); k++) {
                ser = this.serieses.get(k);
                ind = this.indicators.get(k);
                value = ind.calculateIndicator();
                ser.add(i, value);
                System.out.print("Current Time in Milliseconds = " + now.toString() + ", Current " + this.indicatorNames.get(k) + " : " + value);
            }

        } catch (NotEnoughDataException ex) {
            ex.printStackTrace();
        }
    }
}
