package com.thecoderscorner.groovychart.plot

import org.jfree.chart.labels.PieToolTipGenerator
import org.jfree.chart.plot.PiePlot

class PiePlotBuilder extends PlotBuilderGroovyBase {

    public PiePlotBuilder() {
        super(new PiePlot())
    }

    public void processNode(Object name, Map map, Object value)
    {
        if(name == "toolTipGenerator") {
            plot.toolTipGenerator = value as PieToolTipGenerator;
        }
        else {
            super.processNode(name, map, value);
        }
    }


}