package financialmarketsimulator.marketData;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MatchedMarketEntryAttemptUpdate {

    private Date dateGenerated;
    private Vector<MatchedMarketEntryAttempt> tradesData;

    public MatchedMarketEntryAttemptUpdate(Vector<MatchedMarketEntryAttempt> trades) {
        tradesData = trades;
        dateGenerated = new Date();
    }

    public double getFirstTradePrice() {
        return (tradesData == null) ? 0.0 : tradesData.firstElement().getPrice();
    }

    public double getLastTradePrice() {
        return (tradesData == null) ? 0.0 : tradesData.lastElement().getPrice();
    }

    public double getHighestTradePrice() {
        if (tradesData == null) {
            return 0.0;
        }

        double high = tradesData.get(0).getPrice();

        for (int i = 1; i < tradesData.size(); i++) {
            if (high < tradesData.get(i).getPrice()) {
                high = tradesData.get(i).getPrice();
            }
        }
        return high;
    }

    public double getLowestTradePrice() {
        if (tradesData == null) {
            return 0.0;
        }

        double low = tradesData.get(0).getPrice();

        for (int i = 1; i < tradesData.size(); i++) {
            if (low > tradesData.get(i).getPrice()) {
                low = tradesData.get(i).getPrice();
            }
        }
        return low;
    }

    public String getDateGenerated() {
        return dateGenerated.toString();
    }
}
