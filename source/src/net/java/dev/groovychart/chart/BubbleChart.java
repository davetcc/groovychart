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
import org.jfree.data.xy.XYZDataset;

/**
 *
 * @author jclarke
 */
public class BubbleChart extends XYChart {
    
    /**
     * Creates a new instance of AreaChart
     */
    public BubbleChart() {
        super();
    }

    public JFreeChart getChart() {
        return ChartFactory.createBubbleChart(getTitle(), getXAxisLabel(),
                getYAxisLabel(), (XYZDataset)getDataset(),
                getOrientation(), isLegend(), isTooltips(), isUrls());
    }
    
}
