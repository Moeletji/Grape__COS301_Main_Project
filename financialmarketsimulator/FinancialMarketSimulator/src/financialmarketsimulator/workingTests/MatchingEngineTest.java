package financialmarketsimulator.workingTests;

import financialmarketsimulator.market.MarketManager;
import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.exception.ItemNotFoundException;
import financialmarketsimulator.exception.OrderHasNoValuesException;
import financialmarketsimulator.marketData.MatchedOrder;
import java.util.Vector;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MatchingEngineTest {

    public static void showBook(MarketEntryAttemptBook book) throws ItemNotFoundException {
        System.out.printf("%n                    %s                      %n", book.getStockName());
        System.out.printf("--------------------------------------------%n");
        System.out.printf("         Bids                  Offers          %n");
        System.out.printf("--------------------------------------------%n");
        int i = 0;
        if(book.getBids().equals(null) || book.getOffers().equals(null))
        {
            throw new ItemNotFoundException();
        }
        while (i < Math.max(book.getBids().size(), book.getOffers().size())) {
            if (i < book.getBids().size()) {
                MarketEntryAttempt order = book.getBids().get(i);
                System.out.printf("% 10d% 10.3f", order.getNumOfShares(), order.getPrice());
            } else {
                System.out.printf("                    ");
            }
            System.out.printf(" | ");
            if (i < book.getOffers().size()) {
                MarketEntryAttempt order = book.getOffers().get(i);
                System.out.printf("%-10.3f% 10d", order.getPrice(), order.getNumOfShares());
            }
            System.out.printf("%n");
            i++;
        }
        System.out.printf("--------------------------------------------%n%n");
        System.out.println("END OF LOOP _____________________________________________________________");
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException, OrderHasNoValuesException, CloneNotSupportedException, ItemNotFoundException {
        MarketManager facebookStockManager = new MarketManager("FBK");

        //Test whether orders are added into correct lists and in correct orders
        System.out.println("***************************************Test 1***************************************");
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order1 = new MarketEntryAttempt(34.50, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.OFFER);
        facebookStockManager.acceptOrder(order1);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order2 = new MarketEntryAttempt(40.00, 2000, "Jonny Bravo", MarketEntryAttempt.SIDE.OFFER);
        facebookStockManager.acceptOrder(order2);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order3 = new MarketEntryAttempt(34.50, 1500, "Jimmy West", MarketEntryAttempt.SIDE.BID);
        facebookStockManager.acceptOrder(order3);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order4 = new MarketEntryAttempt(34.50, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        facebookStockManager.acceptOrder(order4);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order5 = new MarketEntryAttempt(40.00, 3000, "Tim West", MarketEntryAttempt.SIDE.OFFER);
        facebookStockManager.acceptOrder(order5);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order6 = new MarketEntryAttempt(34.56, 1000, "Jonny Bravo", MarketEntryAttempt.SIDE.BID);
        facebookStockManager.acceptOrder(order6);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order7 = new MarketEntryAttempt(35.00, 1000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        facebookStockManager.acceptOrder(order7);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order8 = new MarketEntryAttempt(40.00, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        facebookStockManager.acceptOrder(order8);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order9 = new MarketEntryAttempt(40.00, 500, "Luis Mario", MarketEntryAttempt.SIDE.BID);
        facebookStockManager.acceptOrder(order9);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order10 = new MarketEntryAttempt(20.89, 2000, "Jonny", MarketEntryAttempt.SIDE.OFFER);
        facebookStockManager.acceptOrder(order10);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order11 = new MarketEntryAttempt(20.89, 2000, "Past Longstone", MarketEntryAttempt.SIDE.BID);
        facebookStockManager.acceptOrder(order11);
        showBook(facebookStockManager.getOrderList());
        MarketEntryAttempt order12 = new MarketEntryAttempt(34.56, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        facebookStockManager.acceptOrder(order12);
        showBook(facebookStockManager.getOrderList());
        
        //Test whether orders are removed correctly
        System.out.println("\n\n");
        System.out.println("***************************************Test 2***************************************");
        
        MarketManager yahooManager = new MarketManager();
        
        order1 = new MarketEntryAttempt(40.01, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.OFFER);
        yahooManager.acceptOrder(order1);
        order2 = new MarketEntryAttempt(40.00, 2000, "Jonny Bravo", MarketEntryAttempt.SIDE.OFFER);
        yahooManager.acceptOrder(order2);
        order3 = new MarketEntryAttempt(34.50, 1500, "Jimmy West", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order3);
        order4 = new MarketEntryAttempt(34.51, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order4);
        order5 = new MarketEntryAttempt(40.02, 3000, "Tim West", MarketEntryAttempt.SIDE.OFFER);
        yahooManager.acceptOrder(order5);
        order6 = new MarketEntryAttempt(34.56, 1000, "Jonny Bravo", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order6);
        order7 = new MarketEntryAttempt(35.00, 1000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order7);
        order8 = new MarketEntryAttempt(40.00, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order8);
        order9 = new MarketEntryAttempt(40.00, 500, "Luis Mario", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order9);
        order10 = new MarketEntryAttempt(20.89, 2000, "Jonny", MarketEntryAttempt.SIDE.OFFER);
        yahooManager.acceptOrder(order10);
        order11 = new MarketEntryAttempt(20.89, 2000, "Past Longstone", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order11);
        order12 = new MarketEntryAttempt(34.56, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order12);
        
        showBook(yahooManager.getOrderList());
        yahooManager.removeOrder(order1);
        showBook(yahooManager.getOrderList());
        yahooManager.acceptOrder(order1);
        yahooManager.removeOrder(order11);
        showBook(yahooManager.getOrderList());
        yahooManager.removeOrder(order10.getOrderID(), order10.getSide());
        showBook(yahooManager.getOrderList());
        
        
        //Test whether orders are edited and updated correctly
        System.out.println("\n\n");
        System.out.println("***************************************Test 3***************************************");
        
        order1 = new MarketEntryAttempt(40.01, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.OFFER);
        yahooManager.acceptOrder(order1);
        order2 = new MarketEntryAttempt(40.00, 2000, "Jonny Bravo", MarketEntryAttempt.SIDE.OFFER);
        yahooManager.acceptOrder(order2);
        order3 = new MarketEntryAttempt(34.50, 1500, "Jimmy West", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order3);
        order4 = new MarketEntryAttempt(34.51, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order4);
        order5 = new MarketEntryAttempt(40.02, 3000, "Tim West", MarketEntryAttempt.SIDE.OFFER);
        yahooManager.acceptOrder(order5);
        order6 = new MarketEntryAttempt(34.56, 1000, "Jonny Bravo", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order6);
        order7 = new MarketEntryAttempt(35.00, 1000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order7);
        order8 = new MarketEntryAttempt(40.00, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order8);
        order9 = new MarketEntryAttempt(40.00, 500, "Luis Mario", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order9);
        order10 = new MarketEntryAttempt(20.89, 2000, "Jonny", MarketEntryAttempt.SIDE.OFFER);
        yahooManager.acceptOrder(order10);
        order11 = new MarketEntryAttempt(33.33, 2000, "Past Longstone", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order11);
        order12 = new MarketEntryAttempt(34.56, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        yahooManager.acceptOrder(order12);
        
        showBook(yahooManager.getOrderList());
        yahooManager.editOrder(order1.getOrderID(), 10000000, 500000, order1.getSide());
        showBook(yahooManager.getOrderList());
        yahooManager.editOrder(order11.getOrderID(), order11.getPrice(), 500000, order11.getSide());
        showBook(yahooManager.getOrderList());
        
         //Test whether orders are matched correctly
        System.out.println("***************************************Test 4***************************************");
        
        MarketManager googleManager = new MarketManager();
        
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order13 = new MarketEntryAttempt(34.50, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.OFFER);
        googleManager.acceptOrder(order13);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order14 = new MarketEntryAttempt(40.00, 2000, "Jonny Bravo", MarketEntryAttempt.SIDE.OFFER);
        googleManager.acceptOrder(order14);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order15 = new MarketEntryAttempt(34.50, 1500, "Jimmy West", MarketEntryAttempt.SIDE.BID);
        googleManager.acceptOrder(order15);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order16 = new MarketEntryAttempt(34.50, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        googleManager.acceptOrder(order16);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order17 = new MarketEntryAttempt(40.00, 3000, "Tim West", MarketEntryAttempt.SIDE.OFFER);
        googleManager.acceptOrder(order17);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order18 = new MarketEntryAttempt(34.56, 1000, "Jonny Bravo", MarketEntryAttempt.SIDE.BID);
        googleManager.acceptOrder(order18);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order19 = new MarketEntryAttempt(35.00, 1000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        googleManager.acceptOrder(order19);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order20 = new MarketEntryAttempt(40.00, 500, "Tim West", MarketEntryAttempt.SIDE.BID);
        googleManager.acceptOrder(order20);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order21 = new MarketEntryAttempt(40.00, 500, "Luis Mario", MarketEntryAttempt.SIDE.BID);
        googleManager.acceptOrder(order21);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order22 = new MarketEntryAttempt(20.89, 2000, "Jonny", MarketEntryAttempt.SIDE.OFFER);
        googleManager.acceptOrder(order22);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order23 = new MarketEntryAttempt(20.89, 2000, "Past Longstone", MarketEntryAttempt.SIDE.BID);
        googleManager.acceptOrder(order23);
        showBook(googleManager.getOrderList());
        MarketEntryAttempt order24 = new MarketEntryAttempt(34.56, 2000, "Daniel Smith", MarketEntryAttempt.SIDE.BID);
        googleManager.acceptOrder(order24);
        showBook(googleManager.getOrderList());
        
        Vector<MatchedOrder> testTrades = googleManager.getOrderList().getMatchedOrders();
        
        for(int i=0; i<testTrades.size();i++)
        {
            System.out.println();
            System.out.println("Trade #" + i+1);
            System.out.println("Trade ID: " + testTrades.get(i).getID());
            System.out.println("Trade Time: " + testTrades.get(i).getDateIssued());
            System.out.println("Trade Price: " + testTrades.get(i).getPrice());
            System.out.println("Trade Quantity: " + testTrades.get(i).getQuantity());
            System.out.println();
        }
        
        //Test whether numbers are rounded to decimal places
        /*System.out.println("\n\n");
        System.out.println("***************************************Test 4***************************************");
        double number = 56.563423234;
        double answer;
        
        MarketManager man = new MarketManager();
        
        answer = man.getOrderList().roundNumber(number);
        System.out.println("Expected value: 56.56 \n Actual Value: " + answer + "\n");
        
        answer = man.getOrderList().roundNumber(number, 4);
        System.out.println("Expected value: 56.5634 \n Actual Value: " + answer + "\n");
        
        number = 56;
        answer = man.getOrderList().roundNumber(number);
        System.out.println("Expected value: 56.0 \n Actual Value: " + answer + "\n");
        
        answer = man.getOrderList().roundNumber(number, 3);
        System.out.println("Expected value: 56.0 \n Actual Value: " + answer + "\n");*/
    }
}