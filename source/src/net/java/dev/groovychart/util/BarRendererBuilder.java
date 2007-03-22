/*
 * StackedBarRendererBuilder.java
 *
 * Created on March 20, 2007, 1:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.util;

import java.beans.IntrospectionException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.dev.groovychart.chart.BeanBuilder;
import net.java.dev.groovychart.chart.Buildable;
import net.java.dev.groovychart.chart.ChartBuilder;
import net.java.dev.groovychart.plot.Plotable;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;

/**
 *
 * @author jclarke
 */
public class BarRendererBuilder extends BeanBuilder implements Buildable {
    private static final Logger logger = Logger.getLogger(BarRendererBuilder.class.getPackage().getName());
    
    BarRenderer renderer = new BarRenderer();
    
    /**
     * Creates a new instance of StackedBarRendererBuilder
     */
    public BarRendererBuilder() {
        try {
            setBeanClass(BarRenderer.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }         
    }
    
    public void setChartBuilder(ChartBuilder chartBuilder) {
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString();
        if(value != null && value instanceof BarRenderer) {
            this.renderer = (BarRenderer)value;
        }else if(method.equalsIgnoreCase("BarRenderer")) {
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
    
    public BarRenderer getRenderer() {
        return renderer;
    }
        
    
}
