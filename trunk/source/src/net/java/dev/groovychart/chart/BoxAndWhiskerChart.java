/*
 * BoxAndWhiskerChart.java
 *
 * Created on November 11, 2006, 9:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;

/**
 *
 * @author jclarke
 */
public class BoxAndWhiskerChart extends BaseChart {
    
    /** Creates a new instance of BoxAndWhiskerChart */
    public BoxAndWhiskerChart() {
    }

    public JFreeChart getChart() {
        return ChartFactory.createBoxAndWhiskerChart(getTitle(),
                getTimeAxisLabel(), getValueAxisLabel(), 
                (BoxAndWhiskerXYDataset)getDataset(),  isLegend());
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

    /**
     * Holds value of property legend.
     */
    private boolean legend;

    /**
     * Getter for property legend.
     * @return Value of property legend.
     */
    public boolean isLegend() {
        return this.legend;
    }

    /**
     * Setter for property legend.
     * @param legend New value of property legend.
     */
    public void setLegend(boolean legend) {
        this.legend = legend;
    }
    
}
