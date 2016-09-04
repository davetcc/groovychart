/*
 * LookupPaintScaleBuilder.java
 *
 * Created on March 23, 2008, 11:08 PM
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
import org.jfree.chart.renderer.LookupPaintScale
import org.jfree.chart.renderer.xy.XYBlockRenderer

/**
 *
 * @author Tiago Antao <tiagoantao@gmail.com>
 */
class LookupPaintScaleBuilder extends BeanBuilder implements Buildable{ 
    private static final Logger logger = Logger.getLogger(LookupPaintScaleBuilder.class.getPackage().getName())

    LookupPaintScale paintScale


    /** Creates a new instance of LookupPaintScaleBuilder */
    LookupPaintScaleBuilder() {
        try {
            setBeanClass(LookupPaintScale.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex)
        }        
    }

    void setChartBuilder(ChartBuilder chartBuilder) {
    }

    void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString()
        if(value != null && value instanceof LookupPaintScale) {
            this.paintScale = (LookupPaintScale)value
        }else if(method.equalsIgnoreCase("LookupPaintScale")) {
            this.paintScale = map ?
                    new LookupPaintScale(
                        map['lowerBound'] ?: 0.0, map['upperBound'] ?: 1.0,
                        map['defaultPaint'] ?: Color.lightGray ) :
                    new LookupPaintScale()
        }else if(method.equalsIgnoreCase("add")) {
            List addPara = (List)value
            this.paintScale.add(addPara[0], addPara[1])
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
    
    public LookupPaintScale getPaintScale() {
        return paintScale
    }
    
}
