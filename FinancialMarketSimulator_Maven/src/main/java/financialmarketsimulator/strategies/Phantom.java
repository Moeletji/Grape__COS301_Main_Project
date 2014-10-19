package financialmarketsimulator.strategies;

import financialmarketsimulator.market.MarketStrategy;
import static financialmarketsimulator.strategies.Phantom.SAFETY_LEVEL.HIGH;
import static financialmarketsimulator.strategies.Phantom.SAFETY_LEVEL.LOW;
import static financialmarketsimulator.strategies.Phantom.SAFETY_LEVEL.MEDIUM;
import static financialmarketsimulator.strategies.Phantom.SAFETY_LEVEL.RANDOM;
import java.util.Random;

/**
 * @brief Phantom strategy is used to generate data to see how other strategies
 * react to its trading behaviour.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class Phantom extends MarketStrategy {

    public static enum SAFETY_LEVEL {

        LOW, MEDIUM, HIGH, RANDOM
    };
    private Phantom.SAFETY_LEVEL safetyLevel;

    public Phantom() {
        super("Phantom");
        safetyLevel = Phantom.SAFETY_LEVEL.RANDOM;
    }

    public Phantom(Phantom.SAFETY_LEVEL safetyLevel) {

        super("Phantom");
        this.safetyLevel = safetyLevel;
    }

    @Override
    public synchronized MarketStrategy.SignalMessage trade() {

        MarketStrategy.SIGNAL signal;

        //Choose to randomly to buy or sell. 
        if (new Random().nextBoolean()) {
            signal = MarketStrategy.SIGNAL.BID;
        } else {
            signal = MarketStrategy.SIGNAL.OFFER;
        }

        int flag = new Random().nextInt(101);

        MarketStrategy.VOLATILITY volatility;

        switch (safetyLevel) {
            case LOW:
                volatility = MarketStrategy.VOLATILITY.LOW;
                break;
            case MEDIUM:
                volatility = MarketStrategy.VOLATILITY.MEDIUM;
                break;
            case HIGH:
                volatility = MarketStrategy.VOLATILITY.HIGH;
                break;
            case RANDOM:
            default:
                //Randomly choose the volatility
                if (flag < 34) {
                    volatility = MarketStrategy.VOLATILITY.LOW;
                } else if (flag >= 34 && flag < 67) {
                    volatility = MarketStrategy.VOLATILITY.MEDIUM;
                } else {
                    volatility = MarketStrategy.VOLATILITY.HIGH;
                }
                break;
        }

        this.signalDetails.setVolaility(volatility);
        this.signalDetails.setSignal(signal);

        return signalDetails;
    }
}
