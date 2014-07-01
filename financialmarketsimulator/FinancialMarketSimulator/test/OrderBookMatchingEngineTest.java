
import financialmarketsimulator.MarketManager;
import financialmarketsimulator.Order;
import financialmarketsimulator.OrderList;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class OrderBookMatchingEngineTest {

    public static void showBook(OrderList book) {
        System.out.printf("%n                    %s                      %n", book.getStockName());
        System.out.printf("--------------------------------------------%n");
        System.out.printf("         Offers                  Bids          %n");
        System.out.printf("--------------------------------------------%n");
        int i = 0;
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

    }

    public static void main(String[] args) throws InterruptedException {
        MarketManager facebookManager = new MarketManager("FBK");

        showBook(facebookManager.getOrderList());
        Order order1 = new Order(34.50, 2000, "Daniel Smith", Order.SIDE.OFFER);
        facebookManager.acceptOrder(order1);
        showBook(facebookManager.getOrderList());
        Order order2 = new Order(40.00, 2000, "Jonny Bravo", Order.SIDE.OFFER);
        facebookManager.acceptOrder(order2);
        showBook(facebookManager.getOrderList());
        Order order3 = new Order(34.50, 1500, "Jimmy West", Order.SIDE.BID);
        facebookManager.acceptOrder(order3);
        showBook(facebookManager.getOrderList());
        Order order4 = new Order(34.50, 500, "Tim West", Order.SIDE.BID);
        facebookManager.acceptOrder(order4);
        showBook(facebookManager.getOrderList());
        Order order5 = new Order(40.00, 3000, "Tim West", Order.SIDE.OFFER);
        facebookManager.acceptOrder(order5);
        showBook(facebookManager.getOrderList());
        Order order6 = new Order(34.56, 1000, "Jonny Bravo", Order.SIDE.BID);
        facebookManager.acceptOrder(order6);
        showBook(facebookManager.getOrderList());
        Order order7 = new Order(35.00, 1000, "Daniel Smith", Order.SIDE.BID);
        facebookManager.acceptOrder(order7);
        showBook(facebookManager.getOrderList());
        Order order8 = new Order(40.00, 500, "Tim West", Order.SIDE.BID);
        facebookManager.acceptOrder(order8);
        showBook(facebookManager.getOrderList());
        Order order9 = new Order(40.00, 500, "Luis Mario", Order.SIDE.BID);
        facebookManager.acceptOrder(order9);
        showBook(facebookManager.getOrderList());
        Order order10 = new Order(20.89, 2000, "Jonny", Order.SIDE.OFFER);
        facebookManager.acceptOrder(order10);
        showBook(facebookManager.getOrderList());
        Order order11 = new Order(20.89, 2000, "Past Longstone", Order.SIDE.BID);
        facebookManager.acceptOrder(order11);
        showBook(facebookManager.getOrderList());
        Order order12 = new Order(34.56, 2000, "Daniel Smith", Order.SIDE.BID);
        facebookManager.acceptOrder(order12);
        showBook(facebookManager.getOrderList());
    }
}
