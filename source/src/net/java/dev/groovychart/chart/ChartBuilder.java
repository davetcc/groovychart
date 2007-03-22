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
 * ChartBuilder.java
 *
 * Created on November 11, 2006, 8:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import groovy.util.BuilderSupport;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.dev.groovychart.axis.CategoryAxisBuilder;
import net.java.dev.groovychart.axis.DateAxisBuilder;
import net.java.dev.groovychart.axis.DomainAxisBuilder;
import net.java.dev.groovychart.axis.NumberAxisBuilder;
import net.java.dev.groovychart.axis.RangeAxisBuilder;
import net.java.dev.groovychart.dataset.category.DefaultCategorySetBuilder;
import net.java.dev.groovychart.dataset.series.xy.DefaultXYDatasetBuilder;
import net.java.dev.groovychart.dataset.category.JDBCCategoryDatasetBuilder;
import net.java.dev.groovychart.dataset.series.xy.interval.TimeSeriesBuilder;
import net.java.dev.groovychart.dataset.series.xy.interval.TimeSeriesCollectionBuilder;
import net.java.dev.groovychart.plot.CategoryPlotBuilder;
import net.java.dev.groovychart.plot.XYPlotBuilder;
import net.java.dev.groovychart.util.BarRendererBuilder;
import net.java.dev.groovychart.util.StackedBarRendererBuilder;
import net.java.dev.groovychart.util.TextTitleBuilder;
import net.java.dev.groovychart.util.XYLineAndShapeRendererBuilder;

/**
 *
 * @author jclarke
 */
public class ChartBuilder extends BuilderSupport {
    private static final Logger logger = Logger.getLogger(ChartBuilder.class.getPackage().getName());
    Chartable chartable;
    
    private Buildable lastNode;
    
    private static HashMap<String, Class> processClasses = new HashMap<String, Class>();
    
    static {
        //charts, make sure all keys are lower case
        processClasses.put("chart", Chart.class);
        processClasses.put("areachart", AreaChart.class);
        processClasses.put("barchart", BarChart.class);
        processClasses.put("barchart3d", BarChart3D.class);
        processClasses.put("boxandwhiskerchart", BoxAndWhiskerChart.class);
        processClasses.put("bubblechart", BubbleChart.class);
        processClasses.put("candlestickchart", CandlestickChart.class);
        processClasses.put("ganttchart", GanttChart.class);
        processClasses.put("highlowchart", HighLowChart.class);
        processClasses.put("historgramchart", HistorgramChart.class);
        processClasses.put("linechart", LineChart.class);
        processClasses.put("linechart3d", LineChart3D.class);
        processClasses.put("multiplepiechart", MultiplePieChart.class);
        processClasses.put("multiplepiechart3d", MultiplePieChart3D.class);
        processClasses.put("piechart", PieChart.class);
        processClasses.put("piechart3d", PieChart3D.class);
        processClasses.put("piechartdiff", PieChartDiff.class);
        processClasses.put("polarchart", PolarChart.class);
        processClasses.put("ringchart", RingChart.class);
        processClasses.put("scatterplot", ScatterPlot.class);
        processClasses.put("stackedareachart", StackedAreaChart.class);
        processClasses.put("stackedbarchart", StackedBarChart.class);
        processClasses.put("stackedbarchart3d", StackedBarChart3D.class);
        processClasses.put("stackedxyareachart", StackedXYAreaChart.class);
        processClasses.put("timeserieschart", TimeseriesChart.class);
        processClasses.put("wafermapchart", WaferMapChart.class);
        processClasses.put("waterfallchart", WaterfallChart.class);
        processClasses.put("windplotchart", WindplotChart.class);
        processClasses.put("xyareachart", XYAreaChart.class);
        processClasses.put("xybarchart", XYBarChart.class);
        processClasses.put("xychart", XYChart.class);
        processClasses.put("xylinechart", XYLineChart.class);
        processClasses.put("xystepareachart", XYStepAreaChart.class);
        processClasses.put("xystepchart", XYStepChart.class);
        
        // data sets
        processClasses.put("defaultcategorydataset", DefaultCategorySetBuilder.class);
        processClasses.put("jdbccategorydataset", JDBCCategoryDatasetBuilder.class);
        processClasses.put("defaultxydataset", DefaultXYDatasetBuilder.class);
        processClasses.put("timeseriescollection", TimeSeriesCollectionBuilder.class);
        processClasses.put("timeseries", TimeSeriesBuilder.class);
        
        // plots
        processClasses.put("xyplot", XYPlotBuilder.class);
        processClasses.put("categoryplot", CategoryPlotBuilder.class);
        
        // axis
        processClasses.put("dateaxis", DateAxisBuilder.class);
        processClasses.put("numberaxis", NumberAxisBuilder.class);
        processClasses.put("categoryaxis", CategoryAxisBuilder.class);
        processClasses.put("domainaxis", DomainAxisBuilder.class);
        processClasses.put("rangeaxis", RangeAxisBuilder.class);
        
        // renderer
        processClasses.put("xylineandshaperenderer", XYLineAndShapeRendererBuilder.class);
        processClasses.put("stackedbarrenderer", StackedBarRendererBuilder.class);
        processClasses.put("barrenderer", BarRendererBuilder.class);
        
        // misc.
        processClasses.put("titletext", TextTitleBuilder.class);
    }

    
    /**
     * Creates a new instance of ChartBuilder
     */
    public ChartBuilder() {
        super();
    }

    protected void nodeCompleted(Object parent, Object node) {
        if(logger.isLoggable(Level.FINEST)) {
            logger.finest("nodeCompleted, parent=" + parent + " child=" + node);
        }
        if(node instanceof Buildable)
            ((Buildable)node).nodeCompleted(parent);
        
    }
    protected void setParent(Object parent, Object child) {
        if(child instanceof Buildable)
            ((Buildable)child).setParent(parent);
    }

    protected Object createNode(Object name) {
        if(logger.isLoggable(Level.FINEST)) {
            logger.finest("ChartBuilder: createNode(name="+ name +", " +
            "map= null, " +
            "value=null)");
        }        
        return createNode(name, null, null);  
    }

    protected Object createNode(Object name, Object value) {
        if(logger.isLoggable(Level.FINEST)) {
            logger.finest("ChartBuilder: createNode(name="+ name +", " +
            "map=null, " +
            "value=" + value + ")");
        }
        return createNode(name, null, value);
    }

    protected Object createNode(Object name, Map map) {
        if(logger.isLoggable(Level.FINEST)) {
            logger.finest("ChartBuilder: createNode(name="+ name +", " +
            "map=" + map + ", " +
            "value=null)");
        }        
        return createNode(name, map, null);
    }
    protected Object createNode(Object name, Map map, Object value) {
        if(logger.isLoggable(Level.FINE)) {
            logger.fine("ChartBuilder: createNode(name="+ name +", " +
            "map=" + map + ", " +
           "value=" + value + ")");
        }
        Class processClass = processClasses.get(name.toString().toLowerCase());
        try {
            if(processClass == null) {
                if(logger.isLoggable(Level.FINEST)) {
                    logger.finest("ChartBuilder: '" + name.toString() + "' process last node");
                }
                lastNode.processNode(name, map, value);
            } else {
                Buildable processObject = null;
                if(logger.isLoggable(Level.FINEST)) {
                    logger.finest("ChartBuilder: process = " + processClass);
                }
                processObject = (Buildable)processClass.newInstance();
                
                processObject.setChartBuilder(this);
                processObject.setName(name.toString());
                processObject.processNode(name, map, value);
                lastNode = processObject;
                
            }
        } catch (Exception ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
        return lastNode;
    }    
    


    
    
    
}
