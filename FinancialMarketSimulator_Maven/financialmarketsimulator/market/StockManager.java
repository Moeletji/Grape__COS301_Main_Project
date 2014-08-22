package financialmarketsimulator.market;

import financialmarketsimulator.exception.OrderHasNoValuesException;
import financialmarketsimulator.marketData.MatchedMarketEntryAttemptUpdate;
import financialmarketsimulator.marketData.Message;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @brief The StockManager class is the super class from which each concrete
 * stock/security manager will inherit.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class StockManager extends Thread {

    //Name of the stock
    private String stockName;
    //An order book of all the orders accepted
    private final MarketEntryAttemptBook orderList;
    //Snapshot of the market data
    private MatchedMarketEntryAttemptUpdate marketSnapShot;
    //List of all MarketParticipants
    private ArrayList<MarketParticipant> participants;
    //Message queue
    private ConcurrentLinkedQueue<Message> messageQueue;
    //Thread started
    private boolean started;
    //Thread paused
    private boolean paused;

    /**
     * @brief MarketManager Constructor
     */
    public StockManager(String stockName, long timePeriod, int totalNumberOfShares) {
        this.orderList = new MarketEntryAttemptBook(stockName, timePeriod, totalNumberOfShares);
        this.stockName = stockName;
        this.participants = new ArrayList<MarketParticipant>();
        this.messageQueue = new ConcurrentLinkedQueue<>();
    }

    /**
     * @brief MarketManager Constructor
     *
     * @param sName Name of the stock
     */
    public StockManager(String sName) {
        this.stockName = sName;
        this.orderList = new MarketEntryAttemptBook(stockName, 10, 1000);
        this.participants = new ArrayList<MarketParticipant>();
        this.messageQueue = new ConcurrentLinkedQueue<>();
    }

    public String getStockName() {
        return stockName;
    }

    /**
     * @brief Acknowledgement of the bid being accepted by the market manager
     * @param order MarketEntryAttempt object to be accepted
     * @return Returns a receipt object that acknowledges that a bid was
     * accepted
     * @throws InterruptedException
     */
    public boolean acceptOrder(MarketEntryAttempt order) throws InterruptedException {
        if (order.getPrice() == 0 || order.getNumOfShares() == 0) {
            return false;
        }

        orderList.placeOrder(order);
        return true;
    }

    /**
     * @brief get a list of all orders
     * @return a list of orders
     */
    public MarketEntryAttemptBook getOrderList() {
        return orderList;
    }

    /**
     * @brief Acknowledgement of the bid being removed by the market manager
     *
     * @param order MarketEntryAttempt to be removed
     * @return MarketEntryAttempt to be removed
     */
    public MarketEntryAttempt removeOrder(MarketEntryAttempt order) {
        return orderList.removeOrder(order);
    }

    /**
     * @brief Acknowledgement of the bid being removed by the market manager
     * @param orderId id of order
     * @param orderSide side of order
     * @return MarketEntryAttempt to be removed
     * @brief Acknowledgement of the bid being removed by the market manager
     */
    public MarketEntryAttempt removeOrder(String orderId, MarketEntryAttempt.SIDE orderSide) {
        return orderList.removeOrder(orderId, orderSide);
    }

    /**
     * @brief Edit the details of the order of a share
     *
     * @param orderId id of the order
     * @param price price of the order
     * @param numberShares number of shares by th e order
     * @param side side of the order
     * @throws OrderHasNoValuesException
     */
    public void editOrder(String orderId, double price, int numberShares, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException {
        orderList.alterOrder(orderId, price, numberShares, side);
    }

    /**
     * @throws java.lang.CloneNotSupportedException
     * @brief Edit the details of the order of a share
     *
     * @param orderId id of the order
     * @param price price of the order
     * @param side side of the order
     * @throws OrderHasNoValuesException
     */
    public void editOrder(String orderId, double price, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException {
        orderList.alterOrder(orderId, price, side);
    }

    /**
     * @throws java.lang.CloneNotSupportedException
     * @brief Edit the details of the order of a share
     *
     * @param orderId id of the order
     * @param numberShares number of shares by th e order
     * @param side side of the order
     * @throws OrderHasNoValuesException
     */
    public void editOrder(String orderId, int numberShares, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException {
        orderList.alterOrder(orderId, numberShares, side);
    }

    public MatchedMarketEntryAttemptUpdate getMarketSnapShot() {
        return new MatchedMarketEntryAttemptUpdate(orderList.getMatchedOrders());
    }

    /**
     * @brief add MarketParticipant
     * @param participant MarketParticipant to be added
     */
    public void attach(MarketParticipant participant) {
        participants.add(participant);
    }

    /**
     * @brief remove a MarketParticipant
     * @param participant MarketParticipant to be removed
     */
    public void detach(MarketParticipant participant) {
        participants.remove(participant);
    }
    
    public void sendMessage(Message message){
        this.messageQueue.add(message);
    } 

    public void Notify() {
        for (MarketParticipant participant : participants) {
            participant.update(this);
        }
    }

    /**
     * @brief StockManager thread that receives Messages and updates the market
     * according to those messages
     */
    @Override
    public void run() {
        try {
            synchronized (this) {
                while (paused) {
                    wait();
                }
            }
        } catch (InterruptedException exception) {
            System.out.println("Interrupted Exception " + super.getId());
        }

        while (true) {
            while (messageQueue.isEmpty()) {}

            synchronized (this) {
                if (!messageQueue.isEmpty()) {
                    Message message = messageQueue.poll();

                    if (message != null) {
                        switch (message.getType()) {
                            case AMEND: {
                                double newPrice = message.getPrice();
                                int newShares = message.getShares();
                                String id = message.getAttempt().getOrderID();
                                MarketEntryAttempt.SIDE side = message.getAttempt().getSide();

                                try {
                                    this.editOrder(id, newPrice, newShares, side);
                                } catch (OrderHasNoValuesException | CloneNotSupportedException ex) {
                                    System.out.println("Order was not edited");
                                }
                            }
                            break;

                            case CANCEL: {
                                this.removeOrder(message.getAttempt());
                            }
                            break;

                            case ORDER: {
                                try {
                                    this.acceptOrder(message.getAttempt());
                                } catch (InterruptedException ex) {
                                    System.out.println("Interrupted Exception " + super.getId());
                                }
                            }
                        }
                        this.Notify();
                    }
                }
            }
        }
    }

    @Override
    public void start() {
        System.out.println(this.getStockName() + " Thread has started");

        if (!started) {
            started = true;
            super.start();
        }

        if (paused) {
            notify();
            paused = false;
        }
    }
}
