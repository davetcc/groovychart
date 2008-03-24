/*
 * XYBlockRendererBuilder.java
 *
 * Created on March 23, 2008, 11:08 PM
 *
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
import org.jfree.chart.renderer.xy.XYBlockRenderer;

/**
 *
 * @author Tiago Antao <tiagoantao@gmail.com>
 */
class XYBlockRendererBuilder extends BeanBuilder implements Buildable{ 
    private static final Logger logger = Logger.getLogger(XYBlockRendererBuilder.class.getPackage().getName())
    
    XYBlockRenderer renderer = new XYBlockRenderer()

    /** Creates a new instance of XYBlockRendererBuilder */
    XYBlockRendererBuilder() {
        try {
            setBeanClass(XYBlockRenderer.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex)
        }        
    }

    void setChartBuilder(ChartBuilder chartBuilder) {
    }

    void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString()
        if(value != null && value instanceof XYBlockRenderer) {
            this.renderer = (XYBlockRenderer)value
        }else if(method.equalsIgnoreCase("XYBlockRenderer")) {
            this.setProperties(this.renderer, map)
        }        
    }
    
    private Object parent

    Object getParent() {
        return parent
    }

    void setParent(Object parent) {
        this.parent = parent
    }

    public void nodeCompleted(Object parent) {
        if(parent != null && parent instanceof Plotable) {
            ((XYPlot)((Plotable)parent).getPlot()).setRenderer(renderer)
        } 
    }

    private String name
    
    public String getName() {
        return this.name
    }

    public void setName(String name) {
        this.name = name
    }
    
    public XYBlockRenderer getRenderer() {
        return renderer
    }
    
}
