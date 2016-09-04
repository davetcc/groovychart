/*
 * CategoryAxisBuilder.java
 *
 * Created on March 20, 2007, 10:00 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.axis;

import java.beans.IntrospectionException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.chart.BeanBuilder;
import com.thecoderscorner.groovychart.chart.Buildable;
import com.thecoderscorner.groovychart.chart.ChartBuilder;
import org.jfree.chart.axis.CategoryAxis;

/**
 *
 * @author jclarke
 */
public class CategoryAxisBuilder extends BeanBuilder implements Buildable{ 
    private static final Logger logger = Logger.getLogger(CategoryAxisBuilder.class.getPackage().getName());
    
    private CategoryAxis axis = new CategoryAxis();
    
    /** Creates a new instance of CategoryAxisBuilder */
    public CategoryAxisBuilder() {
        try {
            setBeanClass(CategoryAxis.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }        
    }

    public void setChartBuilder(ChartBuilder chartBuilder) {
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString();
        if(logger.isLoggable(Level.FINEST))
            logger.finest("processNode: method = " + method);
        if(method.equalsIgnoreCase("categoryaxis")) {
            if(value != null)
                this.axis = (CategoryAxis)value;
            else {
                if(logger.isLoggable(Level.FINEST))
                    logger.finest("Setting properties: " + map);
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
    
}
