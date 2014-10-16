package financialmarketsimulator.indicators;

import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketIndicator;
import java.util.Vector;

/**
 * @brief ADX (Average Directional Index).
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class ADX extends MarketIndicator {

    private static ADX instance = null;
    private final DirectionalIndex di;
    private final MarketEntryAttemptBook book;
    private final int numDays;
    private int executionCount;
    private double previousADX;
    private Vector<Double> diValues;
    private Double average;
    private Double result;

    private ADX(MarketEntryAttemptBook _book, int _numDays) {
        super("Average Directional Index");
        this.book = _book;
        this.numDays = _numDays;
        this.di = DirectionalIndex.getInstance(_book, _numDays);
        this.executionCount = 0;
        this.previousADX = 0.0;
    }

    public static ADX getInstance(MarketEntryAttemptBook _book, int _numDays) {
        if (instance == null) {
            instance = new ADX(_book, _numDays);
        }
        return instance;
    }

    public double calculateADX() {
        diValues = di.getDiretionalIndexValues();

        if (diValues.size() < numDays) {
            return 0.0;
        }

        if (this.executionCount == 0) {
            this.executionCount++;
            average = 0.0;

            for (double val : diValues) {
                average += val;
            }

            average = average / this.numDays;
            this.previousADX = average;
            result = average;
        } else {
            result = ((this.previousADX * (numDays - 1)) + diValues.lastElement()) / this.numDays;
        }

        return result;
    }

    public double getPreviousADX() {
        return this.previousADX;
    }

    @Override
    public Double calculateIndicator() {
        return this.calculateADX();
    }
}
