package financialmarketsimulator.workingTests;

import financialmarketsimulator.MarketManager;
import financialmarketsimulator.Order;
import financialmarketsimulator.OrderList;
import financialmarketsimulator.exception.BidNotFoundException;
import financialmarketsimulator.exception.EmptyException;
import financialmarketsimulator.exception.OrderHasNoValuesException;
import financialmarketsimulator.receipts.MatchedOrder;
import java.util.Vector;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class OrderListMatchingEngineTest {

    public static void showBook(OrderList book) throws EmptyException {
        System.out.printf("%n                    %s                      %n", book.getStockName());
        System.out.printf("--------------------------------------------%n");
        System.out.printf("         Bids                  Offers          %n");
        System.out.printf("--------------------------------------------%n");
        int i = 0;
        if(book.getBids().equals(null) || book.getOffers().equals(null))
        {
            throw new EmptyException();
        }
        while (i < Math.max(book.getBids().size(), book.getOffers().size())) {
            if (i < book.getBids().size()) {
                Order order = book.getBids().get(i);
                System.out.printf("% 10d% 10.3f", order.getQuantity(), order.getPrice());
            } else {
                System.out.printf("                    ");
            }
            System.out.printf(" | ");
            if (i < book.getOffers().size()) {
                Order order = book.getOffers().get(i);
                System.out.printf("%-10.3f% 10d", order.getPrice(), order.getQuantity());
            }
            System.out.printf("%n");
            i++;
        }
        System.out.printf("--------------------------------------------%n%n");
        System.out.println("END OF LOOP _____________________________________________________________");
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException, EmptyException, BidNotFoundException, OrderHasNoValuesException, CloneNotSupportedException {
        MarketManager facebookStockManager = new MarketManager("FBK");

        //Test whether orders are added into correct lists and in correct orders
        System.out.println("***************************************Test 1***************************************");
        showBook(facebookStockManager.getOrderList());
        Order order1 = new Order(34.50, 2000, "Daniel Smith", Order.SIDE.OFFER);
        facebookStockManager.acceptOrder(order1);
        showBook(facebookStockManager.getOrderList());
        Order order2 = new Order(40.00, 2000, "Jonny Bravo", Order.SIDE.OFFER);
        facebookStockManager.acceptOrder(order2);
        showBook(facebookStockManager.getOrderList());
        Order order3 = new Order(34.50, 1500, "Jimmy West", Order.SIDE.BID);
        facebookStockManager.acceptOrder(order3);
        showBook(facebookStockManager.getOrderList());
        Order order4 = new Order(34.50, 500, "Tim West", Order.SIDE.BID);
        facebookStockManager.acceptOrder(order4);
        showBook(facebookStockManager.getOrderList());
        Order order5 = new Order(40.00, 3000, "Tim West", Order.SIDE.OFFER);
        facebookStockManager.acceptOrder(order5);
        showBook(facebookStockManager.getOrderList());
        Order order6 = new Order(34.56, 1000, "Jonny Bravo", Order.SIDE.BID);
        facebookStockManager.acceptOrder(order6);
        showBook(facebookStockManager.getOrderList());
        Order order7 = new Order(35.00, 1000, "Daniel Smith", Order.SIDE.BID);
        facebookStockManager.acceptOrder(order7);
        showBook(facebookStockManager.getOrderList());
        Order order8 = new Order(40.00, 500, "Tim West", Order.SIDE.BID);
        facebookStockManager.acceptOrder(order8);
        showBook(facebookStockManager.getOrderList());
        Order order9 = new Order(40.00, 500, "Luis Mario", Order.SIDE.BID);
        facebookStockManager.acceptOrder(order9);
        showBook(facebookStockManager.getOrderList());
        Order order10 = new Order(20.89, 2000, "Jonny", Order.SIDE.OFFER);
        facebookStockManager.acceptOrder(order10);
        showBook(facebookStockManager.getOrderList());
        Order order11 = new Order(20.89, 2000, "Past Longstone", Order.SIDE.BID);
        facebookStockManager.acceptOrder(order11);
        showBook(facebookStockManager.getOrderList());
        Order order12 = new Order(34.56, 2000, "Daniel Smith", Order.SIDE.BID);
        facebookStockManager.acceptOrder(order12);
        showBook(facebookStockManager.getOrderList());
        
        //Test whether orders are removed correctly
        System.out.println("\n\n");
        System.out.println("***************************************Test 2***************************************");
        
        MarketManager yahooManager = new MarketManager();
        
        order1 = new Order(40.01, 2000, "Daniel Smith", Order.SIDE.OFFER);
        yahooManager.acceptOrder(order1);
        order2 = new Order(40.00, 2000, "Jonny Bravo", Order.SIDE.OFFER);
        yahooManager.acceptOrder(order2);
        order3 = new Order(34.50, 1500, "Jimmy West", Order.SIDE.BID);
        yahooManager.acceptOrder(order3);
        order4 = new Order(34.51, 500, "Tim West", Order.SIDE.BID);
        yahooManager.acceptOrder(order4);
        order5 = new Order(40.02, 3000, "Tim West", Order.SIDE.OFFER);
        yahooManager.acceptOrder(order5);
        order6 = new Order(34.56, 1000, "Jonny Bravo", Order.SIDE.BID);
        yahooManager.acceptOrder(order6);
        order7 = new Order(35.00, 1000, "Daniel Smith", Order.SIDE.BID);
        yahooManager.acceptOrder(order7);
        order8 = new Order(40.00, 500, "Tim West", Order.SIDE.BID);
        yahooManager.acceptOrder(order8);
        order9 = new Order(40.00, 500, "Luis Mario", Order.SIDE.BID);
        yahooManager.acceptOrder(order9);
        order10 = new Order(20.89, 2000, "Jonny", Order.SIDE.OFFER);
        yahooManager.acceptOrder(order10);
        order11 = new Order(20.89, 2000, "Past Longstone", Order.SIDE.BID);
        yahooManager.acceptOrder(order11);
        order12 = new Order(34.56, 2000, "Daniel Smith", Order.SIDE.BID);
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
        
        order1 = new Order(40.01, 2000, "Daniel Smith", Order.SIDE.OFFER);
        yahooManager.acceptOrder(order1);
        order2 = new Order(40.00, 2000, "Jonny Bravo", Order.SIDE.OFFER);
        yahooManager.acceptOrder(order2);
        order3 = new Order(34.50, 1500, "Jimmy West", Order.SIDE.BID);
        yahooManager.acceptOrder(order3);
        order4 = new Order(34.51, 500, "Tim West", Order.SIDE.BID);
        yahooManager.acceptOrder(order4);
        order5 = new Order(40.02, 3000, "Tim West", Order.SIDE.OFFER);
        yahooManager.acceptOrder(order5);
        order6 = new Order(34.56, 1000, "Jonny Bravo", Order.SIDE.BID);
        yahooManager.acceptOrder(order6);
        order7 = new Order(35.00, 1000, "Daniel Smith", Order.SIDE.BID);
        yahooManager.acceptOrder(order7);
        order8 = new Order(40.00, 500, "Tim West", Order.SIDE.BID);
        yahooManager.acceptOrder(order8);
        order9 = new Order(40.00, 500, "Luis Mario", Order.SIDE.BID);
        yahooManager.acceptOrder(order9);
        order10 = new Order(20.89, 2000, "Jonny", Order.SIDE.OFFER);
        yahooManager.acceptOrder(order10);
        order11 = new Order(33.33, 2000, "Past Longstone", Order.SIDE.BID);
        yahooManager.acceptOrder(order11);
        order12 = new Order(34.56, 2000, "Daniel Smith", Order.SIDE.BID);
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
        Order order13 = new Order(34.50, 2000, "Daniel Smith", Order.SIDE.OFFER);
        googleManager.acceptOrder(order13);
        showBook(googleManager.getOrderList());
        Order order14 = new Order(40.00, 2000, "Jonny Bravo", Order.SIDE.OFFER);
        googleManager.acceptOrder(order14);
        showBook(googleManager.getOrderList());
        Order order15 = new Order(34.50, 1500, "Jimmy West", Order.SIDE.BID);
        googleManager.acceptOrder(order15);
        showBook(googleManager.getOrderList());
        Order order16 = new Order(34.50, 500, "Tim West", Order.SIDE.BID);
        googleManager.acceptOrder(order16);
        showBook(googleManager.getOrderList());
        Order order17 = new Order(40.00, 3000, "Tim West", Order.SIDE.OFFER);
        googleManager.acceptOrder(order17);
        showBook(googleManager.getOrderList());
        Order order18 = new Order(34.56, 1000, "Jonny Bravo", Order.SIDE.BID);
        googleManager.acceptOrder(order18);
        showBook(googleManager.getOrderList());
        Order order19 = new Order(35.00, 1000, "Daniel Smith", Order.SIDE.BID);
        googleManager.acceptOrder(order19);
        showBook(googleManager.getOrderList());
        Order order20 = new Order(40.00, 500, "Tim West", Order.SIDE.BID);
        googleManager.acceptOrder(order20);
        showBook(googleManager.getOrderList());
        Order order21 = new Order(40.00, 500, "Luis Mario", Order.SIDE.BID);
        googleManager.acceptOrder(order21);
        showBook(googleManager.getOrderList());
        Order order22 = new Order(20.89, 2000, "Jonny", Order.SIDE.OFFER);
        googleManager.acceptOrder(order22);
        showBook(googleManager.getOrderList());
        Order order23 = new Order(20.89, 2000, "Past Longstone", Order.SIDE.BID);
        googleManager.acceptOrder(order23);
        showBook(googleManager.getOrderList());
        Order order24 = new Order(34.56, 2000, "Daniel Smith", Order.SIDE.BID);
        googleManager.acceptOrder(order24);
        showBook(googleManager.getOrderList());
        
        Vector<MatchedOrder> testTrades = googleManager.getOrderList().getTrades();
        
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
    }
}