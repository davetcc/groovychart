/*
 * XYLineAndShapeRendererBuilder.java
 *
 * Created on March 17, 2007, 11:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.renderer.xy;

import java.beans.IntrospectionException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.chart.BeanBuilder;
import com.thecoderscorner.groovychart.chart.Buildable;
import com.thecoderscorner.groovychart.chart.ChartBuilder;
import com.thecoderscorner.groovychart.plot.Plotable;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 *
 * @author jclarke
 */
public class XYLineAndShapeRendererBuilder extends BeanBuilder implements Buildable{ 
    private static final Logger logger = Logger.getLogger(XYLineAndShapeRendererBuilder.class.getPackage().getName());
    
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

    /** Creates a new instance of XYLineAndShapeRendererBuilder */
    public XYLineAndShapeRendererBuilder() {
        try {
            setBeanClass(XYLineAndShapeRenderer.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }        
    }

    public void setChartBuilder(ChartBuilder chartBuilder) {
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString();
        if(value != null && value instanceof XYLineAndShapeRenderer) {
            this.renderer = (XYLineAndShapeRenderer)value;
        }else if(method.equalsIgnoreCase("XYLineAndShapeRenderer")) {
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
            ((XYPlot)((Plotable)parent).getPlot()).setRenderer(renderer);
        } 
    }
    private String name;
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public XYLineAndShapeRenderer getRenderer() {
        return renderer;
    }
    
}
