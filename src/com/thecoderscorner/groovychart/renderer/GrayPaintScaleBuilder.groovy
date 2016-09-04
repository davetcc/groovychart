/*
 * GrayPaintScaleBuilder.java
 *
 * Created on March 24, 2008, 11:08 PM
 *
 * (C) Tiago Antao
 *
 */

package com.thecoderscorner.groovychart.renderer

import java.beans.IntrospectionException
import java.util.logging.Level
import java.util.logging.Logger
import com.thecoderscorner.groovychart.chart.BeanBuilder
import com.thecoderscorner.groovychart.chart.Buildable
import com.thecoderscorner.groovychart.chart.ChartBuilder
import com.thecoderscorner.groovychart.renderer.xy.XYBlockRendererBuilder
import org.jfree.chart.renderer.GrayPaintScale
import org.jfree.chart.renderer.xy.XYBlockRenderer

/**
 *
 * @author Tiago Antao <tiagoantao@gmail.com>
 */
class GrayPaintScaleBuilder extends BeanBuilder implements Buildable { 
    private static final Logger logger = Logger.getLogger(GrayPaintScaleBuilder.class.getPackage().getName())

    GrayPaintScale paintScale


    /** Creates a new instance of GrayPaintScaleBuilder */
    GrayPaintScaleBuilder() {
        try {
            setBeanClass(GrayPaintScale.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex)
        }        
    }

    void setChartBuilder(ChartBuilder chartBuilder) {
    }

    void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString()
        if(value != null && value instanceof GrayPaintScale) {
            this.paintScale = (GrayPaintScale)value
        }else if(method.equalsIgnoreCase("GrayPaintScale")) {
            this.paintScale = map ?
                    new GrayPaintScale(
                        map['lowerBound'] ?: 0.0, map['upperBound'] ?: 1.0) :
                    new GrayPaintScale()
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
        if(parent != null && parent instanceof XYBlockRendererBuilder) {
            ((XYBlockRenderer)((XYBlockRendererBuilder)parent).getRenderer()).setPaintScale(paintScale)
        } 
    }

    private String name
    
    public String getName() {
        return this.name
    }

    public void setName(String name) {
        this.name = name
    }
    
    public GrayPaintScale getPaintScale() {
        return paintScale
    }
    
}
