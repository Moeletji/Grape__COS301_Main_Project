package com.grape.financialmarketsimulator_maven;


import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketIndicator;
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
import org.jfree.data.time.Millisecond;
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
public class MultiLineChart extends ApplicationFrame implements ActionListener{
    
    private final String chartTitle;
    //private final String item;
    private XYSeries series;
    private XYSeries ser1;
    private XYSeries ser2;
    private final double lastValue = 100.0;
    private final Timer timer = new Timer(250, this);
    private final double yAxisMin;
    private final double yAxisMax;
    //private final MarketIndicator indicator;
    //private final MarketIndicator indicator2;
    //private final MarketIndicator indicator3;
    private final Vector<MarketIndicator> indicators;
    private final Vector<String> indicatorNames;
    private final Vector<XYSeries> serieses;
    int i = 0;
    Date date;
    
    /**
     * @brief Class constructor
     * @param _indicators A vector containing the indicators or parameters to be graphed
     * @param _indicatorNames The names of the indicators in the _indicators vector in the order as they appear in the vector
     * @param _chartTitle The title of the chart
     * @param _yAxisMin The minimum Y-AXIS value for the graph 
     * @param _yAxisMax The maximum Y-AXIS value for the graph
     * @throws NotEnoughDataException 
     */
    public MultiLineChart(Vector<MarketIndicator> _indicators, Vector<String> _indicatorNames, String _chartTitle, double _yAxisMin, double _yAxisMax) throws NotEnoughDataException
    {
        super(_chartTitle);
        this.indicators = _indicators;
        this.indicatorNames = _indicatorNames;
        this.yAxisMin = _yAxisMin;
        this.yAxisMax = _yAxisMax;
        //this.indicator = _indicator;
        //this.indicator2 = _indicator2;
        //this.indicator3 = _indicator3;
        this.chartTitle = _chartTitle;
        //this.item = _item;
        date = new Date();
        
        this.serieses = new Vector<>();
        
        for(String ind : this.indicatorNames)
        {
            this.serieses.add(new XYSeries(ind));
        }
        
        
        
        /*this.series = new XYSeries(this.item + " Movement");
        //this.series = new TimeSeries("Random Data", Millisecond.class);
       this.ser1 = new XYSeries("SMA Movemetn");
       this.ser2 = new XYSeries("RSI Movement");*/
        
       final XYSeriesCollection dataset = new XYSeriesCollection();
       
       for(XYSeries ser : this.serieses)
       {
           dataset.addSeries(ser);
       }
       
        //final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
       /*dataset.addSeries(series);
       dataset.addSeries(ser1);
        dataset.addSeries(ser2);*/
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
            this.chartTitle, "Time","Price", dataset, true, true,false
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
    public void actionPerformed(final ActionEvent e) {
        double v1 = 0;
        i = i + 750;
        try {
            
            XYSeries ser;
            MarketIndicator ind;
            double value;
            final Millisecond now = new Millisecond();
            for( int k = 0; k < this.serieses.size(); k++ )
            {
                ser = this.serieses.get(k);
                ind = this.indicators.get(k);
                value = ind.calculateIndicator();
                ser.add(i,value);
                System.out.print("Current Time in Milliseconds = " + now.toString()+", Current " + this.indicatorNames.get(k) + " : "+value);
            }
            
            
        } catch (NotEnoughDataException ex) {
            ex.printStackTrace();
        }
        
    }
}
