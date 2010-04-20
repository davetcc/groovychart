/*
 * StackedBarRendererBuilder.java
 *
 * Created on March 20, 2007, 1:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.renderer.category;

import java.beans.IntrospectionException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.chart.BeanBuilder;
import com.thecoderscorner.groovychart.chart.Buildable;
import com.thecoderscorner.groovychart.chart.ChartBuilder;
import com.thecoderscorner.groovychart.plot.Plotable;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StackedBarRenderer;

/**
 *
 * @author jclarke
 */
public class StackedBarRendererBuilder extends BeanBuilder implements Buildable {
    private static final Logger logger = Logger.getLogger(StackedBarRendererBuilder.class.getPackage().getName());
    
    StackedBarRenderer renderer = new StackedBarRenderer();
    
    /**
     * Creates a new instance of StackedBarRendererBuilder
     */
    public StackedBarRendererBuilder() {
        try {
            setBeanClass(StackedBarRenderer.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }         
    }
    
    public void setChartBuilder(ChartBuilder chartBuilder) {
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString();
        if(value != null && value instanceof StackedBarRenderer) {
            this.renderer = (StackedBarRenderer)value;
        }else if(method.equalsIgnoreCase("StackedBarRenderer")) {
            this.setProperties(this.renderer, map);
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
        if(parent != null && parent instanceof Plotable) {
            ((CategoryPlot)((Plotable)parent).getPlot()).setRenderer(renderer);
        }     
    }

    private String name;
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public StackedBarRenderer getRenderer() {
        return renderer;
    }
        
    
}
