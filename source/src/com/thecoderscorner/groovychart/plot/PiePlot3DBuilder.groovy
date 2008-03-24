package com.thecoderscorner.groovychart.plot

import org.jfree.chart.labels.PieToolTipGenerator
import org.jfree.chart.plot.PiePlot3D

class PiePlot3DBuilder extends PlotBuilderGroovyBase {

    String name;

    public PiePlot3DBuilder() {
        super(new PiePlot3D());
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