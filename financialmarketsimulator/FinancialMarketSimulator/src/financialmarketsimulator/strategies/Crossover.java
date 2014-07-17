package financialmarketsimulator.strategies;

import static financialmarketsimulator.strategies.Crossover.HigherAverage.ema;
import static financialmarketsimulator.strategies.Crossover.HigherAverage.sma;
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
 * @brief Crossover strategy
 */
public class Crossover {

    /**
     * @brief Class used to house the details of a crossover event.
     */
    public class CrossoverDetails {

        /**
         * The difference between the SMA and EMA values immediately before the
         * crossover event
         */
        private final Double previousDifference;
        /**
         * The difference between the SMA and EMA values immediately after the
         * crossover event
         */
        private final Double currentDifference;
        /**
         * The average on top after the crossover event, either SMA or EMA.
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
        private final Double sma;
        private final Double ema;

        public DayClosingAverages(Date _date, Double _sma, Double _ema) {
            date = _date;
            sma = _sma;
            ema = _ema;
        }

        public Date getDate() {
            return this.date;
        }

        public Double getSMA() {
            return this.sma;
        }

        public Double getEMA() {
            return this.ema;
        }
    }

    public static enum HigherAverage {

        sma, ema
    }

    /**
     * The number of days over which the strategy is observed
     */
    private int numDays;

    /**
     * Houses the SMA and EMA closing values over the past numDays days.
     * DayClosingAverages specifies the particular day and the closing SMA and
     * EMA values for that day.
     */
    @SuppressWarnings({"UseOfObsoleteCollectionType", "FieldMayBeFinal"})
    private Vector<DayClosingAverages> closingAverages;

    /**
     * Stores the crossover points as the exact dates and times when the
     * crossover occur. Date specifies the particular day and CrossoverDetails
     * specifies the crossover details on that day
     */
    private LinkedHashMap<Date, CrossoverDetails> crossoverPoints;

    /**
     * Houses the latest crossover graph
     */
    private JFreeChart graph;

    public Crossover(int _numDays) {
        numDays = _numDays;
        crossoverPoints = null;
        graph = null;
        
        //TODO : Populate with objects housing closing EMA and SMA values over the
        //specidies previous numDays days.
        closingAverages = new Vector<DayClosingAverages>();
    }

    public void determineCrossoverPoints() {
        //Determine which of the SMA or EMA had a higher starting value.
        HigherAverage previousHigherValue = null;

        //Consider the consequent values until non equal values are observed
        //Using while loop for performance sake where EMA and SMA values are equivalent
        int j = 0; //Start from the first SMA and EMA values
        while (j < numDays) {
            if (closingAverages.get(j).getEMA() > closingAverages.get(j).getSMA()) {
                previousHigherValue = ema;
                break;
            } else if (closingAverages.get(j).getEMA() < closingAverages.get(j).getSMA()) {
                previousHigherValue = sma;
                break;
            }
            //If the EMA and SMA values where equivalent continue with search until non-equivalent values observed.
            j++;
        }

        //If all past SMA and EMA values are equal over the specified time, then 
        //set crossoverPoints to null and quit from the function. Else continue.
        if (previousHigherValue == null) {
            crossoverPoints = null;
            return;
        }

        crossoverPoints = new LinkedHashMap<Date, CrossoverDetails>();
        CrossoverDetails cod;

        for (int i = 1; i < numDays; i++) {
            //Iterate through all past SMA and EMA values over the past numDays
            //days and record any crossover dates.

            //Case that SMA had the higher previous value.
            if (previousHigherValue.equals(sma)) {
                //If a crossover occured
                if (closingAverages.get(i).getSMA() < closingAverages.get(i).getEMA()) {
                    previousHigherValue = ema;

                    //Record crossover date and values
                    cod = new CrossoverDetails((closingAverages.get(i - 1).getSMA() - closingAverages.get(i - 1).getEMA()),
                            (closingAverages.get(i).getSMA() - closingAverages.get(i).getEMA()),
                            previousHigherValue);

                    crossoverPoints.put(closingAverages.get(i).getDate(), cod);
                }
            } else //Case that EMA had the higher previous value.
            if (previousHigherValue.equals(ema)) {
                //If a crossover occured
                if (closingAverages.get(i).getEMA() < closingAverages.get(i).getSMA()) {
                    previousHigherValue = sma;

                    //Record crossover date and values
                    cod = new CrossoverDetails((closingAverages.get(i - 1).getEMA() - closingAverages.get(i - 1).getSMA()),
                            (closingAverages.get(i).getEMA() - closingAverages.get(i).getSMA()),
                            previousHigherValue);

                    crossoverPoints.put(closingAverages.get(i).getDate(), cod);
                }
            }
        }
        //At this point all the cross over points are recorded in crossoverPoints LinkedHashMap
    }

    /**
     * @brief Draws a line graph of the SMA and EMA values over numDays days.
     * Graph is stored in the JFreeChart Graph variable
     */
    public void drawCrossoverGraph() {
        //Determine crossover points before drawing graph.
        determineCrossoverPoints();

        TimeSeries emaSeries = new TimeSeries("EMA");
        TimeSeries smaSeries = new TimeSeries("SMA");

        for (DayClosingAverages closingAverage : closingAverages) {
            emaSeries.add(new Day(closingAverage.getDate()), closingAverage.getEMA());
            smaSeries.add(new Day(closingAverage.getDate()), closingAverage.getSMA());
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
    
    public void setNumberOfDays(int _numDays)
    {
        this.numDays = _numDays;
    }

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
