package com.thecoderscorner.groovychart.axis

import com.thecoderscorner.groovychart.chart.Buildable
import com.thecoderscorner.groovychart.chart.ChartBuilder

class RangeAxisBuilder implements Buildable {
    String name
    Object parent
    Object rangeAxis;

    void setChartBuilder(ChartBuilder chartBuilder) {
        rangeAxis = chartBuilder.underlyingChart.chart.plot.rangeAxis;
    }

    void processNode(Object name, Map map, Object value) {
        if(name=="rangeaxis") {
            map?.each {k,v->
                domainAxis[k] =v;
            }
        }
        else {
            rangeAxis[name] = value;
        }
    }

    void nodeCompleted(Object parent) {
        // do nothing here please.
    }
}
