/*
 * Buildable.java
 *
 * Created on November 12, 2006, 12:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import java.util.Map;

/**
 *
 * @author jclarke
 */
public interface Buildable {
    
    public void setChartBuilder(ChartBuilder chartBuilder);
    
    public void processNode(Object name, Map map, Object value) throws Exception;

    /**
     * Getter for property parent.
     * @return Value of property parent.
     */
    public Buildable getParent();

    /**
     * Setter for property parent.
     * @param parent New value of property parent.
     */
    public void setParent(Buildable parent);

    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName();

    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name);
    
}
