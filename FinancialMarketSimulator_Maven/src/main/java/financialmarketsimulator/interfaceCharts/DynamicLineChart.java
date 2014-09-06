/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.interfaceCharts;

import financialmarketsimulator.exception.NotEnoughDataException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author Madimetja
 */
public abstract class DynamicLineChart extends ApplicationFrame implements ActionListener{
    
    protected final String chartTitle;
    protected final String item;
    protected TimeSeries series;
    protected double lastValue = 100.0;
    protected Timer timer = new Timer(250, this);
    
    
    /**
     * @brief Abstract Dynamic Line Chart Constructor
     * @param _chartTitle
     * @param _item
     * @throws NotEnoughDataException 
     */
    public DynamicLineChart(String _chartTitle, String _item) throws NotEnoughDataException
    {
        super(_chartTitle);
        this.chartTitle = _chartTitle;
        this.item = _item;
        this.series = new TimeSeries(this.item + " Movement", Millisecond.class);
       
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);
        timer.setInitialDelay(1000);
       
        final JPanel content = new JPanel(new BorderLayout());
        final ChartPanel chartPanel = new ChartPanel(chart);
        content.add(chartPanel);
        chartPanel.setPreferredSize(new java.awt.Dimension(900, 500));
        setContentPane(content);
        timer.start();
    }
    
    private JFreeChart createChart(final XYDataset dataset) {
        
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            this.item + " Movement", "Time","Price", dataset, true, true,false
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
        yaxis.setRange(-200.0, 10.0);
        return result;
    }
    
    /**
     * @brief Method updating the chart values over time. Should be implemented in concrete classes
     * @param e 
     */
    @Override
    public abstract void actionPerformed(final ActionEvent e);
}
