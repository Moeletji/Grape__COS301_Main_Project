
import financialmarketsimulator.market.MarketEntryAttempt;
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
        //let's only trade investec stocks for now
        
        MarketParticipant entity1 = new MarketParticipant("BGP Holdings", "BGPLTD", "Investor", exchange, names[0]);
        MarketParticipant entity2 = new MarketParticipant("Steinhoff", "STH", "Investor", exchange, names[0]);
        MarketParticipant entity3 = new MarketParticipant("Boshoff", "BGPLTD", "Investor", exchange, names[0]);
        MarketParticipant entity4 = new MarketParticipant("MiguelGroup", "MMG", "Investor", exchange, names[0]);
        MarketParticipant entity5 = new MarketParticipant("SunBlue", "SBINC", "Investor", exchange, names[0]);
        MarketParticipant entity6 = new MarketParticipant("Rising Holdings", "RHSS", "Investor", exchange, names[0]);
        MarketParticipant entity7 = new MarketParticipant("JJK", "JJK", "Investor", exchange, names[0]);
        MarketParticipant entity8 = new MarketParticipant("FacTue", "FTT", "Investor", exchange, names[0]);
        MarketParticipant entity9 = new MarketParticipant("KellyKelly", "KKG", "Investor", exchange, names[0]);
        MarketParticipant entity10 = new MarketParticipant("Unique Holdings", "UHINC", "Investor", exchange, names[0]);
        
        
        Vector offers = exchange.getBook("INV").getOffers();
        Vector bids = exchange.getBook("INV").getBids();
        Vector matched = exchange.getBook("INV").getMatchedOrders();
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        entity1.start();
        
        offers = exchange.getBook("INV").getOffers();
        bids = exchange.getBook("INV").getBids();
        matched = exchange.getBook("INV").getMatchedOrders();
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        entity2.start();
        
        offers = exchange.getBook("INV").getOffers();
        bids = exchange.getBook("INV").getBids();
        matched = exchange.getBook("INV").getMatchedOrders();
       
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        entity3.start();
        
        offers = exchange.getBook("INV").getOffers();
        bids = exchange.getBook("INV").getBids();
        matched = exchange.getBook("INV").getMatchedOrders();
        
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        entity4.start();
        
        offers = exchange.getBook("INV").getOffers();
        bids = exchange.getBook("INV").getBids();
        matched = exchange.getBook("INV").getMatchedOrders();
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        entity5.start();
        
        offers = exchange.getBook("INV").getOffers();
        bids = exchange.getBook("INV").getBids();
        matched = exchange.getBook("INV").getMatchedOrders();
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        entity6.start();
        
        offers = exchange.getBook("INV").getOffers();
        bids = exchange.getBook("INV").getBids();
        matched = exchange.getBook("INV").getMatchedOrders();
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        entity7.start();
        
        offers = exchange.getBook("INV").getOffers();
        bids = exchange.getBook("INV").getBids();
        matched = exchange.getBook("INV").getMatchedOrders();
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        entity8.start();
        
        offers = exchange.getBook("INV").getOffers();
        bids = exchange.getBook("INV").getBids();
        matched = exchange.getBook("INV").getMatchedOrders();
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        entity9.start();
        
        offers = exchange.getBook("INV").getOffers();
        bids = exchange.getBook("INV").getBids();
        matched = exchange.getBook("INV").getMatchedOrders();
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        entity10.start();
        
        offers = exchange.getBook("INV").getOffers();
        bids = exchange.getBook("INV").getBids();
        matched = exchange.getBook("INV").getMatchedOrders();
        
        for(int i = 0; i < offers.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)offers.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n*****************");
        
        for(int i = 0; i < bids.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)bids.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n***********************");
        
        for(int i = 0; i < matched.size(); i++){
            MarketEntryAttempt attempt = (MarketEntryAttempt)matched.get(i);
            System.out.println(attempt.getNumOfShares() + "  " + attempt.getPrice() + "\n");
        }
        
        System.out.println("\n\n****************************");
        
        
        entity1.terminateTrading();
        entity2.terminateTrading();
        entity3.terminateTrading();
        entity4.terminateTrading();
        entity5.terminateTrading();
        entity6.terminateTrading();
        entity7.terminateTrading();
        entity8.terminateTrading();
        entity9.terminateTrading();
        entity10.terminateTrading();
      
    }

}
