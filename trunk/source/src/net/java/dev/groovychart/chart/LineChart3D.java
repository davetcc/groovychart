/*
 * LineChart.java
 *
 * Created on November 11, 2006, 9:56 PM
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
public class LineChart3D extends CategoryValueChart {
    
    /** Creates a new instance of LineChart3D */
    public LineChart3D() {
        super();
    }
    
    public JFreeChart getChart() {
        return ChartFactory.createLineChart3D(getTitle(),
                this.getCategoryAxisLabel(),this.getValueAxisLabel(),
                (CategoryDataset)getDataset(), getOrientation(),
                isLegend(), isTooltips(), isUrls());
    }
    
}
