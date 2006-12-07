/*
 * BasicChart.java
 *
 * Created on November 11, 2006, 9:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

/**
 *
 * @author jclarke
 */
public abstract class BasicChart extends BaseChart {
    
    /** Creates a new instance of BasicChart */
    protected BasicChart() {
    }
    /**
     * Holds value of property legend.
     */
    private boolean legend;

    /**
     * Getter for property legend.
     * @return Value of property legend.
     */
    public boolean isLegend() {
        return this.legend;
    }

    /**
     * Setter for property legend.
     * @param legend New value of property legend.
     */
    public void setLegend(boolean legend) {
        this.legend = legend;
    }

    /**
     * Holds value of property tooltips.
     */
    private boolean tooltips;

    /**
     * Getter for property tooltips.
     * @return Value of property tooltips.
     */
    public boolean isTooltips() {
        return this.tooltips;
    }

    /**
     * Setter for property tooltips.
     * @param tooltips New value of property tooltips.
     */
    public void setTooltips(boolean tooltips) {
        this.tooltips = tooltips;
    }

    /**
     * Holds value of property urls.
     */
    private boolean urls;

    /**
     * Getter for property urls.
     * @return Value of property urls.
     */
    public boolean isUrls() {
        return this.urls;
    }

    /**
     * Setter for property urls.
     * @param urls New value of property urls.
     */
    public void setUrls(boolean urls) {
        this.urls = urls;
    }
    
}
