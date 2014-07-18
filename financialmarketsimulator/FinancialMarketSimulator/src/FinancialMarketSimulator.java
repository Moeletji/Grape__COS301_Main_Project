
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.StockManager;
import java.util.ArrayList;

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
             exchange.addStockManager(new StockManager(names[i]));
        }
        
        for(StockManager manager : managers){
            exchange.addStockManager(manager);
        }
    }

}
