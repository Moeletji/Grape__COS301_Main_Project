
package financialmarketsimulator.strategies;

import static financialmarketsimulator.strategies.Crossover.HigherAverage.price;
import static financialmarketsimulator.strategies.Crossover.HigherAverage.sma;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

/**
 *
 * @Brief Price-SMA Crossover Strategy
 */
public class PriceSmaCrossover extends Crossover{
    
    //indicator1 = Price
    //indicator2 = SMA
    
    @SuppressWarnings("Convert2Diamond")
    public PriceSmaCrossover(int _numDays) {
       super(_numDays, "Price", "SMA");
       
        //TODO : Populate with objects housing closing Price and SMA values over the
        //specidies previous numDays days.
        this.closingAverages = new Vector<Crossover.DayClosingAverages>();
    }

    @Override
    @SuppressWarnings("Convert2Diamond")
    public void determineCrossoverPoints() {
        
        //Determine which of the Price or SMA had a higher starting value.
        
        //Consider the consequent values until non equal values are observed
        //Using while loop for performance sake where Price and SMA values are equivalent
        int j = 0; //Start from the first Price and SMA values
        while (j < numDays) {
            if (closingAverages.get(j).getIndicator1() > closingAverages.get(j).getIndicator2()) {
                currentHigh = price;
                break;
            } else if (closingAverages.get(j).getIndicator1() < closingAverages.get(j).getIndicator2()) {
                currentHigh = sma;
                break;
            }
            //If the Price and SMA values where equivalent continue with search until non-equivalent values observed.
            j++;
        }

        //If all past Price and SMA values are equal over the specified time, then 
        //set crossoverPoints to null and quit from the function. Else continue.
        if (currentHigh == null) {
            crossoverPoints = null;
            return;
        }

        crossoverPoints = new LinkedHashMap<Date, Crossover.CrossoverDetails>();
        Crossover.CrossoverDetails cod;

        for (int i = 1; i < numDays; i++) {
            //Iterate through all past Price and SMA values over the past numDays
            //days and record any crossover dates.

            //Case that Price had the higher previous value.
            if (currentHigh.equals(price)) {
                //If a crossover occured
                if (closingAverages.get(i).getIndicator1() < closingAverages.get(i).getIndicator2()) {
                    currentHigh = sma;

                    //Record crossover date and values
                    cod = new Crossover.CrossoverDetails((closingAverages.get(i - 1).getIndicator2() - closingAverages.get(i - 1).getIndicator1()),
                            (closingAverages.get(i).getIndicator2() - closingAverages.get(i).getIndicator1()),
                            currentHigh);

                    crossoverPoints.put(closingAverages.get(i).getDate(), cod);
                }
            } else //Case that EMA had the higher previous value.
            if (currentHigh.equals(sma)) {
                //If a crossover occured
                if (closingAverages.get(i).getIndicator1() < closingAverages.get(i).getIndicator2()) {
                    currentHigh = price;

                    //Record crossover date and values
                    cod = new Crossover.CrossoverDetails((closingAverages.get(i - 1).getIndicator1() - closingAverages.get(i - 1).getIndicator2()),
                            (closingAverages.get(i).getIndicator1() - closingAverages.get(i).getIndicator2()),
                            currentHigh);

                    crossoverPoints.put(closingAverages.get(i).getDate(), cod);
                }
            }
        }
        //At this point all the cross over points are recorded in crossoverPoints LinkedHashMap
    }

    @Override
    public String getCurrentHight() {
       if(currentHigh == price)
           return "Price";
       else if(currentHigh == sma)
           return "SMA";
       else
           return null;
    }
}
