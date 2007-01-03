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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jfree.data.general.Dataset;

/**
 *
 * @author jclarke
 */
public abstract class BaseChart implements Chartable, Buildable {
    protected ChartBuilder chartBuilder;
    protected Buildable parent;
    protected String name;
    
    /**
     * Creates a new instance of BaseChart
     */
    protected BaseChart() {
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
    
    public void processNode(Object name, Map map, Object value) throws Exception  {
        setProperties(map);
    }   
    
    /**
     * Getter for property parent.
     * @return Value of property parent.
     */
    public Buildable getParent() {
        return this.parent;
    }

    /**
     * Setter for property parent.
     * @param parent New value of property parent.
     */
    public void setParent(Buildable parent) {
        this.parent = parent;
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
    
    public String toString() {
        return this.name;
    }
    
    private void setProperties(Map map) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Map<String, PropertyDescriptor> propertyMap = loadBean(this.getClass());
        Iterator it = map.keySet().iterator();
        while(it.hasNext()) {
            String property = (String)it.next();
            Object value = map.get(property);
            //System.out.println("Value = " + value.getClass());
            PropertyDescriptor desc = propertyMap.get(property);
            if(desc == null){
                throw new IllegalArgumentException("Invalid property: " + property);
            }
            Method meth = desc.getWriteMethod();
            Class propertyType = desc.getPropertyType();
            if(propertyType.equals(String.class)) {
                String arg = value.toString();
                meth.invoke(this, arg);
            }else if(propertyType.equals(Boolean.class) || propertyType.equals(Boolean.TYPE)) {
                Boolean arg = null;
                if(value instanceof Boolean) 
                    arg = (Boolean)value;
                else{
                    arg = Boolean.parseBoolean(value.toString());
                }
                meth.invoke(this, arg);
            }else if(propertyType.equals(Integer.class) || propertyType.equals(Integer.TYPE)) {
                Integer arg = null;
                if(value instanceof Integer) 
                    arg = (Integer)value;
                else{
                    arg = Integer.parseInt(value.toString());
                }
                meth.invoke(this, arg);  
            }else { // Object
                meth.invoke(this, value);
            }
        }
    }
    
    private Map<String, PropertyDescriptor> loadBean(Class theClass) throws IntrospectionException {
        HashMap<String, PropertyDescriptor> propertyMap = new HashMap<String, PropertyDescriptor>();
        BeanInfo info = Introspector.getBeanInfo(this.getClass());
        PropertyDescriptor[] properties = info.getPropertyDescriptors();
        for(int i = 0; i < properties.length; i++) {
            propertyMap.put(properties[i].getName(), properties[i]);
        }
        return propertyMap;
    }
    
    
}
