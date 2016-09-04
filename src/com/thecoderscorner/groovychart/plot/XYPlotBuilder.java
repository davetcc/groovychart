/*
 * XYPlotBuilder.java
 *
 * Created on March 17, 2007, 10:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.plot;

import java.beans.IntrospectionException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.chart.Buildable;
import com.thecoderscorner.groovychart.chart.ChartBuilder;
import com.thecoderscorner.groovychart.util.AutoBeanPropertySetter;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import com.thecoderscorner.groovychart.chart.BeanBuilder;
import org.jfree.chart.plot.Marker;
import org.jfree.ui.Layer;

/**
 *
 * @author jclarke
 */
public class XYPlotBuilder extends BeanBuilder implements Buildable, Plotable{
    private static final Logger logger = Logger.getLogger(XYPlotBuilder.class.getPackage().getName());
    private XYPlot plot;
    /** Creates a new instance of XYPlotBuilder */
    public XYPlotBuilder() {
        try {
            setBeanClass(XYPlot.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    public void setChartBuilder(ChartBuilder chartBuilder) {
        plot = (XYPlot)chartBuilder.getUnderlyingChart().getChart().getPlot();
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString();
        if(logger.isLoggable(Level.FINEST))
            logger.finest("processNode method: " + method + " map: " + map  + " value: " + value); 
        if(value != null && value instanceof XYPlot) {
            this.plot = (XYPlot)value;        
        }else if(method.equalsIgnoreCase("xyplot")) {
            this.setProperties(this.plot, map);
        }else if (method.equalsIgnoreCase("rangeMarker") ||
                method.equalsIgnoreCase("domainMarker")) {
            boolean rangeMarker = method.equalsIgnoreCase("rangeMarker");
            if(value != null) {
                
                if(value instanceof Map) {
                    Iterator it = map.keySet().iterator();
                    while(it.hasNext()) {
                        Layer layer = (Layer)it.next();
                        Marker marker= (Marker)map.get(layer); 
                        if(rangeMarker)
                            this.plot.addRangeMarker(marker, layer);
                        else
                            this.plot.addDomainMarker(marker, layer);
                    }
                }else if(value instanceof Marker) {
                    Marker marker= (Marker)value;
                    if(rangeMarker)
                        this.plot.addRangeMarker(marker);
                    else
                        this.plot.addDomainMarker(marker);
                }
            }else {
                if(map.containsKey("marker")) {
                    Marker marker = (Marker)map.get("marker");
                    if(map.containsKey("layer")) {
                        Layer layer = (Layer)map.get("layer");
                        if(rangeMarker)
                            this.plot.addRangeMarker(marker, layer);
                        else
                            this.plot.addDomainMarker(marker, layer);
                    }else {
                        if(rangeMarker)
                            this.plot.addRangeMarker(marker);
                        else
                            this.plot.addDomainMarker(marker);
                    }
                }else {
                    Iterator it = map.keySet().iterator();
                    while(it.hasNext()){
                        Layer layer = (Layer)it.next();
                        Marker marker = (Marker)map.get(layer);
                        if(rangeMarker)
                            this.plot.addRangeMarker(marker, layer);
                        else
                            this.plot.addDomainMarker(marker, layer);
                    }
                }
            }
            
        }
        else {
            AutoBeanPropertySetter.autoSetProp(plot, method, value);
        }
    }

    private Object parent;
    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;

    }
    
    public void nodeCompleted(Object parent) {
        // do not set the plot, we got the plot earlier.
    }

    private String name;
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plot getPlot() {
        return plot;
    }
    
    public XYPlot getXYPlot() {
        return plot;
    }    
    
}
