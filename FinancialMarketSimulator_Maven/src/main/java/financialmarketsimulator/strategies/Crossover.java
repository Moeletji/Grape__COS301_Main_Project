package financialmarketsimulator.strategies;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketStrategy;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @brief Abstract Crossover Strategy class as there can be different kinds of
 * crossover strategies. Can be implemented by MovingAverage Crossover,
 * PriceVsEma Crossover and PriceVsSma Crossover.
 */
public abstract class Crossover extends MarketStrategy {
    
    /**
     * The number of days over which the strategy is observed
     */
    protected int numDays;

    protected final String ind1;
    protected final String ind2;

    protected MarketEntryAttemptBook data;

    /**
     * Houses the latest crossover graph
     */
    protected JFreeChart graph;

    public Crossover(MarketEntryAttemptBook _data, int _numDays, String _line1, String _line2) {
        super(_line1+ " & " +_line2+" - Crossover");
        this.numDays = _numDays;
        this.graph = null;
        this.ind1 = _line1;
        this.ind2 = _line2;
        this.data = _data;
    }

    @Override
    public abstract SignalMessage trade() throws NotEnoughDataException;

    /**
     * @brief Draws a line graph of the SMA and EMA values over numDays days.
     * Graph is stored in the JFreeChart Graph variable
     */
    public void drawCrossoverGraph() {
        //Determine crossover points before drawing graph.

        TimeSeries emaSeries = new TimeSeries(ind1);
        TimeSeries smaSeries = new TimeSeries(ind2);

        /*for (DayClosingAverages closingAverage : closingAverages) {
            emaSeries.add(new Day(closingAverage.getDate()), closingAverage.getIndicator1());
            smaSeries.add(new Day(closingAverage.getDate()), closingAverage.getIndicator2());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();

        dataset.addSeries(emaSeries);
        dataset.addSeries(smaSeries);

        //Creating the chart
        graph = ChartFactory.createLineChart("Crosover over " + numDays + "Days", "Date", "Price", (CategoryDataset) dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartFrame frame = new ChartFrame("Crossover Graph", graph);
        frame.setVisible(true);
        frame.setSize(1000, 650);*/
    }

    /**
     * @brief Sets the number of days over which the strategy is observed.
     * @param _numDays The number of days over which the strategy is observed
     */
    public void setNumberOfDays(int _numDays) {
        this.numDays = _numDays;
    }
    
    /**
     *
     * @return Returns a JFreeChart object of the latest crossover graph.
     */
    public JFreeChart getCrossoverGraph() {
        drawCrossoverGraph();
        return graph;
    }
}
