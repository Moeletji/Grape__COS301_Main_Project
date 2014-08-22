

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketParticipant;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.StockManager;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * @brief Main project execution class
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class FinancialMarketSimulator {

    public static void main(String[] args) throws NotEnoughDataException {

        MarketExchange exchange = MarketExchange.getInstance("JSE");

        ArrayList<StockManager> managers = new ArrayList();

        String[] names = {"INV", "IPSA", "LBH"};

        for (int i = 0; i < names.length; i++) {
            exchange.addStockManager(new StockManager(names[i], 10, 1000 * (Math.abs(new Random().nextInt() % 10))));
        }

        //10 entities
        //let's only trade investec stocks for now
        MarketParticipant entity1 = new MarketParticipant("BGP Holdings", "BGPLTD", exchange, names[0]);
        MarketParticipant entity2 = new MarketParticipant("Steinhoff", "STH", exchange, names[0]);
        MarketParticipant entity3 = new MarketParticipant("Boshoff", "BGPLTD", exchange, names[0]);
        MarketParticipant entity4 = new MarketParticipant("MiguelGroup", "MMG", exchange, names[0]);
        MarketParticipant entity5 = new MarketParticipant("SunBlue", "SBINC", exchange, names[0]);
        MarketParticipant entity6 = new MarketParticipant("Rising Holdings", "RHSS", exchange, names[0]);
        MarketParticipant entity7 = new MarketParticipant("JJK", "JJK", exchange, names[0]);
        MarketParticipant entity8 = new MarketParticipant("FacTue", "FTT", exchange, names[0]);
        MarketParticipant entity9 = new MarketParticipant("KellyKelly", "KKG", exchange, names[0]);
        MarketParticipant entity10 = new MarketParticipant("Unique Holdings", "UHINC", exchange, names[0]);

        StockManager manager = exchange.getStocksManagers().get("INV");
        
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
