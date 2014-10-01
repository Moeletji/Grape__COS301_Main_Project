package financialmarketsimulator.interfaceCharts;

import financialmarketsimulator.exception.NotEnoughDataException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors.
 */
/**
 *
 * @author Madimetja
 */
public abstract class MultiLineChart extends ApplicationFrame implements ActionListener {

    protected final String chartTitle;
    //private final String item;
    protected XYSeries series;
    protected XYSeries ser1;
    protected XYSeries ser2;
    protected final double lastValue = 100.0;
    protected final Timer timer = new Timer(250, this);
    protected final double yAxisMin;
    protected final double yAxisMax;

    final Vector<XYSeries> serieses;
    int i = 0;
    Date date;

    /**
     * @brief Class constructor
     * @param _chartTitle The title of the chart
     * @param _yAxisMin The minimum Y-AXIS value for the graph
     * @param _yAxisMax The maximum Y-AXIS value for the graph
     * @throws NotEnoughDataException
     */
    public MultiLineChart(String _chartTitle, double _yAxisMin, double _yAxisMax) throws NotEnoughDataException {
        super(_chartTitle);
        this.yAxisMin = _yAxisMin;
        this.yAxisMax = _yAxisMax;
        this.chartTitle = _chartTitle;
        date = new Date();

        this.serieses = new Vector<>();

        final XYSeriesCollection dataset = new XYSeriesCollection();

        for (XYSeries ser : this.serieses) {
            dataset.addSeries(ser);
        }

        final JFreeChart chart = createChart(dataset);
        timer.setInitialDelay(1000);

        //Created JPanel to show graph on screen
        final JPanel content = new JPanel(new BorderLayout());
        final ChartPanel chartPanel = new ChartPanel(chart);
        content.add(chartPanel);
        chartPanel.setPreferredSize(new java.awt.Dimension(900, 500));
        setContentPane(content);
        timer.start();
    }

    private JFreeChart createChart(final XYDataset dataset) {

        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                this.chartTitle, "Time", "Price", dataset, true, true, false
        );

        final XYPlot plot = result.getXYPlot();

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);

        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);

        //Domain axis would show data of 60 seconds for a time
        xaxis.setFixedAutoRange(40000.0);  // 60 seconds
        xaxis.setVerticalTickLabels(true);
        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setRange(this.yAxisMin, this.yAxisMax);
        return result;
    }

    @Override
    public abstract void actionPerformed(final ActionEvent e);
}
