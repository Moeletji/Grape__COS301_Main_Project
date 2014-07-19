
package financialmarketsimulator.strategies;

import static financialmarketsimulator.strategies.Crossover.HigherAverage.ema;
import static financialmarketsimulator.strategies.Crossover.HigherAverage.price;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;


/**
 *
 * @brief Price-EMA Crossover Strategy 
 */
public class PriceEmaCrossover extends Crossover{
    
    //indicator1 = Price
    //indicator2 = EMA
    
    @SuppressWarnings("Convert2Diamond")
    public PriceEmaCrossover(int _numDays) {
       super(_numDays, "Price", "EMA");
       
        //TODO : Populate with objects housing closing Price and EMA values over the
        //specidies previous numDays days.
        this.closingAverages = new Vector<Crossover.DayClosingAverages>();
    }

    @Override
    @SuppressWarnings("Convert2Diamond")
    public void determineCrossoverPoints() {
        
        //Determine which of the Price or EMA had a higher starting value.
        
        //Consider the consequent values until non equal values are observed
        //Using while loop for performance sake where Price and EMA values are equivalent
        int j = 0; //Start from the first Price and EMA values
        while (j < numDays) {
            if (closingAverages.get(j).getIndicator1() > closingAverages.get(j).getIndicator2()) {
                currentHigh = price;
                break;
            } else if (closingAverages.get(j).getIndicator1() < closingAverages.get(j).getIndicator2()) {
                currentHigh = ema;
                break;
            }
            //If the Price and EMA values where equivalent continue with search until non-equivalent values observed.
            j++;
        }

        //If all past Price and EMA values are equal over the specified time, then 
        //set crossoverPoints to null and quit from the function. Else continue.
        if (currentHigh == null) {
            crossoverPoints = null;
            return;
        }

        crossoverPoints = new LinkedHashMap<Date, Crossover.CrossoverDetails>();
        Crossover.CrossoverDetails cod;

        for (int i = 1; i < numDays; i++) {
            //Iterate through all past Price and EMA values over the past numDays
            //days and record any crossover dates.

            //Case that Price had the higher previous value.
            if (currentHigh.equals(price)) {
                //If a crossover occured
                if (closingAverages.get(i).getIndicator1() < closingAverages.get(i).getIndicator2()) {
                    currentHigh = ema;

                    //Record crossover date and values
                    cod = new Crossover.CrossoverDetails((closingAverages.get(i - 1).getIndicator2() - closingAverages.get(i - 1).getIndicator1()),
                            (closingAverages.get(i).getIndicator2() - closingAverages.get(i).getIndicator1()),
                            currentHigh);

                    crossoverPoints.put(closingAverages.get(i).getDate(), cod);
                }
            } else //Case that EMA had the higher previous value.
            if (currentHigh.equals(ema)) {
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
       else if(currentHigh == ema)
           return "EMA";
       else
           return null;
    }
}
