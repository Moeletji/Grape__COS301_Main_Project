
import financialmarketsimulator.market.MarketEntity;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.StockManager;
import java.util.ArrayList;
import java.util.Random;

/**
 * @brief Main project execution class
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class FinancialMarketSimulator {

    public static void main(String[] args) {
        
        MarketExchange exchange = MarketExchange.getInstance("JSE");
        
        ArrayList<StockManager> managers =  new ArrayList();
        
        String [] names = {"INV", "IPSA", "LBH", "ABSAB", "AIP", "ABL", "HWN", "ILV"};
        
        int size = 8;
        
        for(int i = 0; i < size; i++){
             exchange.addStockManager(new StockManager(names[i], 10, 1000 * (Math.abs(new Random().nextInt() % 10))));
        }
        
        for(StockManager manager : managers){
            exchange.addStockManager(manager);
        }
        
        //10 entities
        //let's only trade investec stocks
        
        MarketEntity entity1 = new MarketEntity("BGP Holdings", "BGPLTD", "Investor", exchange, names[0]);
        MarketEntity entity2 = new MarketEntity("Steinhoff", "STH", "Investor", exchange, names[0]);
        MarketEntity entity3 = new MarketEntity("Boshoff", "BGPLTD", "Investor", exchange, names[0]);
        MarketEntity entity4 = new MarketEntity("MiguelGroup", "MMG", "Investor", exchange, names[0]);
        MarketEntity entity5 = new MarketEntity("SunBlue", "SBINC", "Investor", exchange, names[0]);
        MarketEntity entity6 = new MarketEntity("Rising Holdings", "RHSS", "Investor", exchange, names[0]);
        MarketEntity entity7 = new MarketEntity("JJK", "JJK", "Investor", exchange, names[0]);
        MarketEntity entity8 = new MarketEntity("FacTue", "FTT", "Investor", exchange, names[0]);
        MarketEntity entity9 = new MarketEntity("KellyKelly", "KKG", "Investor", exchange, names[0]);
        MarketEntity entity10 = new MarketEntity("Unique Holdings", "UHINC", "Investor", exchange, names[0]);
        
        
    }

}
