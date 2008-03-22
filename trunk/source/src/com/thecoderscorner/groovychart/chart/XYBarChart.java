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
 * AreaChart.java
 *
 * Created on November 11, 2006, 9:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.IntervalXYDataset;

/**
 *
 * @author jclarke
 */
public class XYBarChart extends XYChart {
    
    /**
     * Creates a new instance of AreaChart
     */
    public XYBarChart() {
        super();
    }

    public JFreeChart getChart() {
        JFreeChart chart = ChartFactory.createXYBarChart(this.getTitle(),
                this.getXAxisLabel(), this.isDateAxis(), this.getYAxisLabel(),
                (IntervalXYDataset)this.getDataset(), this.getOrientation(),
                this.isLegend(), this.isTooltips(), this.isUrls());
        return setExtraProperties(chart);

    }

    /**
     * Holds value of property dateAxis.
     */
    private boolean dateAxis;

    /**
     * Getter for property dateAxis.
     * @return Value of property dateAxis.
     */
    public boolean isDateAxis() {
        return this.dateAxis;
    }

    /**
     * Setter for property dateAxis.
     * @param dateAxis New value of property dateAxis.
     */
    public void setDateAxis(boolean dateAxis) {
        this.dateAxis = dateAxis;
    }
    
}
