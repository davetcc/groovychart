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
 * BaseChart.java
 *
 * Created on November 11, 2006, 9:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.Dataset;

/**
 *
 * @author jclarke
 */
public abstract class BaseChart extends BeanBuilder implements Chartable, Buildable {
    private static final Logger logger = Logger.getLogger(BeanBuilder.class.getPackage().getName());
    protected ChartBuilder chartBuilder;
    protected Object parent;
    protected String name;
    protected Map chartProperties = new HashMap();
    private BeanBuilder bb = new BeanBuilder();
    
    /**
     * Creates a new instance of BaseChart
     */
    protected BaseChart() {
        try {
            setBeanClass(this.getClass());
            bb.setBeanClass(JFreeChart.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    /**
     * Holds value of property title.
     */
    private String title;

    /**
     * Getter for property title.
     * @return Value of property title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for property title.
     * @param title New value of property title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Holds value of property dataset.
     */
    private Dataset dataset;

    /**
     * Getter for property dataset.
     * @return Value of property dataset.
     */
    public Dataset getDataset() {
        return this.dataset;
    }

    /**
     * Setter for property dataset.
     * @param dataset New value of property dataset.
     */
    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }
    
    public void setChartBuilder(ChartBuilder chartBuilder) {
        this.chartBuilder = chartBuilder;
        this.chartBuilder.chartable = this;
    }
    
    public JFreeChart setExtraProperties(JFreeChart chart) {
        if(this.getTextTitle() != null)
            chart.setTitle(this.getTextTitle());
        if(chartProperties.size() > 0) {
            try {
                bb.setProperties(chart, chartProperties);
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
         }
        return chart;
    }
    public void processNode(Object name, Map map, Object value) throws Exception  {
        JSlider slider = new JSlider();
        Map m = new HashMap(map);
        Iterator it = map.keySet().iterator();
        // save propertis for the JFreeChart which are not fixed in this class
        while(it.hasNext()) {
            String property = (String)it.next();
            if(!hasProperty(property)) {
                chartProperties.put(property, m.remove(property));
            }
        }        
        setProperties(this,m);
    }   
    
    /**
     * Getter for property parent.
     * @return Value of property parent.
     */
    public Object getParent() {
        return this.parent;
    }

    /**
     * Setter for property parent.
     * @param parent New value of property parent.
     */
    public void setParent(Object parent) {
        this.parent = parent;
    }
    
    public void nodeCompleted(Object parent) {
        // empty
    }

    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }    
    
    /**
     * Holds value of property textTitle.
     */
    private TextTitle textTitle;

    /**
     * Getter for property textTitle.
     * @return Value of property textTitle.
     */
    public TextTitle getTextTitle() {
        return this.textTitle;
    }

    /**
     * Setter for property textTitle.
     * @param textTitle New value of property textTitle.
     */
    public void setTextTitle(TextTitle textTitle) {
        this.textTitle = textTitle;
    }    
    
    public String toString() {
        return this.name;
    }
    
    
    /**
     * Holds value of property plot.
     */
    private Plot plot;

    /**
     * Getter for property plot.
     * @return Value of property plot.
     */
    public Plot getPlot() {
        return this.plot;
    }

    /**
     * Setter for property plot.
     * @param plot New value of property plot.
     */
    public void setPlot(Plot plot) {
        this.plot = plot;
    }
    
    
}
