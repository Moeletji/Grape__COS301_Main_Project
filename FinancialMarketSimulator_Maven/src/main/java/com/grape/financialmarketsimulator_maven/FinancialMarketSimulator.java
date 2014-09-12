
import com.grape.financialmarketsimulator_maven.MultiLineChart;
import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.MACD;
import financialmarketsimulator.indicators.RSI;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.interfaceCharts.DynamicLineChart;
import financialmarketsimulator.interfaceCharts.IndicatorChart;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketIndicator;
import financialmarketsimulator.market.MarketParticipant;
import financialmarketsimulator.market.MarketStrategy;
import financialmarketsimulator.market.PhantomMarketParticipant;
import financialmarketsimulator.market.StockManager;
import financialmarketsimulator.strategies.Phantom;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
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
        MarketParticipant entity1 = new PhantomMarketParticipant("BGP Holdings", "BGPLTD", exchange, names[0]);
        MarketParticipant entity2 = new PhantomMarketParticipant("Steinhoff", "STH", exchange, names[0]);
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
        ind.add(new EMA(exchange.getBook("INV"),14));
        ind.add(new SMA(exchange.getBook("INV"),14));
        ind.add(new RSI(exchange.getBook("INV"),14));
        ind.add(new MACD(exchange.getBook("INV")));
        
        Vector<String> indNames = new Vector<>();
        
        indNames.add("EMA Movement");
        indNames.add("SMA Movement");
        indNames.add("RSI Movement");
        indNames.add("MACD Movement");
        
        final MultiLineChart chart = new MultiLineChart(ind,indNames,"Indicators");
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
