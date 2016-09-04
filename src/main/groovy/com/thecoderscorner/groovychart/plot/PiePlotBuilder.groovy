package com.thecoderscorner.groovychart.plot

import com.thecoderscorner.groovychart.chart.Buildable
import com.thecoderscorner.groovychart.chart.ChartBuilder
import java.awt.Paint
import org.jfree.chart.plot.Plot

class PiePlotBuilder implements Buildable, Plotable {
    String name
    Object parent
    Plot plot;

    void setChartBuilder(ChartBuilder chartBuilder) {
        plot = chartBuilder.underlyingChart.chart.plot;
    }

    void processNode(Object name, Map map, Object value) {
        if(name=="pieplot") {
            map?.each {k,v->
                plot[k] =v;
            }
        }
        else if(name=="sectionPaint") {
            plot.setSectionPaint(value as Comparable, map.paint as Paint);
        }
        else if(name=="sectionOutlinePaint") {
            plot.setSectionOutlinePaint(value as Comparable, map.paint as Paint);
        }
        else {
            plot[name] = value;
        }
    }

    void nodeCompleted(Object parent) {
        // do nothing here please.
    }

}
