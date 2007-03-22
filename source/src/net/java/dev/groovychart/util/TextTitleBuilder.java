/*
 * TextTitleBuilder.java
 *
 * Created on March 20, 2007, 1:28 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.util;

import java.beans.IntrospectionException;
import java.util.Iterator;
import java.util.Map;
import net.java.dev.groovychart.chart.BaseChart;
import net.java.dev.groovychart.chart.BeanBuilder;
import net.java.dev.groovychart.chart.Buildable;
import net.java.dev.groovychart.chart.ChartBuilder;
import org.jfree.chart.title.TextTitle;

/**
 *
 * @author jclarke
 */
public class TextTitleBuilder extends BeanBuilder implements Buildable {
    
    private TextTitle text = new TextTitle();
    
    /**
     * Creates a new instance of TextTitleBuilder
     */
    public TextTitleBuilder() {
        try {
            this.setBeanClass(TextTitle.class);
        } catch (IntrospectionException ex) {
            ex.printStackTrace();
        }
    }

    public void setChartBuilder(ChartBuilder chartBuilder) {
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString();
        if(value != null) {
            if(value instanceof TextTitle) {
                this.text = (TextTitle)value;
            }else {
                this.text = new TextTitle(value.toString());
            }
        } else if(method.equalsIgnoreCase("texttitle")) {
            this.setProperties(this.text, map);
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
        if(parent != null && parent instanceof BaseChart) {
            ((BaseChart)parent).setTextTitle(this.text);
        } 
    }

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
