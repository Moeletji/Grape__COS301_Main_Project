package financialmarketsimulator.market;

import financialmarketsimulator.database.DBConnect;
import financialmarketsimulator.exception.OrderHasNoValuesException;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import java.util.Vector;

/**
 * @Brief Order book housing all bids and offers made
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class MarketEntryAttemptBook {

    /**
     * @brief list of offers
     */
    Vector<MarketEntryAttempt> offers;
    /**
     * @brief list of bids
     */
    Vector<MarketEntryAttempt> bids;
    /**
     * @brief list of all trades that occurred within the stock
     */
    Vector<MatchedMarketEntryAttempt> matchedOrders;
    /**
     * @brief Vector of all gain values from trade to trade
     */
    Vector<Double> gains;
    /**
     * @bried Vector of all loss values from trade to trade
     */
    Vector<Double> losses;
    /**
     * @brief Name of the stock stored as a string
     */
    private String stockName;
    /**
     * @brief Database connection object through which all database
     * communication occurs
     */
    private DBConnect db;
    /**
     * @brief the time to wait before synchronizing with database
     */
    private long timePeriod;
    /**
     * @brief previous time to synchronize with db
     */
    private long pastTime;
    /**
     * @brief last trade price
     */
    private double lastTradePrice;
    /**
     * @brief The immediate previous trade price
     */
    private double previousTradePrice;
    /**
     * @brief Total number of shares the stock holds
     */
    private int totalNumberOfShares;
    /**
     * @brief Current number of shares the stock holds
     */
    private int currentNumberOfShares;
    /**
     * @brief MarketExchange
     */
    private MarketExchange exchange;
    
    /**
     * @brief Used for garbage collection
     */
    private Vector<MatchedMarketEntryAttempt> tempMatchedOrders;
    /**
     * @brief Used for garbage collection
     */
    private Vector<Double> tempGains;
    /**
     * @brief Used for garbage collection
     */
    private Vector<Double> tempLosees;
    
    public MarketEntryAttemptBook(String _stockName, long _timePeriod, int _totalNumberOfShares) {
        this();
        this.timePeriod = _timePeriod;
        this.totalNumberOfShares = _totalNumberOfShares;
        this.currentNumberOfShares = this.totalNumberOfShares;
        this.stockName = _stockName;
        this.lastTradePrice = 0.0;
    }

    /**
     * @brief constructor for MarketEntryAttemptBook
     * @param _stockName
     */
    public MarketEntryAttemptBook(String _stockName) {
        this();
        this.stockName = _stockName;
        this.lastTradePrice = 0.0;
    }

    /**
     * @brief constructor for MarketEntryAttemptBook
     * @param timePeriod time before synchronizing with db
     * @param stockName
     */
    public MarketEntryAttemptBook(long timePeriod, String stockName) {
        this.stockName = stockName;
        this.totalNumberOfShares = 10000;
        this.currentNumberOfShares = this.totalNumberOfShares;
        this.bids = new Vector<>();
        this.offers = new Vector<>();
        this.matchedOrders = new Vector<>();
        this.timePeriod = timePeriod;
        this.pastTime = System.currentTimeMillis();
        this.gains = new Vector<>();
        this.losses = new Vector<>();
        this.tempGains = new Vector<>();
        this.tempLosees = new Vector<>();
        this.tempMatchedOrders = new Vector<>();
    }

    /**
     * @brief constructor for MarketEntryAttemptBook
     * @param timePeriod time before synchronizing with db
     */
    public MarketEntryAttemptBook(long timePeriod) {
        this.stockName = "";
        this.totalNumberOfShares = 10000;
        this.currentNumberOfShares = this.totalNumberOfShares;
        this.bids = new Vector<>();
        this.offers = new Vector<>();
        this.matchedOrders = new Vector<>();
        this.timePeriod = timePeriod;
        this.pastTime = System.currentTimeMillis();
        this.gains = new Vector<>();
        this.losses = new Vector<>();
    }

    /**
     * @brief constructor for MarketEntryAttemptBook
     */
    public MarketEntryAttemptBook() {
        this.stockName = "";
        this.totalNumberOfShares = 0;
        this.bids = new Vector<>();
        this.offers = new Vector<>();
        this.matchedOrders = new Vector<>();
        this.timePeriod = 10;
        this.pastTime = System.currentTimeMillis();
        this.gains = new Vector<>();
        this.losses = new Vector<>();
        this.exchange = MarketExchange.getInstance("JSE");
    }

    /**
     * @brief Returns a list of all bids
     * @return list of bids
     */
    public Vector<MarketEntryAttempt> getBids() {
        return bids;
    }

    /**
     * @brief Returns a list of all offers
     * @return list of offers
     */
    public Vector<MarketEntryAttempt> getOffers() {
        return offers;
    }

    /**
     * @brief Gets the name of the stock
     * @return name of the stock
     */
    public String getStockName() {
        return this.stockName;
    }

    /**
     * @brief Function to look for trades and if there aren't any trades to be
     * made the order is added to the relevant order(bid or order) list.
     * @param newOrder
     */
    public synchronized void placeOrder(MarketEntryAttempt newOrder) {
        if (newOrder.getPrice() <= 0 || newOrder.getNumOfShares() <= 0) {
            return;//throw exception
        }

        //No shares remaining to trade with
        if (currentNumberOfShares <= 0) {
            return;//throw exception
        }

        MarketEntryAttempt.SIDE orderSide = newOrder.getSide();
        //the list to check if any matchedOrders can be made
        Vector<MarketEntryAttempt> listToCheck = (orderSide == MarketEntryAttempt.SIDE.BID) ? offers : bids;

        boolean hasMoreShares = true;

        //check if the other list still has orders and the order to be placed 
        //still has more shares
        while (listToCheck.size() > 0 && hasMoreShares) {
            MarketEntryAttempt topOrder = listToCheck.get(0);
            //if theres no match
            if ((orderSide == MarketEntryAttempt.SIDE.BID && newOrder.getPrice() < topOrder.getPrice())
                    || (orderSide == MarketEntryAttempt.SIDE.OFFER && newOrder.getPrice() > topOrder.getPrice())) {
                break;
            }//if there is a match 
            else if ((orderSide == MarketEntryAttempt.SIDE.BID && newOrder.getPrice() >= topOrder.getPrice())
                    || (orderSide == MarketEntryAttempt.SIDE.OFFER && newOrder.getPrice() <= topOrder.getPrice())) {
                if (newOrder.getNumOfShares() == topOrder.getNumOfShares()) {
                    hasMoreShares = false;
                    removeOrder(topOrder);
                    MatchedMarketEntryAttempt newTrade = new MatchedMarketEntryAttempt(newOrder, topOrder);
                    //If there's not enough shares remaining to honour the Match
                    //Only Match with whatever shares are remaining.
                    //if ((currentNumberOfShares - newOrder.getNumOfShares()) < 0) {
                    //    newOrder.setNumOfShares(currentNumberOfShares);
                    //}

                    //currentNumberOfShares -= newOrder.getNumOfShares();
                    if (!matchedOrders.isEmpty()) {
                        double difference = newTrade.getPrice() - matchedOrders.lastElement().getPrice();
                        if (difference < 0) {
                            if (losses != null) {
                                losses.add(difference);
                            }
                        } else if (difference > 0) {
                            if (gains != null) {
                                gains.add(difference);
                            }
                        }
                    }
                    matchedOrders.add(newTrade);
                    this.lastTradePrice = newTrade.getPrice();
                    exchange.getManager(stockName).getParticipant(newTrade.getBid().getParticipantID()).upadateWorth(newTrade.getQuantity(), this.getLastTradePrice(), newTrade.getPrice(), MarketEntryAttempt.SIDE.BID);
                    exchange.getManager(stockName).getParticipant(newTrade.getOffer().getParticipantID()).upadateWorth(newTrade.getQuantity(), this.getLastTradePrice(), newTrade.getPrice(), MarketEntryAttempt.SIDE.OFFER);
                } else if (newOrder.getNumOfShares() > topOrder.getNumOfShares()) {
                    //If there's not enough shares remaining to honour the Match
                    //Only Match with whatever shares are remaining.
                    /*if ((currentNumberOfShares - newOrder.getNumOfShares()) < 0) {
                     newOrder.setNumOfShares(currentNumberOfShares);
                     }*/
                    MatchedMarketEntryAttempt newTrade = new MatchedMarketEntryAttempt(newOrder, topOrder);
                    //currentNumberOfShares -= newOrder.getNumOfShares();
                    if (!matchedOrders.isEmpty()) {
                        double difference = newTrade.getPrice() - matchedOrders.lastElement().getPrice();
                        if (difference < 0) {
                            if (losses != null) {
                                losses.add(difference);
                            }
                        } else if (difference > 0) {
                            if (gains != null) {
                                gains.add(difference);
                            }
                        }
                    }
                    matchedOrders.add(newTrade);
                    newOrder.setNumOfShares(newOrder.getNumOfShares() - topOrder.getNumOfShares());
                    removeOrder(topOrder);
                    this.lastTradePrice = newTrade.getPrice();
                    exchange.getManager(stockName).getParticipant(newTrade.getBid().getParticipantID()).upadateWorth(newTrade.getQuantity(), this.getLastTradePrice(), newTrade.getPrice(), MarketEntryAttempt.SIDE.BID);
                    exchange.getManager(stockName).getParticipant(newTrade.getOffer().getParticipantID()).upadateWorth(newTrade.getQuantity(), this.getLastTradePrice(), newTrade.getPrice(), MarketEntryAttempt.SIDE.OFFER);

                } else if (newOrder.getNumOfShares() < topOrder.getNumOfShares()) {
                    //If there's not enough shares remaining to honour the Match
                    //Only Match with whatever shares are remaining.
                    //if ((currentNumberOfShares - newOrder.getNumOfShares()) < 0) {
                    //    newOrder.setNumOfShares(currentNumberOfShares);
                    //}
                    MatchedMarketEntryAttempt newTrade = new MatchedMarketEntryAttempt(newOrder, topOrder);

                    //currentNumberOfShares -= newOrder.getNumOfShares();
                    if (!matchedOrders.isEmpty()) {
                        double difference = newTrade.getPrice() - matchedOrders.lastElement().getPrice();
                        if (difference < 0) {
                            if (losses != null) {
                                losses.add(difference);
                            }
                        } else if (difference > 0) {
                            if (gains != null) {
                                gains.add(difference);
                            }
                        }
                    }
                    matchedOrders.add(newTrade);
                    topOrder.setNumOfShares(topOrder.getNumOfShares() - newOrder.getNumOfShares());
                    hasMoreShares = false;
                    this.lastTradePrice = newTrade.getPrice();
                    exchange.getManager(stockName).getParticipant(newTrade.getBid().getParticipantID()).upadateWorth(newTrade.getQuantity(), this.getLastTradePrice(), newTrade.getPrice(), MarketEntryAttempt.SIDE.BID);
                    exchange.getManager(stockName).getParticipant(newTrade.getOffer().getParticipantID()).upadateWorth(newTrade.getQuantity(), this.getLastTradePrice(), newTrade.getPrice(), MarketEntryAttempt.SIDE.OFFER);

                }
            }
        }
        System.out.println("===================================================");
        System.out.println("Last Traded Price"+"("+this.getStockName()+")"+": "+this.getLastTradePrice());
        System.out.println("===================================================");
        //if there are still more shares then add the order to the list
        if (hasMoreShares) {
            addOrderToList(newOrder);
        }
        
        if( gains.size() > 500 )
        {
            tempGains.clear();
            for( int i=0; i<200;i++ )
            {
                tempGains.add(gains.elementAt(i));
            }
            
            gains.clear();
            gains = null;
            gains = (Vector<Double>) tempGains.clone();
        }
        
        if( losses.size() > 500 )
        {
            tempLosees.clear();
            for( int i=0; i<200;i++ )
            {
                tempLosees.add(losses.elementAt(i));
            }
            
            losses.clear();
            losses = null;
            losses = (Vector<Double>) tempLosees.clone();
        }
        
        if( matchedOrders.size() > 500 )
        {
            tempMatchedOrders.clear();
            for( int i=0; i<200;i++ )
            {
                tempMatchedOrders.add(matchedOrders.elementAt(i));
            }
            
            MatchedMarketEntryAttempt att;
            for(int i = 200; i < matchedOrders.size(); i++)
            {
                att = matchedOrders.elementAt(i);
                att = null;
            }
            
            matchedOrders.clear();
            matchedOrders = null;
            matchedOrders = (Vector<MatchedMarketEntryAttempt>) tempMatchedOrders.clone();
        }
        
        System.gc();
    }

    /**
     * @param side
     * @throws financialmarketsimulator.exception.OrderHasNoValuesException
     * @throws java.lang.CloneNotSupportedException
     * @brief Alter the price and/or share
     * @param orderID Id of the order
     * @param price price of the order
     * @param shares number of the order
     */
    public synchronized void alterOrder(String orderID, double price, int shares, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException {
        MarketEntryAttempt order = searchForOrder(orderID, side);

        if (price <= 0 || shares <= 0 || order == null)
        {
            return;
        }

        if (order.getNumOfShares() != shares) {
            order.setNumOfShares(shares);
        }

        if (price != order.getPrice()) {
            order.setPrice(price);

            MarketEntryAttempt newOrder = (MarketEntryAttempt) order.clone();
            removeOrder(order.getOrderID(), order.getSide());
            addOrderToList(newOrder);
        }
    }

    /**
     * @param side
     * @throws financialmarketsimulator.exception.OrderHasNoValuesException
     * @throws java.lang.CloneNotSupportedException
     * @brief Alter the price and/or shares of an Order
     * @param orderID Id of the order
     * @param shares number of the order
     */
    public synchronized void alterOrder(String orderID, int shares, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException {
        MarketEntryAttempt order = searchForOrder(orderID, side);

        if (shares <= 0 || order == null) //throw new OrderHasNoValuesException();
        {
            return;
        }

        if (order.getNumOfShares() != shares) {
            order.setNumOfShares(shares);
        }
    }

    /**
     * @param side
     * @throws financialmarketsimulator.exception.OrderHasNoValuesException
     * @throws java.lang.CloneNotSupportedException
     * @brief Alter the price and/or shares of an Order
     * @param orderID Id of the order
     * @param price price of the order
     */
    public synchronized void alterOrder(String orderID, double price, MarketEntryAttempt.SIDE side) throws OrderHasNoValuesException, CloneNotSupportedException {
        MarketEntryAttempt order = searchForOrder(orderID, side);

        if (price <= 0 || order == null) //throw new OrderHasNoValuesException();
        {
            return;
        }

        if (price != order.getPrice()) {
            order.setPrice(price);

            MarketEntryAttempt newOrder = (MarketEntryAttempt) order.clone();
            removeOrder(order.getOrderID(), order.getSide());
            addOrderToList(newOrder);
        }
    }

    /**
     * This method adds on order to a list of orders
     *
     * @param order
     */
    public synchronized void addOrderToList(MarketEntryAttempt order) {
        MarketEntryAttempt.SIDE side = order.getSide();
        Vector<MarketEntryAttempt> temp = (order.getSide() == MarketEntryAttempt.SIDE.BID) ? bids : offers;

        //enter the bid at right index
        temp.add(searchForOrder(temp, order, side), order);
    }

    /**
     *
     * @param list
     * @param element
     * @return An integer of the index where the order can be entered
     */
    private int searchForOrder(Vector<MarketEntryAttempt> list, MarketEntryAttempt element, MarketEntryAttempt.SIDE side) {
        //start index
        int start = 0;
        //end index
        int end = list.size();

        while (start < end) {
            int middle = (start + end) / 2;
            if (compareOrders(list.get(middle), element, side)) {
                start = middle + 1;
            } else {
                end = middle;
            }
        }

        return end;
    }

    private boolean compareOrders(MarketEntryAttempt order_1, MarketEntryAttempt order_2, MarketEntryAttempt.SIDE _side) {
        return (_side == MarketEntryAttempt.SIDE.BID)
                ? !(order_1.getPrice() < order_2.getPrice())
                : !(order_1.getPrice() > order_2.getPrice());
    }

    private MarketEntryAttempt searchForOrder(String orderId, MarketEntryAttempt.SIDE side) {
        Vector<MarketEntryAttempt> orders = (side == MarketEntryAttempt.SIDE.BID ? bids : offers);

        for (MarketEntryAttempt order : orders) {
            if (order.getOrderID().equals(orderId)) {
                //update listeners
                //set ids at a later stage
                return order;
            }
        }

        return null;
    }

    private MarketEntryAttempt searchForOrder(MarketEntryAttempt attempt) {
        Vector<MarketEntryAttempt> orders = (attempt.side == MarketEntryAttempt.SIDE.BID ? bids : offers);

        for (MarketEntryAttempt order : orders) {
            if (order.getOrderID().equals(attempt.getOrderID())) {
                return order;
            }
        }

        return null;
    }

    public synchronized MarketEntryAttempt removeOrder(String orderId, MarketEntryAttempt.SIDE _side) {
        Vector<MarketEntryAttempt> orders = (_side == MarketEntryAttempt.SIDE.BID ? bids : offers);

        for (int i = 0; i < orders.size(); i++) {
            MarketEntryAttempt order = orders.get(i);
            if (order.getOrderID().equals(orderId)) {
                //update listeners
                //set ids at a later stage
                return orders.remove(i);
            }
        }

        return null;
    }

    public synchronized MarketEntryAttempt removeOrder(MarketEntryAttempt orderToBeRemoved) {
        Vector<MarketEntryAttempt> orders = (orderToBeRemoved.side == MarketEntryAttempt.SIDE.BID ? bids : offers);

        for (int i = 0; i < orders.size(); i++) {
            MarketEntryAttempt order = orders.get(i);
            if (order.getOrderID().equals(orderToBeRemoved.getOrderID())) {
                //update listeners
                //set ids at a later stage
                return orders.remove(i);
            }
        }

        return null;
    }

    /**
     * @brief empty the order book
     */
    public void clearAllBidsAndOffers() {
        bids.clear();
        offers.clear();
    }

    public synchronized Vector<MatchedMarketEntryAttempt> getMatchedOrders() {
        return matchedOrders;
    }
    
    /**
     * @brief Returns the current highest trade price
     * @param period The period over which the trade price is calculated
     * @return Returns the current highest trade price
     */
    public synchronized double getHighestTradePrice(int period) {
        if (period <= 0 || matchedOrders.size() <= 0) {
            return 0.0;//an exception must be thrown here
        }
        int length = matchedOrders.size();
        int range = ((length - 2) - period < 0) ? (length - 2) - period : 0;
        double highest;

        if (matchedOrders.size() >= range) {
            highest = 0;
        } else {
            highest = matchedOrders.get(range).getPrice();
            for (int i = range; i < length; i++) {
                if (matchedOrders == null || matchedOrders.get(i) == null) {
                    break;
                }

                if (matchedOrders.get(i).getPrice() > highest) {
                    highest = matchedOrders.get(i).getPrice();
                }
            }
        }
        return highest;
    }

    /**
     * @brief Returns the current lowest trade price
     * @param period The period over which the trade price is calculated
     * @return Returns the current lowest trade price
     */
    public synchronized double getLowestTradePrice(int period) {
        if (period <= 0 || matchedOrders.size() <= 0) {
            return 0.0;//an exception must be thrown here
        }
        int length = matchedOrders.size();
        int range = ((length - 2) - period < 0) ? (length - 2) - period : 0;
        double lowest;
        if (matchedOrders.size() >= range) {
            lowest = 0;
        } else {
            lowest = matchedOrders.get(range).getPrice();
            for (int i = range; i < length; i++) {
                if (matchedOrders == null || matchedOrders.get(i) == null) {
                    break;
                }

                if (matchedOrders.get(i).getPrice() < lowest) {
                    lowest = matchedOrders.get(i).getPrice();
                }
            }
        }
        return lowest;
    }

    /**
     * @brief Returns the first trade price
     * @return Returns the first trade price
     */
    public synchronized double getFirstTradePrice() {
        return (!matchedOrders.isEmpty()) ? matchedOrders.firstElement().getPrice() : 0.0;
    }

    /**
     * @brief get the highest bid price
     * @return highest bid price
     */
    public synchronized double getHighestBidPrice() {
        if (!bids.isEmpty()) {
            return (bids.get(0).price > 0.0 ? bids.get(0).price : 0.0);
        }
        return 0.0;
    }

    /**
     * @brief get the lowest offer price
     * @return lowest offer price
     */
    public synchronized double getLowestOfferPrice() {
        if (!offers.isEmpty()) {
            return (offers.get(0).price > 0.0 ? offers.get(0).price : 0.0);
        }
        return 0.0;
    }

    /**
     * @brief get the total shares for the highest bid price
     * @return total shares
     */
    public synchronized int getTotalSharesForHighestBidPrice() {
        if (!bids.isEmpty()) {
            return (bids.get(0).numOfShares > 0.0 ? bids.get(0).numOfShares : 0);
        }
        return 0;
    }

    /**
     * @brief get the total shares for the lowest offer price
     * @return total shares
     */
    public synchronized int getTotalSharesForLowestOfferPrice() {
        if (!offers.isEmpty()) {
            return (offers.get(0).numOfShares > 0.0 ? offers.get(0).numOfShares : 0);
        }
        return 0;
    }

    /**
     * @brief count number of order at a bid price
     * @return number of orders
     */
    public synchronized int getBidOrders() {
        int count = 0;
        double price = bids.get(0).price;

        for (MarketEntryAttempt bid : bids) {
            if (bid.getPrice() == price) {
                count++;
            }
        }

        for (MarketEntryAttempt offer : offers) {
            if (offer.getPrice() == price) {
                count++;
            }
        }

        return count;
    }

    /**
     * @brief count number of order at a offer price
     * @return number of orders
     */
    public synchronized int getOfferOrders() {
        int count = 0;
        double price = offers.get(0).price;

        for (MarketEntryAttempt offer : offers) {
            if (offer.getPrice() == price) {
                count++;
            }
        }

        for (MarketEntryAttempt bid : bids) {
            if (bid.getPrice() == price) {
                count++;
            }
        }

        return count;
    }

    /**
     * @brief count number of order at a bid price
     * @param price price of a bid
     * @return number of orders
     */
    public synchronized int getBidOrders(double price) {
        int count = 0;

        for (MarketEntryAttempt bid : bids) {
            if (bid.getPrice() == price) {
                count++;
            }
        }

        for (MarketEntryAttempt offer : offers) {
            if (offer.getPrice() == price) {
                count++;
            }
        }

        return count;
    }

    /**
     * @brief count number of order at a offer price
     * @param price price of a offer
     * @return number of orders
     */
    public synchronized int getOfferOrders(double price) {
        int count = 0;

        for (MarketEntryAttempt offer : offers) {
            if (offer.getPrice() == price) {
                count++;
            }
        }

        for (MarketEntryAttempt bid : bids) {
            if (bid.getPrice() == price) {
                count++;
            }
        }

        return count;
    }

    /**
     * @todo calculate the volatility of the stock using standard deviation
     * @brief calculate the volatility of the stock
     * @return the volatility of the market
     */
    public synchronized double getVolatility() {
        return 0.0;
    }

    /**
     * @brief Returns a vector housing the gains from trade to trade
     * @return Double Vector housing gains
     */
    public synchronized Vector<Double> getGains() {
        return this.gains;
    }

    /**
     * @brief Return a vector housing the losses from trade to trade
     * @return Double Vector housing losses
     */
    public synchronized Vector<Double> getLosses() {
        return this.losses;
    }

    /**
     * @brief Assess if the bid and offer stacks are empty or not.
     * @return Returns true if both bid and offer stacks are empty and false if
     * either one of the bid or offer stacks is not empty.
     */
    public Boolean isEmpty() {
        return bids.isEmpty() == true && offers.isEmpty() == true;
    }

    /**
     * @brief Returns the last trade price
     * @return Returns the last trade price
     */
    public double getLastTradePrice() {
        return lastTradePrice;
    }

    /**
     * @brief Sets the last trade price
     * @param lastTradePrice The last trade price to set to
     */
    public void setLastTradePrice(double lastTradePrice) {
        this.previousTradePrice = this.lastTradePrice;
        this.lastTradePrice = lastTradePrice;
    }

    /**
     * @brief Returns the previous trade price
     * @return Returns the previous trade price
     */
    public double getPreviousTradePrice() {
        return this.previousTradePrice;
    }

    /**
     * @brief Returns the total number of shares for this stock
     * @return Returns the total number of shares for this stock
     */
    public int getTotalNumberOfShares() {
        return totalNumberOfShares;
    }

    /**
     * @brief Sets the total number of shares
     * @param totalNumberOfShares The total number of shares to be set to
     */
    public void setTotalNumberOfShares(int totalNumberOfShares) {
        this.totalNumberOfShares = totalNumberOfShares;
    }

    /**
     * @brief Returns the opening price of the stock
     * @return Returns the opening price of the stock
     */
    public double getOpeningPrice() {
        if (this.matchedOrders.isEmpty()) {
            //throw new NotEnoughDataException();
            return 0.0;
        }

        return this.matchedOrders.firstElement().getPrice();
    }

    /**
     * @brief Returns the current highest trade price over all recorded trades
     * @return Returns the current trade price over all the recorded trades
     */
    public double getHighestTradedPrice() {
        double high = Double.MIN_VALUE;

        Vector<MatchedMarketEntryAttempt> tmp = (Vector<MatchedMarketEntryAttempt>) matchedOrders.clone();

        for (MatchedMarketEntryAttempt tmp1 : tmp) {
            MatchedMarketEntryAttempt matched = (MatchedMarketEntryAttempt) tmp1;
            if (matched.getPrice() > high) {
                high = matched.getPrice();
            }
        }
        return (high == Double.MIN_VALUE) ? 0.0 : high;
    }

    /**
     * @brief Returns the lowest trade price over all the trades 
     * @return Returns the lowest trade price over all the trades
     */
    public double getLowestTradedPrice() {
        double low = Double.MAX_VALUE;

        Vector<MatchedMarketEntryAttempt> tmp = (Vector<MatchedMarketEntryAttempt>) matchedOrders.clone();

        for (MatchedMarketEntryAttempt tmp1 : tmp) {
            MatchedMarketEntryAttempt matched = (MatchedMarketEntryAttempt) tmp1;
            if (matched.getPrice() < low) {
                low = Math.abs(matched.getPrice());
            }
        }
        return (low == Double.MAX_VALUE) ? 0.0 : low;
    }
}
