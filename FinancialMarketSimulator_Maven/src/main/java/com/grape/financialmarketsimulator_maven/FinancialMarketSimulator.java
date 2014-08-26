
import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketParticipant;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketStrategy;
import financialmarketsimulator.market.StockManager;
import financialmarketsimulator.strategies.Phantom;
import java.util.ArrayList;
import java.util.Random;

/**
 * @brief Main project execution class
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class FinancialMarketSimulator {

    public static void main(String[] args) throws NotEnoughDataException {

        MarketExchange exchange = MarketExchange.getInstance("JSE");

        String[] names = {"INV", "IPSA", "LBH"};

        for (int i = 0; i < names.length; i++) {
            exchange.addStockManager(new StockManager(names[i], 10, 1000 * (Math.abs(new Random().nextInt() % 10))));
        }

        //10 Strategies
        //Trading Strategies
        MarketStrategy strategy1 = new Phantom(exchange, Phantom.LEVEL_PRICE.LOW, Phantom.LEVEL_SHARES.LOW);
        MarketStrategy strategy2 = new Phantom(exchange, Phantom.LEVEL_PRICE.MEDIUM, Phantom.LEVEL_SHARES.MEDIUM);
        MarketStrategy strategy3 = new Phantom(exchange, Phantom.LEVEL_PRICE.HIGH, Phantom.LEVEL_SHARES.HIGH);
        MarketStrategy strategy4 = new Phantom(exchange, Phantom.LEVEL_PRICE.LOW, Phantom.LEVEL_SHARES.HIGH);
        MarketStrategy strategy5 = new Phantom(exchange, Phantom.LEVEL_PRICE.HIGH, Phantom.LEVEL_SHARES.LOW);
        MarketStrategy strategy6 = new Phantom(exchange, Phantom.LEVEL_PRICE.MEDIUM, Phantom.LEVEL_SHARES.LOW);
        MarketStrategy strategy7 = new Phantom(exchange, Phantom.LEVEL_PRICE.HIGH, Phantom.LEVEL_SHARES.MEDIUM);
        MarketStrategy strategy8 = new Phantom(exchange, Phantom.LEVEL_PRICE.LOW, Phantom.LEVEL_SHARES.HIGH);
        MarketStrategy strategy9 = new Phantom(exchange, Phantom.LEVEL_PRICE.HIGH, Phantom.LEVEL_SHARES.LOW);
        MarketStrategy strategy10 = new Phantom(exchange, Phantom.LEVEL_PRICE.MEDIUM, Phantom.LEVEL_SHARES.MEDIUM);

        //10 entities
        //let's only trade investec stocks for now
        MarketParticipant entity1 = new MarketParticipant("BGP Holdings", "BGPLTD", exchange, names[0], strategy1);
        MarketParticipant entity2 = new MarketParticipant("Steinhoff", "STH", exchange, names[0], strategy2);
        MarketParticipant entity3 = new MarketParticipant("Boshoff", "BGPLTD", exchange, names[0], strategy3);
        MarketParticipant entity4 = new MarketParticipant("MiguelGroup", "MMG", exchange, names[0], strategy4);
        MarketParticipant entity5 = new MarketParticipant("SunBlue", "SBINC", exchange, names[0], strategy5);
        MarketParticipant entity7 = new MarketParticipant("JJK", "JJK", exchange, names[0], strategy6);
        MarketParticipant entity8 = new MarketParticipant("FacTue", "FTT", exchange, names[0], strategy7);
        MarketParticipant entity9 = new MarketParticipant("KellyKelly", "KKG", exchange, names[0], strategy8);
        MarketParticipant entity10 = new MarketParticipant("Unique Holdings", "UHINC", exchange, names[0], strategy9);
        MarketParticipant entity6 = new MarketParticipant("Rising Holdings", "RHSS", exchange, names[0], strategy10);

        StockManager manager = exchange.getStocksManagers().get("INV");
        
        if(manager == null){
            System.out.println("Manager not found");
            return;
        }

        manager.attach(entity1);
        manager.attach(entity2);
        manager.attach(entity3);
        manager.attach(entity4);
        manager.attach(entity5);
        manager.attach(entity6);
        manager.attach(entity7);
        manager.attach(entity8);
        manager.attach(entity9);
        manager.attach(entity10);

        manager.start();

        entity1.start();
        entity2.start();

    }
}
