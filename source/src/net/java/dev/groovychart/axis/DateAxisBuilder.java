/*
 * DateAxisBuilder.java
 *
 * Created on March 17, 2007, 10:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.axis;

import java.beans.IntrospectionException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.dev.groovychart.chart.Buildable;
import net.java.dev.groovychart.chart.ChartBuilder;
import org.jfree.chart.plot.XYPlot;
import net.java.dev.groovychart.chart.BeanBuilder;
import net.java.dev.groovychart.plot.Plotable;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.Plot;

/**
 *
 * @author jclarke
 */
public class DateAxisBuilder extends BeanBuilder implements Buildable{
    private static final Logger logger = Logger.getLogger(DateAxisBuilder.class.getPackage().getName());
    
    private DateAxis axis = new DateAxis();
    private boolean domain = false;
    /**
     * Creates a new instance of DateAxisBuilder
     */
    public DateAxisBuilder() {
        try {
            setBeanClass(DateAxis.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    public void setChartBuilder(ChartBuilder chartBuilder) {
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString();
        if(value != null) {
            this.axis = (DateAxis)value;
        }else {
            if(logger.isLoggable(Level.FINEST))
                logger.finest("processNode: method = " + method);        
            if(method.equalsIgnoreCase("dateaxis")) {
                this.setProperties(this.axis, map);
            }
        }
    }

    private Object parent;
    public Object getParent() {
        return parent;
    }
    public void setParent(Object parent) {
        this.parent = parent;
    }

    public void nodeCompleted(Object parent) {
        if(parent != null && parent instanceof AxisSettable) {
            logger.finest("Setting axis on parent");
            ((AxisSettable)parent).setAxis(this.axis);
        }
    }

    private String name;
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public DateAxis getAxis() {
        return axis;
    }

}
