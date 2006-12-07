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
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author jclarke
 */
public class XYLineChart extends XYChart {
    
    /**
     * Creates a new instance of AreaChart
     */
    public XYLineChart() {
        super();
    }

    public JFreeChart getChart() {
        return ChartFactory.createXYLineChart(this.getTitle(),
                this.getXAxisLabel(), this.getYAxisLabel(),
                (XYDataset)this.getDataset(), this.getOrientation(),
                this.isLegend(), this.isTooltips(), this.isUrls());
    }
    
}
