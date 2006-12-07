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
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author jclarke
 */
public class WaterfallChart extends CategoryValueChart{
    
    /**
     * Creates a new instance of AreaChart
     */
    public WaterfallChart() {
        super();
    }

    public JFreeChart getChart() {
        return ChartFactory.createWaterfallChart(this.getTitle(),
                this.getCategoryAxisLabel(), this.getValueAxisLabel(),
                (CategoryDataset)this.getDataset(),this.getOrientation(),
                this.isLegend(), this.isTooltips(), this.isUrls());
    }
    
}
