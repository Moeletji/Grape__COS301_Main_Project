package financialmarketsimulator.receipts;

import financialmarketsimulator.Bid;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class BidReceipt implements Receipt{
    
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

    @Override
    public void issueReceipt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
