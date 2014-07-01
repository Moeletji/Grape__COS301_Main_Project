
import financialmarketsimulator.MarketManager;
import financialmarketsimulator.Order;
import financialmarketsimulator.OrderList;
import financialmarketsimulator.exception.EmptyException;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class OrderBookMatchingEngineTest {

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

    public static void main(String[] args) throws InterruptedException, EmptyException {
        MarketManager facebookStockManager = new MarketManager("FBK");

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
    }
}
