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

package net.java.dev.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.TableXYDataset;

/**
 *
 * @author jclarke
 */
public class StackedXYAreaChart extends XYChart {
    
    /**
     * Creates a new instance of AreaChart
     */
    public StackedXYAreaChart() {
        super();
    }

    public JFreeChart getChart() {
        return ChartFactory.createStackedXYAreaChart(getTitle(), getXAxisLabel(),
                getYAxisLabel(), (TableXYDataset)getDataset(),
                getOrientation(), isLegend(), isTooltips(), isUrls());
    }
    
}
