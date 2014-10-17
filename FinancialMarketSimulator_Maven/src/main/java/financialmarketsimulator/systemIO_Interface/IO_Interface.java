/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */
package financialmarketsimulator.systemIO_Interface;

import com.grape.financialmarketsimulator_maven.MultiLineChart;
import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.indicators.ADX;
import financialmarketsimulator.indicators.ATR;
import financialmarketsimulator.indicators.BollingerBands;
import financialmarketsimulator.indicators.DirectionalIndex;
import financialmarketsimulator.indicators.EMA;
import financialmarketsimulator.indicators.MACD;
import financialmarketsimulator.indicators.NDI;
import financialmarketsimulator.indicators.NDM;
import financialmarketsimulator.indicators.PDI;
import financialmarketsimulator.indicators.PDM;
import financialmarketsimulator.indicators.RSI;
import financialmarketsimulator.indicators.SMA;
import financialmarketsimulator.indicators.StochasticOscillator;
import financialmarketsimulator.indicators.Volatility;
import financialmarketsimulator.interfaceCharts.PriceMultiLineChart;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketIndicator;
import financialmarketsimulator.market.MarketParticipant;
import financialmarketsimulator.market.MarketStrategy;
import financialmarketsimulator.market.PhantomMarketParticipant;
import financialmarketsimulator.market.StockManager;
import financialmarketsimulator.strategies.DirectionalMovementIndex;
import financialmarketsimulator.strategies.MACDStrategy;
import financialmarketsimulator.strategies.MovingAverageCrossover;
import financialmarketsimulator.strategies.MovingAverageEnvelope;
import financialmarketsimulator.strategies.MovingAverageFilter;
import financialmarketsimulator.strategies.Phantom;
import financialmarketsimulator.strategies.PriceEmaCrossover;
import financialmarketsimulator.strategies.PriceSmaCrossover;
import financialmarketsimulator.strategies.SimpleRSI;
import financialmarketsimulator.strategies.Simple_MACD_ADX;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import org.jfree.ui.RefineryUtilities;
import simulatorInterface.MessageBox;
import simulatorInterface.Participants;

/**
 * @brief This class provides access to the system backend in a RESTful manner,
 * i.e. it is the equivalent of a web-services. All front end interfaces should
 * obtain information/data to display from this class.
 * @author Madimetja
 */
public class IO_Interface {

    private static IO_Interface instance = null;
    private MarketExchange exchange;
    private MultiLineChart chart = null;
    private PriceMultiLineChart pchart = null;

    ArrayList<MarketParticipant> participants = new ArrayList();

    private IO_Interface() {
        exchange = MarketExchange.getInstance("JSE");
        chart = null;
        pchart = null;

    }

    public static IO_Interface getInstance() {
        if (instance == null) {
            try {
                instance = new IO_Interface();
            } catch (Exception ex) {
                System.out.println("IO_Interface  Exception");
            }
        }
        return instance;
    }

    public StockManager getExchangeStockManager(String stockName) {
        return this.exchange.getManager(stockName);
    }

    public ArrayList<MarketParticipant> getAllParticipants() {
        return this.exchange.getAllParticipants();
    }

    public Map<String, StockManager> getAllStockManagers() {
        return this.exchange.getStocksManagers();
    }

    public MultiLineChart getIndicatorChart() {
        return this.chart;
    }

    public PriceMultiLineChart getPriceChart() {
        return this.pchart;
    }

    public void addParticipant(String strat, String stockName, String partName, String ID) {
        MarketStrategy strategy = null;

        switch (strat) {
            case "Phantom":
                strategy = new Phantom();
                break;
            case "DirectionalMovementIndex":
                strategy = DirectionalMovementIndex.getInstance(exchange.getBook(stockName), 14);
                break;
            case "MACDStrategy":
                try {
                    strategy = MACDStrategy.getInstance(exchange.getBook(stockName));
                } catch (Exception ex) {
                    MessageBox.infoBox("Insufficient Data to create MACD Strategy", "");
                }
                break;
            case "MovingAverageCrossover":
                try {
                    strategy = MovingAverageCrossover.getInstance(exchange.getBook(stockName), 14);
                } catch (Exception ex) {
                    MessageBox.infoBox("Insufficient Data to create MovingAverageCrossover Strategy", "");
                }
                break;
            case "MovingAverageEnvelope":
                try {
                    strategy = MovingAverageEnvelope.getInstance(exchange.getBook(stockName));
                } catch (Exception ex) {
                    MessageBox.infoBox("Insufficient Data to create Moving Average Envelope Strategy", "");
                }
                break;
            case "MovingAverageFilter":
                try {
                    strategy = MovingAverageFilter.getInstance(exchange.getBook(stockName));
                } catch (Exception ex) {
                    MessageBox.infoBox("Insufficient Data to create Moving Average Filter Strategy", "");
                }
                break;
            case "PriceEmaCrossover":
                try {
                    strategy = PriceEmaCrossover.getInstance(exchange.getBook(stockName), 14);
                } catch (Exception ex) {
                    MessageBox.infoBox("Insufficient Data to create Price EMA Crossover Strategy", "");
                }
                break;
            case "PriceSmaCrossover":
                strategy = PriceSmaCrossover.getInstance(exchange.getBook(stockName), 14);
                break;
            case "SimpleRSI":
                try {
                    strategy = SimpleRSI.getInstance(exchange.getBook(stockName));
                } catch (Exception ex) {
                    MessageBox.infoBox("Insufficient Data to create Simple RSI Strategy", "");
                }
                break;
            case "Simple_MACD_ADX":
                try {
                    strategy = Simple_MACD_ADX.getInstance(exchange.getBook(stockName));
                } catch (Exception ex) {
                    MessageBox.infoBox("Insufficient Data to create Simple MACD ADX Strategy", "");
                }
                break;
            default:
                MessageBox.infoBox("Strategy does not exist.", "Strategy not found");
                return;
        }
        MarketParticipant participant;

        try {
            participant = new MarketParticipant(partName, ID, exchange, stockName, strategy);
            participants.add(participant);
        } catch (NotEnoughDataException ex) {
            System.out.println("Creating participant threw not enough data exception");
        }
    }

    public void attatchPhantomMarketParticipant() {
        for (StockManager manager : exchange.getStocksManagers().values()) {
            if (manager.getAllParticipantsForStock().size() > 0) {
                try {
                    manager.attach(new PhantomMarketParticipant("Phantom", "Phantom", exchange, manager.getStockName()));

                } catch (NotEnoughDataException ex) {
                    Logger.getLogger(Participants.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Participants.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public boolean checkStockExistance(String stockName) {
        return exchange.stockFound(stockName);
    }

    public void addStockManager(String stockName) {
        this.exchange.addStockManager(new StockManager(stockName));
    }

    public void nullifyIndicatorChart() {
        this.chart = null;
    }

    public void nullyfyPriceChart() {
        this.pchart = null;
    }

    public void openExchangeMarket() {
        exchange.openMarket();
    }

    public void pauseExchange() {
        exchange.pause();
    }

    public void stopExchange() {
        exchange.stop();
    }

    public void clearExchangeStocks() {
        exchange.clearStocks();
    }

    public void clearExchangeStocks(String stockName) {
        MarketExchange.getInstance(stockName).clearStocks();
    }

    public void clearParticipants() {
        this.participants.clear();
    }

    public void nullifyExchange() {
        exchange = null;
    }

    public void initParticipants(StockManager manager) {
        try {
            MarketStrategy strategy1 = DirectionalMovementIndex.getInstance(exchange.getBook(manager.getStockName()), 14);
            MarketStrategy strategy2 = MovingAverageCrossover.getInstance(exchange.getBook(manager.getStockName()), 14);
            MarketStrategy strategy3 = MovingAverageEnvelope.getInstance(exchange.getBook(manager.getStockName()));
            MarketStrategy strategy4 = MovingAverageFilter.getInstance(exchange.getBook(manager.getStockName()));
            MarketStrategy strategy5 = PriceEmaCrossover.getInstance(exchange.getBook(manager.getStockName()), 14);
            MarketStrategy strategy6 = PriceSmaCrossover.getInstance(exchange.getBook(manager.getStockName()), 14);
            MarketStrategy strategy7 = SimpleRSI.getInstance(exchange.getBook(manager.getStockName()));
            MarketStrategy strategy8 = Simple_MACD_ADX.getInstance(exchange.getBook(manager.getStockName()));
            MarketStrategy strategy9 = MACDStrategy.getInstance(exchange.getBook(manager.getStockName()));

            MarketParticipant mp1 = new MarketParticipant("Participant 1 " + strategy1.getStrategyName(), "1", exchange, manager.getStockName(), strategy1);
            manager.attach(mp1);
            participants.add(mp1);

            MarketParticipant mp2 = new MarketParticipant("Participant 2 " + strategy2.getStrategyName(), "2", exchange, manager.getStockName(), strategy2);
            manager.attach(mp2);
            participants.add(mp2);

            MarketParticipant mp3 = new MarketParticipant("Participant 3 " + strategy3.getStrategyName(), "3", exchange, manager.getStockName(), strategy3);
            manager.attach(mp3);
            participants.add(mp3);

            MarketParticipant mp4 = new MarketParticipant("Participant 4 " + strategy4.getStrategyName(), "4", exchange, manager.getStockName(), strategy4);
            manager.attach(mp4);
            participants.add(mp4);

            MarketParticipant mp5 = new MarketParticipant("Participant 5 " + strategy5.getStrategyName(), "5", exchange, manager.getStockName(), strategy5);
            manager.attach(mp5);
            participants.add(mp5);

            MarketParticipant mp6 = new MarketParticipant("Participant 6 " + strategy6.getStrategyName(), "6", exchange, manager.getStockName(), strategy6);
            manager.attach(mp6);
            participants.add(mp6);

            MarketParticipant mp7 = new MarketParticipant("Participant 7 " + strategy7.getStrategyName(), "7", exchange, manager.getStockName(), strategy7);
            manager.attach(mp7);
            participants.add(mp7);

            MarketParticipant mp8 = new MarketParticipant("Participant 8 " + strategy8.getStrategyName(), "8", exchange, manager.getStockName(), strategy8);
            manager.attach(mp8);
            participants.add(mp8);

            MarketParticipant mp9 = new MarketParticipant("Participant 9 " + strategy9.getStrategyName(), "9", exchange, manager.getStockName(), strategy9);
            manager.attach(mp9);
            participants.add(mp9);

        } catch (NotEnoughDataException e) {
            System.err.println("Participant - Not enough data exception");
        } catch (Exception ee) {
        }
    }

    public ArrayList<MarketParticipant> getMarketParticipants() {
        return this.participants;
    }

    public void drawIndicatorGraph() {
        Vector<MarketIndicator> ind;
        ind = new Vector<>();

        ind.add(ADX.getInstance(exchange.getBook("INV"), 14));
        //ind.add(ATR.getInstance(exchange.getBook("INV"), 14));
        //ind.add(BollingerBands.getInstance(exchange.getBook("INV"), 14));
        ind.add(DirectionalIndex.getInstance(exchange.getBook("INV"), 14));
        ind.add(EMA.getInstance(exchange.getBook("INV"), 14));
        //ind.add(MACD.getInstance(exchange.getBook("INV")));
        //ind.add(NDI.getInstance(exchange.getBook("INV"), 14));
        //ind.add(NDM.getInstance(exchange.getBook("INV"), 14));
        //ind.add(PDI.getInstance(exchange.getBook("INV"), 14));
        //ind.add(PDM.getInstance(exchange.getBook("INV"), 14));
        //ind.add(RSI.getInstance(exchange.getBook("INV"), 14));
        ind.add(SMA.getInstance(exchange.getBook("INV"), 14));
        //ind.add(StochasticOscillator.getInstance(exchange.getBook("INV")));
        //ind.add(Volatility.getInstance(14, exchange.getBook("INV")));

        Vector<String> indNames = new Vector<>();

        indNames.add("ADX Movement");
        //indNames.add("ATR Movement");
        //indNames.add("Bollinger Movement");
        indNames.add("Directional Movement");
        indNames.add("EMA Movement");
        //indNames.add("MACD Movement");
        //indNames.add("NDI Movement");
        //indNames.add("NDM Movement");
        //indNames.add("PDI Movement");
        //indNames.add("PDM Movement");
        //indNames.add("RSI Movement");
        indNames.add("SMA Movement");
        //indNames.add("Stochastic Movement");
        //indNames.add("Volatitlity Movement");

        try {
            chart = new MultiLineChart(ind, indNames, "Indicators", -50, 100);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen(chart);
            chart.setVisible(true);
        } catch (Exception ex) {
            System.err.println("Indicator Graph Exception");
        }
    }

    public void drawPriceGraph() {

        Vector<StockManager> man = new Vector<StockManager>();
        for (StockManager stockmanager : exchange.getStocksManagers().values()) {
            man.add(stockmanager);
        }
        Vector<String> manNames = new Vector<>();
        for (StockManager stockmanager : exchange.getStocksManagers().values()) {
            manNames.add(stockmanager.getStockName());
        }

        if (pchart != null) {
            try {
                pchart = new PriceMultiLineChart(man, manNames, 0, 30);
                pchart.pack();
                RefineryUtilities.centerFrameOnScreen(pchart);
                pchart.setDefaultCloseOperation(HIDE_ON_CLOSE);
                pchart.setVisible(true);
            } catch (NotEnoughDataException ex) {
                System.out.println("Price graph exception");
            }
        } else {
            pchart.setVisible(true);
        }
    }
}
