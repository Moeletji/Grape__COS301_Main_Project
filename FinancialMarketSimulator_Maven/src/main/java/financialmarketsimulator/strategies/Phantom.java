package financialmarketsimulator.strategies;

import financialmarketsimulator.market.MarketStrategy;
import java.util.Random;

/**
 * @brief @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class Phantom extends MarketStrategy {

    public Phantom() {
        super("Phantom");
    }

    @Override
    public SignalDetails trade() {

        //Choose a random SIZE to place an MarketEntryAttempt on
        int flag = new Random().nextInt(11);

        MarketStrategy.SIGNAL signal;

        if (flag > 5) {
            signal = MarketStrategy.SIGNAL.BUY;
        } else {
            signal = MarketStrategy.SIGNAL.SELL;
        }

        flag = new Random().nextInt(101);

        MarketStrategy.LENGTH length;

        if (flag < 34) {
            length = MarketStrategy.LENGTH.LOW;
        } else if (flag >= 34 && flag < 67) {
            length = MarketStrategy.LENGTH.MEDUIM;
        } else {
            length = MarketStrategy.LENGTH.HIGH;
        }

        this.signalDetails.setLength(length);
        this.signalDetails.setSignal(signal);

        return signalDetails;
    }
}
