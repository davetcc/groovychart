/*
 * AreaGraph.java
 *
 * Created on November 11, 2006, 9:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author jclarke
 */
public class BarChart3D extends CategoryValueChart {
    
    /** Creates a new instance of AreaGraph */
    public BarChart3D() {
        super();
    }

    public JFreeChart getChart() {
        return ChartFactory.createBarChart3D(getTitle(), getCategoryAxisLabel(),
                getValueAxisLabel(), (CategoryDataset)getDataset(),
                getOrientation(), isLegend(), isTooltips(), isUrls());
    }
    
}
