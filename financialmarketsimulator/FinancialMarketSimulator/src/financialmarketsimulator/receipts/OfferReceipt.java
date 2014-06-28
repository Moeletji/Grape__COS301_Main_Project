package financialmarketsimulator.receipts;

import financialmarketsimulator.Offer;

/**
 *
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class OfferReceipt extends Receipt {
    
    /* Receipt object of the offer  */
    Offer offerReceipt;
    
    /**
     * @brief Constructor of Receipt object
     * @param offer the accepted offer
     */
    public OfferReceipt(Offer offer)
    {
        super();
        this.offerReceipt = offer;
    }
    
}
