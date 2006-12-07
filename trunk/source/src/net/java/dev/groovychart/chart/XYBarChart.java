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
public class XYBarChart extends XYChart {
    
    /**
     * Creates a new instance of AreaChart
     */
    public XYBarChart() {
        super();
    }

    public JFreeChart getChart() {
        return ChartFactory.createXYBarChart(this.getTitle(),
                this.getXAxisLabel(), this.isDateAxis(), this.getYAxisLabel(),
                (IntervalXYDataset)this.getDataset(), this.getOrientation(),
                this.isLegend(), this.isTooltips(), this.isUrls());
    }

    /**
     * Holds value of property dateAxis.
     */
    private boolean dateAxis;

    /**
     * Getter for property dateAxis.
     * @return Value of property dateAxis.
     */
    public boolean isDateAxis() {
        return this.dateAxis;
    }

    /**
     * Setter for property dateAxis.
     * @param dateAxis New value of property dateAxis.
     */
    public void setDateAxis(boolean dateAxis) {
        this.dateAxis = dateAxis;
    }
    
}
