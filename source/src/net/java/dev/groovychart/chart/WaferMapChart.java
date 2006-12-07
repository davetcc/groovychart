/*
 * WaferMapChart.java
 *
 * Created on November 11, 2006, 10:29 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.WaferMapDataset;

/**
 *
 * @author jclarke
 */
public class WaferMapChart extends BasicChart {
    
    /**
     * Creates a new instance of WaferMapChart
     */
    public WaferMapChart() {
    }

    public JFreeChart getChart() {
        return ChartFactory.createWaferMapChart(this.getTitle(),
                (WaferMapDataset)this.getDataset(), this.getOrientation(),
                this.isLegend(), this.isTooltips(), this.isUrls());
    }

    /**
     * Holds value of property orientation.
     */
    private PlotOrientation orientation;

    /**
     * Getter for property orientation.
     * @return Value of property orientation.
     */
    public PlotOrientation getOrientation() {
        return this.orientation;
    }

    /**
     * Setter for property orientation.
     * @param orientation New value of property orientation.
     */
    public void setOrientation(PlotOrientation orientation) {
        this.orientation = orientation;
    }
    
}
