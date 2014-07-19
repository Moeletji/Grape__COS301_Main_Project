package financialmarketsimulator.strategies;

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
public abstract class Crossover {

    /**
     * @brief Class used to house the details of a crossover event.
     */
    public class CrossoverDetails {

        /**
         * The difference between the two indicator values immediately before the
         * crossover event
         */
        private final Double previousDifference;
        /**
         * The difference between the two indicator values immediately after the
         * crossover event
         */
        private final Double currentDifference;
        /**
         * The indicator or price on top, post the crossover.
         */
        private final HigherAverage currentHigh;

        public CrossoverDetails(Double _previousDifference, Double _currentDifference, HigherAverage _currentHigh) {
            previousDifference = _previousDifference;
            currentDifference = _currentDifference;
            currentHigh = _currentHigh;
        }

        public Double getPreviousDifference() {
            return this.previousDifference;
        }

        public Double getCurrentDifference() {
            return this.currentDifference;
        }

        public HigherAverage getCurrentHigh() {
            return this.currentHigh;
        }
    }

    /**
     * @brief Class storing the closing EMA and SMA values for a particular day
     */
    public class DayClosingAverages {

        private final Date date;
        private final Double indicator1;
        private final Double indicator2;

        public DayClosingAverages(Date _date, Double _indicator1, Double _indicator2) {
            date = _date;
            indicator1 = _indicator1;
            indicator2 = _indicator2;
        }

        public Date getDate() {
            return this.date;
        }

        public Double getIndicator1() {
            return this.indicator1;
        }

        public Double getIndicator2() {
            return this.indicator2;
        }
    }

    public static enum HigherAverage {

        sma, ema, price
    }

    /**
     * The number of days over which the strategy is observed
     */
    protected int numDays;
    
    
    protected final String ind1;
    protected final String ind2;

    protected HigherAverage currentHigh;

    /**
     * Houses the two indicator closing values over the past numDays days.
     * DayClosingAverages specifies the particular day and the closing values for that day.
     */
    @SuppressWarnings({"UseOfObsoleteCollectionType", "FieldMayBeFinal"})
    protected Vector<DayClosingAverages> closingAverages;

    /**
     * Stores the crossover points as the exact dates and times when the
     * crossover occur. Date specifies the particular day and CrossoverDetails
     * specifies the crossover details on that day
     */
    protected LinkedHashMap<Date, CrossoverDetails> crossoverPoints;

    /**
     * Houses the latest crossover graph
     */
    protected JFreeChart graph;

    public Crossover(int _numDays, String _line1, String _line2) {
        this.numDays = _numDays;
        this.crossoverPoints = null;
        this.graph = null;
        this.currentHigh = null;
        this.ind1 = _line1;
        this.ind2 = _line2;
    }

    /**
     * @brief Determines when crossovers events took place in the past numDays days and
     * stores the crossover events information in CrossoverDetails objects and 
     * the crossoverPoints vector.
     */
    public abstract void determineCrossoverPoints();

    /**
     * @brief Draws a line graph of the SMA and EMA values over numDays days.
     * Graph is stored in the JFreeChart Graph variable
     */
    public void drawCrossoverGraph() {
        //Determine crossover points before drawing graph.
        determineCrossoverPoints();

        TimeSeries emaSeries = new TimeSeries(ind1);
        TimeSeries smaSeries = new TimeSeries(ind2);

        for (DayClosingAverages closingAverage : closingAverages) {
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
        frame.setSize(1000, 650);
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
     * @return Returns the current higher indicator/price
     */
    public abstract String getCurrentHight();

    /**
     *
     * @return Returns a HashMap of crossover events.
     */
    public LinkedHashMap<Date, CrossoverDetails> getCrossoverPoints() {
        return this.crossoverPoints;
    }

    /**
     *
     * @return Returns a vector of DayClosingAverages objects over numDays days.
     */
    public Vector<DayClosingAverages> getClosingAverages() {
        return this.closingAverages;
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
