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
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author jclarke
 */
public class PolarChart extends BasicChart {
    
    /** Creates a new instance of MultiplePieChart */
    public PolarChart() {
    }



    public JFreeChart getChart() {
        return ChartFactory.createPolarChart(getTitle(),
                (XYDataset)getDataset(), 
                isLegend(), isTooltips(), isUrls());
    }
    
}
