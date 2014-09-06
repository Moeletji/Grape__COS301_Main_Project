/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.market;

import financialmarketsimulator.exception.NotEnoughDataException;

/**
 *
 * @author Madimetja
 */
public abstract class MarketIndicator {
    
    protected final String name;
    
    public MarketIndicator(String _name)
    {
        this.name = _name;
    }
    
    public abstract Double calculateIndicator() throws NotEnoughDataException;
    
    public String getName()
    {
        return this.name;
    }
}
