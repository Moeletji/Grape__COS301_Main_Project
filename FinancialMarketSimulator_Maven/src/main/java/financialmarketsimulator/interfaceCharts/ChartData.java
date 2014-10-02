/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.interfaceCharts;

import java.util.Vector;

/**
 *
 * @author Madimetja
 */
public class ChartData {
    private final Vector<Object> dataItems;
    private final Vector<String> itemNames;
    
    public ChartData()
    {
        this.dataItems = new Vector<>();
        this.itemNames = new Vector<>();
    }
    
    public boolean addDataItem(Object o, String name)
    {
        return dataItems.add(o) && itemNames.add(name);
    }
    
    public void removeDataItem(Object o)
    {
        int position = dataItems.indexOf(o);
        dataItems.remove(o);
        itemNames.removeElementAt(position);
    }
    
    public Vector<Object> getDataItems()
    {
        return this.dataItems;
    }
    
    public Vector<String> getItemNames()
    {
        return this.itemNames;
    }
}
