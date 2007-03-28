/*
 * Chart.java
 *
 * Created on March 18, 2007, 9:52 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import java.awt.Font;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author jclarke
 */
public class Chart extends BaseChart {
    
    
    /** Creates a new instance of Chart */
    public Chart() {
        super();
    }
    
     public JFreeChart getChart() {

         JFreeChart chart = new JFreeChart(this.getTitle(), this.getTitleFont(), this.getPlot(), this.isCreateLegend());
         return setExtraProperties(chart);
     }
    /**
     * Holds value of property titleFont.
     */
    private Font titleFont;

    /**
     * Getter for property titleFont.
     * @return Value of property titleFont.
     */
    public Font getTitleFont() {
        return this.titleFont;
    }

    /**
     * Setter for property titleFont.
     * @param titleFont New value of property titleFont.
     */
    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }

    /**
     * Holds value of property createLegend.
     */
    private boolean createLegend;

    /**
     * Getter for property legend.
     * @return Value of property legend.
     */
    public boolean isCreateLegend() {
        return this.createLegend;
    }

    /**
     * Setter for property legend.
     * @param legend New value of property legend.
     */
    public void setCreateLegend(boolean createLegend) {
        this.createLegend = createLegend;
    }


    
}
