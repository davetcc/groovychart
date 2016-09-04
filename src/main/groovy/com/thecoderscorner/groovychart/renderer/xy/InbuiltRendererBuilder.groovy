package com.thecoderscorner.groovychart.renderer.xy

import com.thecoderscorner.groovychart.chart.ChartBuilder
import com.thecoderscorner.groovychart.renderer.Renderable
import com.thecoderscorner.groovychart.chart.Buildable
import java.awt.Paint
import java.awt.Shape
import org.jfree.chart.renderer.AbstractRenderer

class InbuiltRendererBuilder implements Buildable, Renderable {
    def theRenderer
    String name
    Object parent;

    public void setChartBuilder(ChartBuilder chartBuilder) {
        theRenderer = chartBuilder.underlyingChart.chart.plot.renderer;
    }

    void processNode(Object name, Map map, Object value) {
       String method = name.toString();
        if(method.equalsIgnoreCase("renderer")) {
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
        else if(method.equalsIgnoreCase("seriesOutlinePaint") && value != null) {
            Integer series = (Integer) value;
            Paint col = (Paint) map.get("paint");
            theRenderer.setSeriesOutlinePaint(series, col);
        }
        else if(method.equalsIgnoreCase("seriesOutlineShape") && value != null) {
            Integer series = (Integer) value;
            Shape shape = (Shape) map.get("shape");
            theRenderer.setSeriesOutlineShape(series, shape);
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
