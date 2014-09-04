package financialmarketsimulator.market;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.marketData.Message;
import java.util.Map;

/**
 * @brief The super market strategy class from which each concrete market
 * strategy used by each participant will inherit from.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public abstract class MarketStrategy {
    
    protected SignalDetails signalDetails;
    
    public static enum LENGTH {HIGH, MEDUIM, NORMAL, LOW};
    
    public static enum SIGNAL {BUY, SELL, DO_NOTHING};
    
    protected class SignalDetails
    {
        private MarketStrategy.LENGTH length;

        private MarketStrategy.SIGNAL signal;

        public SignalDetails(){}

        public MarketStrategy.LENGTH getLength() {
            return length;
        }

        public MarketStrategy.SIGNAL getSignal() {
            return signal;
        }

        public void setLength(MarketStrategy.LENGTH length) {
            if(length.equals(null))
            {
                this.length = MarketStrategy.LENGTH.NORMAL;
            }
            else
                this.length = length;
        }

        public void setSignal(MarketStrategy.SIGNAL _signal) {
            if(signal.equals(null))
            {
                this.signal = MarketStrategy.SIGNAL.DO_NOTHING;
            }
            else
                this.signal = _signal;
        }
    };
    
    /**
     * @brief Name of the MarketStrategy
     */
    private final String strategyName;

    /**
     * @brief MarketStrategy constructor
     * @param exchange Stock exchange
     * @param strategyName Name of the Market Strategy
     */
    public MarketStrategy(String strategyName) {
        this.strategyName = strategyName;
        this.signalDetails = new SignalDetails();
    }

    /**
     * @brief Retrieve the name of the name of the strategy
     * @return Strategy name
     */
    public String getStrategyName() {
        return strategyName;
    }

    /**
     * @throws financialmarketsimulator.exception.NotEnoughDataException
     * @Brief where trade signals are generated
     */
    public abstract void trade() throws NotEnoughDataException;
}
