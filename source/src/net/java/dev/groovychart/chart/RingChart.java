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
import org.jfree.data.general.PieDataset;

/**
 *
 * @author jclarke
 */
public class RingChart extends BasicChart {
    
    /** Creates a new instance of MultiplePieChart */
    public RingChart() {
    }



    public JFreeChart getChart() {
        return ChartFactory.createRingChart(getTitle(),
                (PieDataset)getDataset(), 
                isLegend(), isTooltips(), isUrls());
    }
    
}
