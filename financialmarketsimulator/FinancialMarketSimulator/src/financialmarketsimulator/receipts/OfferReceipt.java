package financialmarketsimulator.receipts;

import financialmarketsimulator.Offer;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class OfferReceipt implements Receipt {
    
    /* Receipt object of the offer  */
    Offer offerReceipt;
    
    /**
     * @brief Constructor of Receipt object
     * @param offer the offer that will have a receipt
     */
    public OfferReceipt(Offer offer)
    {
        super();
        this.offerReceipt = offer;
    }

    @Override
    public void issueReceipt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
