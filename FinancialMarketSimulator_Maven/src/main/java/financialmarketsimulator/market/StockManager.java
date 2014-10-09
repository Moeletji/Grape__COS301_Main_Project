package financialmarketsimulator.market;

import financialmarketsimulator.exception.OrderHasNoValuesException;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import financialmarketsimulator.marketData.MatchedMarketEntryAttemptUpdate;
import financialmarketsimulator.marketData.Message;
import static financialmarketsimulator.marketData.Message.MessageType.AMEND;
import static financialmarketsimulator.marketData.Message.MessageType.CANCEL;
import static financialmarketsimulator.marketData.Message.MessageType.ORDER;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Exchanger;

/**
 * @brief The StockManager class is the super class from which each concrete
 * stock/security manager will inherit.
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class StockManager extends Thread {

    /**
     * @brief Name of the stock
     */
    private String stockName;
    /**
     * @brief an order book all orders accepted
     */
    private final MarketEntryAttemptBook orderList;
    /**
     * @brief Snapshot of the MarketData
     */
    private MatchedMarketEntryAttemptUpdate marketSnapShot;
    /**
     * @brief List of all MarketParticipants
     */
    private ArrayList<MarketParticipant> participants;
    /**
     * @brief Holds A list of all messages sent by MarketParticipants
     */
    private ConcurrentLinkedQueue<Message> messageQueue;
    //Thread started
    private boolean started;
    //Thread paused
    private boolean paused;
    //Thread stopeed
    private boolean stop;

    /**
     * @param stockName
     * @param timePeriod
     * @param totalNumberOfShares
     * @brief MarketManager Constructor
     */
    public StockManager(String stockName, long timePeriod, int totalNumberOfShares) {
        this.orderList = new MarketEntryAttemptBook(stockName, timePeriod, totalNumberOfShares);
        this.stockName = stockName;
        this.participants = new ArrayList<MarketParticipant>();
        this.messageQueue = new ConcurrentLinkedQueue<>();
        this.stop = false;
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
        this.stop = false;
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
    public synchronized void acceptOrder(MarketEntryAttempt order) throws InterruptedException {
        if (order.getPrice() <= 0 || order.getNumOfShares() <= 0) {
            return;
        }
        orderList.placeOrder(order);
    }

    /**
     * @brief get a list of all orders
     * @return a list of orders
     */
    public synchronized MarketEntryAttemptBook getOrderList() {
        return orderList;
    }

    /**
     * @brief Acknowledgement of the bid being removed by the market manager
     *
     * @param order MarketEntryAttempt to be removed
     * @return MarketEntryAttempt to be removed
     */
    public synchronized MarketEntryAttempt removeOrder(MarketEntryAttempt order) {
        return orderList.removeOrder(order);
    }

    /**
     * @brief Acknowledgement of the bid being removed by the market manager
     * @param orderId id of order
     * @param orderSide side of order
     * @return MarketEntryAttempt to be removed
     * @brief Acknowledgement of the bid being removed by the market manager
     */
    public synchronized MarketEntryAttempt removeOrder(String orderId, MarketEntryAttempt.SIDE orderSide) {
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

    public void sendMessage(Message message) {
        this.messageQueue.add(message);
    }

    /**
     * @brief StockManager thread that receives Messages and updates the market
     * according to those messages
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (paused) {
                    wait();
                }

            } catch (InterruptedException exception) {
                System.out.println("Interrupted Exception " + super.getId());
            }

            if (stop) {
                return;
            }

            while (messageQueue.isEmpty()) {
            }

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
                
                MarketExchange exchange = MarketExchange.getInstance("JSE");
                //exchange.print(stockName);
            }
        }
    }

    @Override
    public void start() {
        System.out.println("StockManager : " + this.getStockName() + " Thread has started");

        if (!started) {
            started = true;

            //Start the stock market
            super.start();

            //Randomize the Market Participants
            //Collections.shuffle(participants);

            //Begin to start trading with participants
            for (MarketParticipant participant : participants) {
                participant.start();
            }
        }

        if (paused) {
            synchronized (this) {
                notifyAll();
                paused = false;
            }
        }
    }

    /**
     * Notify all MarketParticipants randomly that the Stock has been updated.
     */
    public synchronized void Notify() {
        //Collections.shuffle(participants);
        for (MarketParticipant participant : participants) {
            participant.update(this);
        }
        super.notifyAll();
    }

    /**
     * @brief see if a specific MarketParticipant is trading in a stock
     * @param marketParticipantID ID of MarketParticipant
     * @return true if MarketParticipant is trading and false if it's not
     */
    boolean participantIsTrading(String marketParticipantID) {
        for (MarketParticipant participant : participants) {
            if ((participant != null) && (participant.getParticipantID().equals(marketParticipantID))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @brief get a MarketParticipant that is trading in a stock
     * @param marketParticipantID ID of MarketParticipant
     * @return MarketParticipant if it exists else null
     */
    public MarketParticipant getParticipant(String marketParticipantID) {
        for (MarketParticipant participant : participants) {
            if ((participant != null) && (participant.getParticipantID().equals(marketParticipantID))) {
                return participant;
            }
        }
        return null;
    }

    /**
     * @brief Get all Market Participants
     * @return All Participants
     */
    public ArrayList<MarketParticipant> getAllParticipantsForStock() {
        return participants;
    }

    /**
     * @brief Pause the Market
     */
    public synchronized void pause() {
        this.paused = true;
        for (MarketParticipant part : participants) {
            part.pause();
        }
    }

    /**
     * @brief Stop trading
     */
    synchronized public void terminateTrading() {
        cancel();
        for (MarketParticipant part : participants) {
            part.terminateTrading();
        }
        stop = true;
    }

    private void cancel() {
        interrupt();
    }

}
