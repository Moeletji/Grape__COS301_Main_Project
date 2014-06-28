package financialmarketsimulator.receipts;

import financialmarketsimulator.Bid;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class BidReceipt extends Receipt{
    
    /* Receipt object of the bid  */
    private Bid receiptBid;
    
    /**
     * @brief Constructor of Receipt object
     * @param bid the bid that will a receipt
     */
    public BidReceipt(Bid bid)
    {
        super();
        receiptBid = bid;
    }
}
