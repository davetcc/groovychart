/*
 * TimeseriesChart.java
 *
 * Created on November 11, 2006, 10:27 PM
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
public class TimeseriesChart extends BasicChart {
    
    /** Creates a new instance of TimeseriesChart */
    public TimeseriesChart() {
    }

    public JFreeChart getChart() {
        return ChartFactory.createTimeSeriesChart(this.getTitle(),
                this.getTimeAxisLabel(), this.getValueAxisLabel(),
                (XYDataset)this.getDataset(),this.isLegend(),
                this.isTooltips(), this.isUrls());
    }

    /**
     * Holds value of property timeAxisLabel.
     */
    private String timeAxisLabel;

    /**
     * Getter for property timeAxisLabel.
     * @return Value of property timeAxisLabel.
     */
    public String getTimeAxisLabel() {
        return this.timeAxisLabel;
    }

    /**
     * Setter for property timeAxisLabel.
     * @param timeAxisLabel New value of property timeAxisLabel.
     */
    public void setTimeAxisLabel(String timeAxisLabel) {
        this.timeAxisLabel = timeAxisLabel;
    }

    /**
     * Holds value of property valueAxisLabel.
     */
    private String valueAxisLabel;

    /**
     * Getter for property valueAxisLabel.
     * @return Value of property valueAxisLabel.
     */
    public String getValueAxisLabel() {
        return this.valueAxisLabel;
    }

    /**
     * Setter for property valueAxisLabel.
     * @param valueAxisLabel New value of property valueAxisLabel.
     */
    public void setValueAxisLabel(String valueAxisLabel) {
        this.valueAxisLabel = valueAxisLabel;
    }
    
}
