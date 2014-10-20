/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */
package financialmarketsimulator.marketData;

import com.csvreader.CsvWriter;
import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttemptBook;
import financialmarketsimulator.market.MarketParticipant;
import financialmarketsimulator.strategies.MACDStrategy;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Moeletji
 */
public class WriteToCSV {

    /*private MarketParticipant participant;
    private String stockName;
    private String fileName;
    private Path path;
    private final String folderName = "ParticipantData";
    private File folder;
    private CsvWriter output; 
    private boolean exists;
    
    private static WriteToCSV instance = null;
    
    private WriteToCSV(MarketParticipant _participant, String _stockName) throws IOException
    {
        this.participant = _participant;
        this.stockName = _stockName;
        fileName = this.participant.getCurrentStrategy().getStrategyName()+".csv";
        path = Paths.get(folderName+"/"+fileName);
        Files.createDirectories(path.getParent());
        Files.createFile(path);
        folder = new File(folderName);
        folder.getParentFile().mkdir();
        exists = new File(folderName+"/"+fileName).exists();
        if (!exists)
        {
            output.write("participantId");
            output.write("participantName");
            output.write("currentStrategy");
            output.write("amountOfShares");
            output.write("currentAmount");
            output.write("numberOfStrategies");
            output.write("stockName");
        }
    }
    
    public static WriteToCSV getInstance(MarketParticipant _participant, String _stockName) throws IOException {
        if (instance == null) {
            instance = new WriteToCSV(_participant, _stockName);
        }
        return instance;
    }
    
    public void createFile(String name)
    {
        File[] listOfFiles = folder.listFiles();
        
        for (File file:listOfFiles)
        {
            if (file.getName().equals(fileName))
            {
                
            }
        }
    }
    
    */
}
