
import com.grape.financialmarketsimulator_maven.MultiLineChart;
import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.*;
import financialmarketsimulator.indicators.MACD;
import financialmarketsimulator.indicators.RSI;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketIndicator;
import financialmarketsimulator.market.MarketParticipant;
import financialmarketsimulator.market.MarketStrategy;
import financialmarketsimulator.market.PhantomMarketParticipant;
import financialmarketsimulator.market.StockManager;
import financialmarketsimulator.strategies.Phantom;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.ui.RefineryUtilities;

/**
 * @brief Main project execution class
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */
public class FinancialMarketSimulator {

    public static void main(String[] args) throws NotEnoughDataException {

        MarketExchange exchange = MarketExchange.getInstance("JSE");

        String[] names = {"INV", "IPSA", "LBH"};

        for (int i = 0; i < names.length; i++) {
            exchange.addStockManager(new StockManager(names[i], 10, 1000 * (Math.abs(new Random().nextInt() % 10))));
        }

        //10 Strategies
        //Trading Strategies
        MarketStrategy strategy1 = new Phantom();
        MarketStrategy strategy2 = new Phantom();
        MarketStrategy strategy3 = new Phantom();
        MarketStrategy strategy4 = new Phantom();
        MarketStrategy strategy5 = new Phantom();
        MarketStrategy strategy6 = new Phantom();
        MarketStrategy strategy7 = new Phantom();
        MarketStrategy strategy8 = new Phantom();
        MarketStrategy strategy9 = new Phantom();
        MarketStrategy strategy10 = new Phantom();

        //10 entities
        //let's only trade investec stocks for now
        MarketParticipant entity1 = null;
        MarketParticipant entity2 = null;
        try {
            entity1 = new PhantomMarketParticipant("BGP Holdings", "BGPLTD", exchange, names[0]);
            entity2 = new PhantomMarketParticipant("Steinhoff", "STH", exchange, names[0]);
        } catch (IOException ex) {
            Logger.getLogger(FinancialMarketSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        MarketParticipant entity3 = new MarketParticipant("Boshoff", "BGPLTD", exchange, names[0], strategy3);
        MarketParticipant entity4 = new MarketParticipant("MiguelGroup", "MMG", exchange, names[0], strategy4);
        MarketParticipant entity5 = new MarketParticipant("SunBlue", "SBINC", exchange, names[0], strategy5);
        MarketParticipant entity7 = new MarketParticipant("JJK", "JJK", exchange, names[0], strategy6);
        MarketParticipant entity8 = new MarketParticipant("FacTue", "FTT", exchange, names[0], strategy7);
        MarketParticipant entity9 = new MarketParticipant("KellyKelly", "KKG", exchange, names[0], strategy8);
        MarketParticipant entity10 = new MarketParticipant("Unique Holdings", "UHINC", exchange, names[0], strategy9);
        MarketParticipant entity6 = new MarketParticipant("Rising Holdings", "RHSS", exchange, names[0], strategy10);

        StockManager manager = exchange.getStocksManagers().get("INV");
        
        if(manager == null){
            System.out.println("Manager not found");
            return;
        }

        manager.attach(entity1);
        manager.attach(entity2);
        manager.attach(entity3);
        manager.attach(entity4);
        manager.attach(entity5);
        manager.attach(entity6);
        manager.attach(entity7);
        manager.attach(entity8);
        manager.attach(entity9);
        manager.attach(entity10);

        manager.start();

        
        //Running the 2 phantoms
        entity1.start();
        entity2.start();
        
        //FOR CREATING THE GRAPHS
        
        //Creating multi line graphs
        Vector<MarketIndicator> ind = new Vector<>();
        
        ind.add(new ADX(exchange.getBook("INV"),14));
        ind.add(new ATR(exchange.getBook("INV"),14));
        //ind.add(new BollingerBands(exchange.getBook("INV")));
        ind.add(new DirectionalIndex(exchange.getBook("INV"),14));
        ind.add(new EMA(exchange.getBook("INV"),14));
        ind.add(new MACD(exchange.getBook("INV")));
        ind.add(new NDI(exchange.getBook("INV"), 14));
        ind.add(new NDM(exchange.getBook("INV"),14));
        ind.add(new PDI(exchange.getBook("INV"),14));
        ind.add(new PDM(exchange.getBook("INV"),14));
        ind.add(new RSI(exchange.getBook("INV"),14));
        ind.add(new SMA(exchange.getBook("INV"),14));
        ind.add(new StochasticOscillator(exchange.getBook("INV")));
        ind.add(new Volatility(14, exchange.getBook("INV")));
        
        
        
        Vector<String> indNames = new Vector<>();
        
        indNames.add("ADX Movement");
        indNames.add("ATR Movement");
        //indNames.add("Bollinger Movement");
        indNames.add("Directional Movement");
        indNames.add("EMA Movement");
        indNames.add("MACD Movement");
        indNames.add("NDI Movement");
        indNames.add("NDM Movement");
        indNames.add("PDI Movement");
        indNames.add("PDM Movement");
        indNames.add("RSI Movement");
        indNames.add("SMA Movement");
        indNames.add("Stochastic Movement");
        indNames.add("Volatitlity Movement");
        
        final MultiLineChart chart = new MultiLineChart(ind,indNames,"Indicators", -50, 100);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
        
        //Graph for EMA
        /*EMA emaObj = new EMA(exchange.getBook("INV"),14);
        final IndicatorChart ema = new IndicatorChart(emaObj,"Exponential Moving Average","EMA");
        ema.pack();
        RefineryUtilities.centerFrameOnScreen(ema);
        ema.setVisible(true);
        
        //Graph for SMA
        SMA smaObj = new SMA(exchange.getBook("INV"),14);
        final IndicatorChart sma = new IndicatorChart(smaObj,"Simple Moving Average","SMA");
        sma.pack();
        RefineryUtilities.centerFrameOnScreen(sma);
        sma.setVisible(true);
        
        //Graph for RSI
        RSI rsiObj = new RSI(exchange.getBook("INV"),14);
        final IndicatorChart rsi = new IndicatorChart(rsiObj,"Relative Strength Index","RSI");
        rsi.pack();
        RefineryUtilities.centerFrameOnScreen(rsi);
        rsi.setVisible(true);*/
        
    }
}
