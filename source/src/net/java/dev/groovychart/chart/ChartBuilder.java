/*
 * ChartBuilder.java
 *
 * Created on November 11, 2006, 8:43 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import groovy.util.BuilderSupport;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

/**
 *
 * @author jclarke
 */
public class ChartBuilder extends BuilderSupport {
    
    Chartable chartable;
    
    private Buildable lastNode;
    
    private static HashMap<String, Class> processClasses = new HashMap<String, Class>();
    
    static {
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
        
        processClasses.put("defaultcategorydataset", DefaultCategorySetBuilder.class);
        processClasses.put("jdbccategorydataset", JDBCCategoryDatasetBuilder.class);
        processClasses.put("defaultxydataset", DefaultXYDatasetBuilder.class);
    }
    
    /**
     * Creates a new instance of ChartBuilder
     */
    public ChartBuilder() {
        super();
    }

    protected void setParent(Object parent, Object child) {
        System.out.println("ChartBuilder: setParent(parent = " + parent + 
                            " child = " + child+ ")");
        if(parent == null || child == null || parent.equals(child) )
            return;
        else
            ((Buildable)child).setParent((Buildable)parent);
    }

    protected Object createNode(Object name) {
        System.out.println("ChartBuilder: createNode(name=" + name + ")");
        return createNode(name, null, null);  
    }

    protected Object createNode(Object name, Object value) {
        System.out.println("ChartBuilder: createNode(name=" + name + ", " +
                                        "value=" + value+ ")");
        return createNode(name, null, value);
    }

    protected Object createNode(Object name, Map map) {
        System.out.println("ChartBuilder: createNode(name="+ name +", " +
                            "map=" + map + ")");
        return createNode(name, map, null);
    }
    protected Object createNode(Object name, Map map, Object value) {
        System.out.println("ChartBuilder: createNode(name="+ name +", " +
            "map=" + map + ", " +
           "value=" + value + ")");
        Class processClass = processClasses.get(name.toString().toLowerCase());
        try {
            if(processClass == null) {
                System.out.println("ChartBuilder: process last node");
                lastNode.processNode(name, map, value);
            } else {
                Buildable processObject = null;
                System.out.println("ChartBuilder: process = " + processObject);
                processObject = (Buildable)processClass.newInstance();
                processObject.setChartBuilder(this);
                processObject.setName(name.toString());
                processObject.processNode(name, map, value);
                lastNode = processObject;
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lastNode;
    }    
    


    
    
    public JFreeChart getChart() {
        return this.chartable.getChart();
    }
    
}
