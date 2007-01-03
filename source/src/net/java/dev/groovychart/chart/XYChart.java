/*
 *
 * Copyright 2006 Sun Microsystems, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * XYChart.java
 *
 * Created on November 11, 2006, 9:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import org.jfree.chart.plot.PlotOrientation;

/**
 *
 * @author jclarke
 */
public abstract class XYChart extends BasicChart {
    
    /** Creates a new instance of XYChart */
    public XYChart() {
    }
    
    /**
     * Holds value of property orientation.
     */
    private PlotOrientation orientation;

    /**
     * Getter for property orientation.
     * @return Value of property orientation.
     */
    public PlotOrientation getOrientation() {
        return this.orientation;
    }

    /**
     * Setter for property orientation.
     * @param orientation New value of property orientation.
     */
    public void setOrientation(PlotOrientation orientation) {
        this.orientation = orientation;
    }    

    /**
     * Holds value of property xAxisLabel.
     */
    private String xAxisLabel;

    /**
     * Getter for property xAxisLabel.
     * @return Value of property xAxisLabel.
     */
    public String getXAxisLabel() {
        return this.xAxisLabel;
    }

    /**
     * Setter for property xAxisLabel.
     * @param xAxisLabel New value of property xAxisLabel.
     */
    public void setXAxisLabel(String xAxisLabel) {
        this.xAxisLabel = xAxisLabel;
    }

    /**
     * Holds value of property yAxisLabel.
     */
    private String yAxisLabel;

    /**
     * Getter for property yAxisLabel.
     * @return Value of property yAxisLabel.
     */
    public String getYAxisLabel() {
        return this.yAxisLabel;
    }

    /**
     * Setter for property yAxisLabel.
     * @param yAxisLabel New value of property yAxisLabel.
     */
    public void setYAxisLabel(String yAxisLabel) {
        this.yAxisLabel = yAxisLabel;
    }
}
