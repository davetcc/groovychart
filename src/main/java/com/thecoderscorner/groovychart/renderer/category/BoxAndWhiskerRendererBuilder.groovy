/*
 * BoxAndWhiskerRendererBuilder.groovy
 *
 * Created on March 25, 2008, 1:28 PM
 *
 */

package com.thecoderscorner.groovychart.renderer.category

import java.beans.IntrospectionException
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.chart.BeanBuilder;
import com.thecoderscorner.groovychart.chart.Buildable;
import com.thecoderscorner.groovychart.chart.ChartBuilder;
import com.thecoderscorner.groovychart.plot.Plotable;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;

/**
 *
 * @author Tiago Antao <tiagoantao@gmail.com>
 */
public class BoxAndWhiskerRendererBuilder extends BeanBuilder implements Buildable {
    private static final Logger logger = Logger.getLogger(BoxAndWhiskerRendererBuilder.class.getPackage().getName());
    
    BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
    
    /**
     * Creates a new instance of BoxAndWhiskerRendererBuilder
     */
    public BoxAndWhiskerRendererBuilder() {
        try {
            setBeanClass(BoxAndWhiskerRenderer.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }         
    }
    
    public void setChartBuilder(ChartBuilder chartBuilder) {
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString();
        if(value != null && value instanceof BoxAndWhiskerRenderer) {
            this.renderer = (BoxAndWhiskerRenderer)value;
        }else if(method.equalsIgnoreCase("BoxAndWhiskerRenderer")) {
            this.setProperties(this.renderer, map)
        }        
    }
    
    private Object parent

    public Object getParent() {
        return parent
    }

    public void setParent(Object parent) {
        this.parent = parent
    }
    
    public void nodeCompleted(Object parent) {
        if(parent != null && parent instanceof Plotable) {
            ((CategoryPlot)((Plotable)parent).getPlot()).setRenderer(renderer)
        }     
    }

    private String name
    
    public String getName() {
        return this.name
    }

    public void setName(String name) {
        this.name = name
    }
    
    public BoxAndWhiskerRenderer getRenderer() {
        return renderer
    }
        
    
}
