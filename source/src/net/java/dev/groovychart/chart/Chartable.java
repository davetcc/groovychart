/*
 * Chartable.java
 *
 * Created on November 11, 2006, 9:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;

/**
 *
 * @author jclarke
 */
public interface Chartable {
    
    public JFreeChart getChart();
    
    
    public void setDataset(Dataset dataset);
    
}
