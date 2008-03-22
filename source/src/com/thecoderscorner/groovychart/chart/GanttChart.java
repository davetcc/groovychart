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
 * GanttChart.java
 *
 * Created on November 11, 2006, 9:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.IntervalCategoryDataset;

/**
 *
 * @author jclarke
 */
public class GanttChart extends BasicChart {
    
    /** Creates a new instance of GanttChart */
    public GanttChart() {
    }

    /**
     * Holds value of property categoryAxisLabel.
     */
    private String categoryAxisLabel;

    /**
     * Getter for property categoryAxisLabel.
     * @return Value of property categoryAxisLabel.
     */
    public String getCategoryAxisLabel() {
        return this.categoryAxisLabel;
    }

    /**
     * Setter for property categoryAxisLabel.
     * @param categoryAxisLabel New value of property categoryAxisLabel.
     */
    public void setCategoryAxisLabel(String categoryAxisLabel) {
        this.categoryAxisLabel = categoryAxisLabel;
    }

    /**
     * Holds value of property dateAxisLabel.
     */
    private String dateAxisLabel;

    /**
     * Getter for property dateAxisLabel.
     * @return Value of property dateAxisLabel.
     */
    public String getDateAxisLabel() {
        return this.dateAxisLabel;
    }

    /**
     * Setter for property dateAxisLabel.
     * @param dateAxisLabel New value of property dateAxisLabel.
     */
    public void setDateAxisLabel(String dateAxisLabel) {
        this.dateAxisLabel = dateAxisLabel;
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

    public JFreeChart getChart() {
        
        JFreeChart chart = ChartFactory.createGanttChart(getTitle(),
                getCategoryAxisLabel(), getDateAxisLabel(),
                (IntervalCategoryDataset)getDataset(),
                isLegend(), isTooltips(), isUrls());
        return setExtraProperties(chart);

    }
    
}
