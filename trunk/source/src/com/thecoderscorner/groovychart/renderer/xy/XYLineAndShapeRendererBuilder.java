/*
 * XYLineAndShapeRendererBuilder.java
 *
 * Created on March 17, 2007, 11:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.renderer.xy;

import java.awt.*;
import java.beans.IntrospectionException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.chart.BeanBuilder;
import com.thecoderscorner.groovychart.chart.Buildable;
import com.thecoderscorner.groovychart.chart.ChartBuilder;
import com.thecoderscorner.groovychart.plot.Plotable;
import com.thecoderscorner.groovychart.renderer.Renderable;
import com.thecoderscorner.groovychart.util.AutoBeanPropertySetter;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 *
 * @author jclarke
 */
public class XYLineAndShapeRendererBuilder extends BeanBuilder implements Buildable, Renderable { 
    private static final Logger logger = Logger.getLogger(XYLineAndShapeRendererBuilder.class.getPackage().getName());
    
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    Integer index;

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
        }
        else if(method.equalsIgnoreCase("XYLineAndShapeRenderer")) {
            this.setProperties(this.renderer, map);
        }
        else if(method.equalsIgnoreCase("seriesPaint") && value != null) {
            Integer series = (Integer) value;
            Paint col = (Paint) map.get("paint");
            renderer.setSeriesPaint(series, col);
        }
        else if(method.equalsIgnoreCase("seriesShape") && value != null) {
            Integer series = (Integer) value;
            Shape shape = (Shape) map.get("shape");
            renderer.setSeriesShape(series, shape);
        }
        else if(method.equalsIgnoreCase("index")) {
            index = (Integer)value;
        }
        else {
            AutoBeanPropertySetter.autoSetProp(renderer, method, value);
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
            if(index == null) {
                ((XYPlot)((Plotable)parent).getPlot()).setRenderer(renderer);
            }
            else {
                ((XYPlot)((Plotable)parent).getPlot()).setRenderer(index, renderer);
            }
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
