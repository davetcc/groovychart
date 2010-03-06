/*
 * CategoryPlotBuilder.java
 *
 * Created on March 20, 2007, 1:22 PM
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
import com.thecoderscorner.groovychart.chart.BeanBuilder;
import com.thecoderscorner.groovychart.chart.Buildable;
import com.thecoderscorner.groovychart.chart.ChartBuilder;
import com.thecoderscorner.groovychart.chart.Chartable;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.Plot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.Layer;

/**
 *
 * @author jclarke
 */
public class CategoryPlotBuilder extends BeanBuilder implements Buildable, Plotable {
   private static final Logger logger = Logger.getLogger(CategoryPlotBuilder.class.getPackage().getName());
    private CategoryPlot plot;
    
    /** Creates a new instance of CategoryPlotBuilder */
    public CategoryPlotBuilder() {
        try {
            setBeanClass(CategoryPlot.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }        
    }
    public void setChartBuilder(ChartBuilder chartBuilder) {
        chartBuilder.getUnderlyingChart().getChart().getPlot();
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString();
        if(logger.isLoggable(Level.FINEST))
            logger.finest("processNode method: " + method + " map: " + map  + " value: " + value);
        if(value != null && value instanceof CategoryPlot) {
            this.plot = (CategoryPlot)value;
        }else if(method.equalsIgnoreCase("categoryplot")) {
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
                            this.plot.addDomainMarker((CategoryMarker)marker, layer);
                    }
                }else if(value instanceof Marker) {
                    Marker marker= (Marker)value;
                    if(rangeMarker)
                        this.plot.addRangeMarker(marker);
                    else
                        this.plot.addDomainMarker((CategoryMarker)marker);
                }
            }else {
                if(map.containsKey("marker")) {
                    Marker marker = (Marker)map.get("marker");
                    if(map.containsKey("layer")) {
                        Layer layer = (Layer)map.get("layer");
                        if(rangeMarker)
                            this.plot.addRangeMarker(marker, layer);
                        else
                            this.plot.addDomainMarker((CategoryMarker)marker, layer);
                    }else {
                        if(rangeMarker)
                            this.plot.addRangeMarker(marker);
                        else
                            this.plot.addDomainMarker((CategoryMarker)marker);
                    }
                }
            }
            
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
        if(parent != null && parent instanceof Chartable) {
            this.getCategoryPlot().setDataset((CategoryDataset)((Chartable)parent).getDataset());
            ((Chartable)parent).setPlot(this.getPlot());
        } 
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
    
    public CategoryPlot getCategoryPlot() {
        return plot;
    }    
    
}
