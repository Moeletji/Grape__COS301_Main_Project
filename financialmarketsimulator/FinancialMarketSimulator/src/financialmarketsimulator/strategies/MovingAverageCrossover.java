package financialmarketsimulator.strategies;

import static financialmarketsimulator.strategies.Crossover.HigherAverage.ema;
import static financialmarketsimulator.strategies.Crossover.HigherAverage.sma;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;

/**
 *
 * @brief Moving Average Crossover strategy
 */
public class MovingAverageCrossover extends Crossover{

    //indicator1 = EMA
    //indicator2 = SMA
    
    @SuppressWarnings("Convert2Diamond")
    public MovingAverageCrossover(int _numDays) {
       super(_numDays, "EMA", "SMA");
       
        //TODO : Populate with objects housing closing EMA and SMA values over the
        //specidies previous numDays days.
        this.closingAverages = new Vector<DayClosingAverages>();
    }

    @Override
    @SuppressWarnings("Convert2Diamond")
    public void determineCrossoverPoints() {
        
        //Determine which of the SMA or EMA had a higher starting value.
        
        //Consider the consequent values until non equal values are observed
        //Using while loop for performance sake where EMA and SMA values are equivalent
        int j = 0; //Start from the first SMA and EMA values
        while (j < numDays) {
            if (closingAverages.get(j).getIndicator1() > closingAverages.get(j).getIndicator2()) {
                currentHigh = ema;
                break;
            } else if (closingAverages.get(j).getIndicator1() < closingAverages.get(j).getIndicator2()) {
                currentHigh = sma;
                break;
            }
            //If the EMA and SMA values where equivalent continue with search until non-equivalent values observed.
            j++;
        }

        //If all past SMA and EMA values are equal over the specified time, then 
        //set crossoverPoints to null and quit from the function. Else continue.
        if (currentHigh == null) {
            crossoverPoints = null;
            return;
        }

        crossoverPoints = new LinkedHashMap<Date, CrossoverDetails>();
        CrossoverDetails cod;

        for (int i = 1; i < numDays; i++) {
            //Iterate through all past SMA and EMA values over the past numDays
            //days and record any crossover dates.

            //Case that SMA had the higher previous value.
            if (currentHigh.equals(sma)) {
                //If a crossover occured
                if (closingAverages.get(i).getIndicator1() < closingAverages.get(i).getIndicator2()) {
                    currentHigh = ema;

                    //Record crossover date and values
                    cod = new CrossoverDetails((closingAverages.get(i - 1).getIndicator2() - closingAverages.get(i - 1).getIndicator1()),
                            (closingAverages.get(i).getIndicator2() - closingAverages.get(i).getIndicator1()),
                            currentHigh);

                    crossoverPoints.put(closingAverages.get(i).getDate(), cod);
                }
            } else //Case that EMA had the higher previous value.
            if (currentHigh.equals(ema)) {
                //If a crossover occured
                if (closingAverages.get(i).getIndicator1() < closingAverages.get(i).getIndicator2()) {
                    currentHigh = sma;

                    //Record crossover date and values
                    cod = new CrossoverDetails((closingAverages.get(i - 1).getIndicator1() - closingAverages.get(i - 1).getIndicator2()),
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
       if(currentHigh == sma)
           return "SMA";
       else if(currentHigh == ema)
           return "EMA";
       else
           return null;
    }
}
