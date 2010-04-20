package com.thecoderscorner.groovychart.axis

import com.thecoderscorner.groovychart.chart.ChartBuilder
import com.thecoderscorner.groovychart.chart.Buildable

class DomainAxisBuilder implements Buildable {
    String name
    Object parent
    Object domainAxis;

    void setChartBuilder(ChartBuilder chartBuilder) {
        domainAxis = chartBuilder.underlyingChart.chart.plot.domainAxis;
    }

    void processNode(Object name, Map map, Object value) {
        if(name=="domainaxis") {
            map?.each {k,v->
                domainAxis[k] =v;
            }
        }
        else {
            domainAxis[name] = value;
        }
    }

    void nodeCompleted(Object parent) {
        // do nothing here please.
    }
}
