package financialmarketsimulator.market;

import financialmarketsimulator.exception.NotEnoughDataException;

/**
 * @brief The super market strategy class from which each concrete market
 * strategy used by each participant will inherit from.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public abstract class MarketStrategy {
    
    /**
     * @brief Struct class the encapsulates the Volatility and Signal message sent to a MarketParticipant
     */
    public class SignalMessage
    {
        private MarketStrategy.VOLATILITY volatility;

        private MarketStrategy.SIGNAL signal;

        public SignalMessage(){}

        public MarketStrategy.VOLATILITY getVolatility() {
            return volatility;
        }

        public MarketStrategy.SIGNAL getSignal() {
            return signal;
        }

        public void setVolaility(MarketStrategy.VOLATILITY length) {
            if(length == null)
            {
                this.volatility = MarketStrategy.VOLATILITY.NORMAL;
            }
            else
                this.volatility = length;
        }

        public void setSignal(MarketStrategy.SIGNAL _signal) {
            if(signal == null)
            {
                this.signal = MarketStrategy.SIGNAL.DO_NOTHING;
            }
            else
                this.signal = _signal;
        }
    };
    
    /**
     * @brief   A message sent to the MarketParticipant to
     *          indicate where to BUY or SELL a rate of volatility.
     */
    protected MarketStrategy.SignalMessage signalDetails;
    
    /**
     * @brief Range of volatility 
     */
    public static enum VOLATILITY {HIGH, MEDIUM, NORMAL, LOW};
    
    /**
     * @brief Trade signal 
     */
    public static enum SIGNAL {BID, OFFER, DO_NOTHING};
    
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
        this.signalDetails = new MarketStrategy.SignalMessage();
    }

    /**
     * @brief Retrieve the name of the name of the strategy
     * @return Strategy name
     */
    public String getStrategyName() {
        return strategyName;
    }

    /**
     * @return Signal Details Object
     * @throws financialmarketsimulator.exception.NotEnoughDataException
     * @Brief where trade signals are generated
     */
    public abstract  MarketStrategy.SignalMessage trade() throws NotEnoughDataException;
}
