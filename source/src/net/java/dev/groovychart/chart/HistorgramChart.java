/*
 * AreaChart.java
 *
 * Created on November 11, 2006, 9:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.IntervalXYDataset;

/**
 *
 * @author jclarke
 */
public class HistorgramChart extends XYChart {
    
    /**
     * Creates a new instance of AreaChart
     */
    public HistorgramChart() {
        super();
    }

    public JFreeChart getChart() {
        return ChartFactory.createHistogram(getTitle(), getXAxisLabel(),
                getYAxisLabel(), (IntervalXYDataset)getDataset(),
                getOrientation(), isLegend(), isTooltips(), isUrls());
    }
    
}
