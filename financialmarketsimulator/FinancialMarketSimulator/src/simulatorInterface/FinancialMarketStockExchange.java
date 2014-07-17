package simulatorInterface;


import financialmarketsimulator.exception.StockAlreadyExistsException;
import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.StockManager;
import java.util.ArrayList;

/**
 * @brief Stock Market class
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class FinancialMarketStockExchange {
    private ArrayList<StockManager> stockManagers;
    
    public FinancialMarketStockExchange(){
        stockManagers = new ArrayList();
        
        stockManagers.add(new StockManager("FBK"));
        stockManagers.add(new StockManager("ANG"));
        stockManagers.add(new StockManager("YHO"));
        stockManagers.add(new StockManager("GMS"));
        stockManagers.add(new StockManager("GGL"));
        stockManagers.add(new StockManager("BMW"));
        stockManagers.add(new StockManager("ONX"));
        
    }

    public boolean placeMarketEntryAttempt(MarketEntryAttempt attempt, String stockName) throws InterruptedException{
        
        int i = 0;
        for(StockManager stockManager : stockManagers){
            if(stockManager.getStockName().equals(stockName)){
                return stockManagers.get(i).acceptOrder(attempt);
            }
            i++;
        }
        return false;
    }
    
    public MarketEntryAttemptBook getOrderBook(String stockName){
        int i = 0;
        for(StockManager stockManager : stockManagers){
            if(stockManager.getStockName().equals(stockName)){
                return stockManagers.get(i).getOrderList();
            }
            i++;
        }
        return null;
    }
    
    public StockManager getStockManager(String stockName){
        for(StockManager stockManager : stockManagers){
            if(stockManager.getStockName().equals(stockName)){
                return stockManager;
            }
        }
        return null;
    }
}
