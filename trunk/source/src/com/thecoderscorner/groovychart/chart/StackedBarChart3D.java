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
 * StackedBarChart.java
 *
 * Created on November 11, 2006, 10:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author jclarke
 */
public class StackedBarChart3D extends BasicChart  {
    
    /** Creates a new instance of StackedBarChart3D */
    public StackedBarChart3D() {
    }

    public JFreeChart createChart() {
        return ChartFactory.createStackedBarChart3D(getTitle(),
                this.getDomainAxisLabel(), this.getRangeAxisLabel(),
                (CategoryDataset)this.getDataset(), this.getOrientation(),
                this.isLegend(), this.isTooltips(), this.isUrls());
    }

    /**
     * Holds value of property domainAxisLabel.
     */
    private String domainAxisLabel;

    /**
     * Getter for property domainAxisLabel.
     * @return Value of property domainAxisLabel.
     */
    public String getDomainAxisLabel() {
        return this.domainAxisLabel;
    }

    /**
     * Setter for property domainAxisLabel.
     * @param domainAxisLabel New value of property domainAxisLabel.
     */
    public void setDomainAxisLabel(String domainAxisLabel) {
        this.domainAxisLabel = domainAxisLabel;
    }

    /**
     * Holds value of property rangeAxisLabel.
     */
    private String rangeAxisLabel;

    /**
     * Getter for property rangeAxisLabel.
     * @return Value of property rangeAxisLabel.
     */
    public String getRangeAxisLabel() {
        return this.rangeAxisLabel;
    }

    /**
     * Setter for property rangeAxisLabel.
     * @param rangeAxisLabel New value of property rangeAxisLabel.
     */
    public void setRangeAxisLabel(String rangeAxisLabel) {
        this.rangeAxisLabel = rangeAxisLabel;
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

}
