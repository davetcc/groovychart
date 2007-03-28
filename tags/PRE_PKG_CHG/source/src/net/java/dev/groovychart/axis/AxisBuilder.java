/*
 * AxisBuilder.java
 *
 * Created on March 20, 2007, 8:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.axis;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.dev.groovychart.chart.Buildable;
import net.java.dev.groovychart.chart.ChartBuilder;
import org.jfree.chart.axis.Axis;

/**
 *
 * @author jclarke
 */
public abstract class AxisBuilder implements AxisSettable, Buildable {
    private static final Logger logger = Logger.getLogger(AxisBuilder.class.getPackage().getName());
    /**
     * Creates a new instance of AxisBuilder
     */
    public AxisBuilder() {
    }
    
    public void setChartBuilder(ChartBuilder chartBuilder) {
        //ignore
    }
    
    public void processNode(Object name, Map map, Object value) throws Exception {
        if(logger.isLoggable(Level.FINEST))
            logger.finest("name: " + name + " Map: " + map + " Value: " + value);
        if(value != null)
            setIndex(((Number)value).intValue());
        else if(map != null && map.containsKey("index"))
            setIndex( ((Number)map.get("index")).intValue() );
    }    

    /**
     * Holds value of property axis.
     */
    protected Axis axis;

    /**
     * Getter for property axis.
     * @return Value of property axis.
     */
    public Axis getAxis() {
        return this.axis;
    }

    /**
     * Setter for property axis.
     * @param axis New value of property axis.
     */
    public void setAxis(Axis axis) {
        this.axis = axis;
    }

    /**
     * Holds value of property name.
     */
    protected String name;

    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    protected  Object parent;
    
    public Object getParent() {
        return this.parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    } 
    

    /**
     * Holds value of property index.
     */
    private int index = -1;

    /**
     * Getter for property index.
     * @return Value of property index.
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Setter for property index.
     * @param index New value of property index.
     */
    public void setIndex(int index) {
        this.index = index;
    }
    
}
