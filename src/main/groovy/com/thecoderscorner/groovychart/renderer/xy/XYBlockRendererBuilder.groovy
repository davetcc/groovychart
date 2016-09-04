/*
 * XYBlockRendererBuilder.java
 *
 * Created on March 23, 2008, 11:08 PM
 *
 */

package com.thecoderscorner.groovychart.renderer.xy

import java.util.logging.Logger

import com.thecoderscorner.groovychart.chart.Buildable;
import com.thecoderscorner.groovychart.chart.ChartBuilder;
import com.thecoderscorner.groovychart.plot.Plotable;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBlockRenderer
import com.thecoderscorner.groovychart.renderer.Renderable
import org.jfree.chart.renderer.AbstractRenderer;

/**
 *
 * @author Tiago Antao <tiagoantao@gmail.com>
 */
class XYBlockRendererBuilder implements Buildable, Renderable {
    private static final Logger logger = Logger.getLogger(XYBlockRendererBuilder.class.getPackage().getName())

    Object parent
    String name
    private XYBlockRenderer renderer = new XYBlockRenderer()

    /** Creates a new instance of XYBlockRendererBuilder */
    void setChartBuilder(ChartBuilder chartBuilder) {
    }

    void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString()
        if(value != null && value instanceof XYBlockRenderer) {
            this.renderer = (XYBlockRenderer)value
        }
        else if(method.equalsIgnoreCase("XYBlockRenderer")) {
            map?.each{ k, v ->
                renderer[k] = v;
            }
        }
        else {
            renderer[k] = v;
        }
    }

    public void nodeCompleted(Object parent) {
        if(parent != null && parent instanceof Plotable) {
            ((XYPlot)((Plotable)parent).getPlot()).setRenderer(renderer)
        } 
    }


    public AbstractRenderer getRenderer() {
        return renderer
    }
    
}
