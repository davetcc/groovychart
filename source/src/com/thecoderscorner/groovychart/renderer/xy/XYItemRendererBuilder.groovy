package com.thecoderscorner.groovychart.renderer.xy

import com.thecoderscorner.groovychart.chart.ChartBuilder
import com.thecoderscorner.groovychart.renderer.Renderable
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYItemRenderer
import com.thecoderscorner.groovychart.chart.Buildable
import java.awt.Paint
import java.awt.Shape
import org.jfree.chart.renderer.AbstractRenderer

class XYItemRendererBuilder implements Buildable, Renderable {
    XYItemRenderer theRenderer
    String name
    Object parent;

    public void setChartBuilder(ChartBuilder chartBuilder) {
        theRenderer = ((XYPlot)chartBuilder.underlyingChart.chart.plot).getRenderer();
    }

    void processNode(Object name, Map map, Object value) {
       String method = name.toString();
        if(method.equalsIgnoreCase("xyitemrenderer")) {
            map.each { key, val ->
                theRenderer[key] = val;
            }
        }
        else if(method.equalsIgnoreCase("seriesPaint") && value != null) {
            Integer series = (Integer) value;
            Paint col = (Paint) map.get("paint");
            theRenderer.setSeriesPaint(series, col);
        }
        else if(method.equalsIgnoreCase("seriesShape") && value != null) {
            Integer series = (Integer) value;
            Shape shape = (Shape) map.get("shape");
            theRenderer.setSeriesShape(series, shape);
        }
        else {
            theRenderer[name] = value;
        }
    }
    
    void nodeCompleted(Object parent) {
        // nothing to do, do not copy back the renderer
    }

    AbstractRenderer getRenderer() {
        return renderer;
    }

}
