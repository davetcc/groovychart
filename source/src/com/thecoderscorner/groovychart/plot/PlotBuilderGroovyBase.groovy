package com.thecoderscorner.groovychart.plot

import com.thecoderscorner.groovychart.chart.Buildable
import com.thecoderscorner.groovychart.chart.ChartBuilder
import com.thecoderscorner.groovychart.chart.Chartable
import org.jfree.chart.plot.Plot

class PlotBuilderGroovyBase implements Plotable, Buildable {

    Plot plot;
    String name;
    Object parent;

    PlotBuilderGroovyBase() {
    }

    public void setChartBuilder(ChartBuilder chartBuilder) {
        plot = chartBuilder.underlyingChart.chart.plot;
    }

    public void processNode(Object name, Map map, Object value) {

        // set simple properties by traversing the map
        if(map != null) {
            map.each { key, val ->
                plot[key] = val;
            }
        }
        else {
            plot[name] = value;
        }
    }

    public void nodeCompleted(Object parent) {
        if(parent != null && parent instanceof Chartable) {
            this.plot['dataset'] = parent['dataset']
            parent.plot = this.plot;
        }

    }

}