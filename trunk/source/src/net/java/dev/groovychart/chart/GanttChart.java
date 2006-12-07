/*
 * GanttChart.java
 *
 * Created on November 11, 2006, 9:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.IntervalCategoryDataset;

/**
 *
 * @author jclarke
 */
public class GanttChart extends BasicChart {
    
    /** Creates a new instance of GanttChart */
    public GanttChart() {
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
     * Holds value of property dateAxisLabel.
     */
    private String dateAxisLabel;

    /**
     * Getter for property dateAxisLabel.
     * @return Value of property dateAxisLabel.
     */
    public String getDateAxisLabel() {
        return this.dateAxisLabel;
    }

    /**
     * Setter for property dateAxisLabel.
     * @param dateAxisLabel New value of property dateAxisLabel.
     */
    public void setDateAxisLabel(String dateAxisLabel) {
        this.dateAxisLabel = dateAxisLabel;
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

    public JFreeChart getChart() {
        
        return ChartFactory.createGanttChart(getTitle(),
                getCategoryAxisLabel(), getDateAxisLabel(),
                (IntervalCategoryDataset)getDataset(),
                isLegend(), isTooltips(), isUrls());
    }
    
}
