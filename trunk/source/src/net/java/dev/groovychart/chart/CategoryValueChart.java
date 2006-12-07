/*
 * CategoryValueChart.java
 *
 * Created on November 11, 2006, 9:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author jclarke
 */
public abstract class CategoryValueChart extends BasicChart {
    /** Creates a new instance of CategoryValueChart */
    protected CategoryValueChart() {
    }

    /**
     * Holds value of property categoryAxisLabel.
     */
    private String categoryAxisLabel;

    /**
     * Getter for property categoryAxisLabel.
     * @return Value of property categoryAxisLabel.
     */
    public String getCategoryAxisLabel() {
        return this.categoryAxisLabel;
    }

    /**
     * Setter for property categoryAxisLabel.
     * @param categoryAxisLabel New value of property categoryAxisLabel.
     */
    public void setCategoryAxisLabel(String categoryAxisLabel) {
        this.categoryAxisLabel = categoryAxisLabel;
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
