/*
 * MultiplePieChart.java
 *
 * Created on November 11, 2006, 10:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.util.TableOrder;

/**
 *
 * @author jclarke
 */
public class MultiplePieChart3D extends BasicChart {
    
    /** Creates a new instance of MultiplePieChart */
    public MultiplePieChart3D() {
    }

    /**
     * Holds value of property order.
     */
    private TableOrder order;

    /**
     * Getter for property order.
     * @return Value of property order.
     */
    public TableOrder getOrder() {
        return this.order;
    }

    /**
     * Setter for property order.
     * @param order New value of property order.
     */
    public void setOrder(TableOrder order) {
        this.order = order;
    }

    public JFreeChart getChart() {
        return ChartFactory.createMultiplePieChart(getTitle(),
                (CategoryDataset)getDataset(), getOrder(),
                isLegend(), isTooltips(), isUrls());
    }
    
}
