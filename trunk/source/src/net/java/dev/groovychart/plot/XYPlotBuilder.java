/*
 * XYPlotBuilder.java
 *
 * Created on March 17, 2007, 10:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.plot;

import java.beans.IntrospectionException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.dev.groovychart.chart.Buildable;
import net.java.dev.groovychart.chart.ChartBuilder;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import net.java.dev.groovychart.chart.BeanBuilder;
import net.java.dev.groovychart.chart.Chartable;
import org.jfree.chart.plot.Marker;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.Layer;

/**
 *
 * @author jclarke
 */
public class XYPlotBuilder extends BeanBuilder implements Buildable, Plotable{
    private static final Logger logger = Logger.getLogger(XYPlotBuilder.class.getPackage().getName());
    private XYPlot plot = new XYPlot();
    /** Creates a new instance of XYPlotBuilder */
    public XYPlotBuilder() {
        try {
            setBeanClass(XYPlot.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    public void setChartBuilder(ChartBuilder chartBuilder) {
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
            this.getXYPlot().setDataset((XYDataset)((Chartable)parent).getDataset());
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
    
    public XYPlot getXYPlot() {
        return plot;
    }    
    
}
