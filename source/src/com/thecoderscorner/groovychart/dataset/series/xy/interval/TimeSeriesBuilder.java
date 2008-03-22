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
 * TimePeriodValuesCollectionBuilder.java
 *
 * Created on December 7, 2006, 3:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.dataset.series.xy.interval;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.chart.BeanBuilder;
import com.thecoderscorner.groovychart.chart.Buildable;
import com.thecoderscorner.groovychart.chart.ChartBuilder;
import org.jfree.data.general.Series;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;

/**
 *
 * @author jclarke
 */
public class TimeSeriesBuilder extends BeanBuilder implements Buildable {
    private static final Logger logger = Logger.getLogger(TimeSeriesBuilder.class.getPackage().getName());

    private Object parent;
    private Map beanMap;
    private Map addValue = null;
    
    /** Creates a new instance of TimePeriodValuesCollectionBuilder */
    public TimeSeriesBuilder() {
        try {
            this.setBeanClass(TimeSeries.class);
        } catch (IntrospectionException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
    
    public Series getSeries() {
        return series;
    }    
    

    /**
     * Holds value of property collection.
     */
    private TimeSeries series;

    /**
     * Getter for property collection.
     * @return Value of property collection.
     */
    public TimeSeries getTimeSeries() {

        return this.series;
    }

    /**
     * Setter for property collection.
     * @param collection New value of property collection.
     */
    public void setSeries(Series series) {
        this.series = (TimeSeries)series;
    }
    

    public void processNode(Object name, Map map, Object value) throws Exception {
        if(logger.isLoggable(Level.FINE)) {
            logger.finest("TimeSeriesBuilder: " + name + ", " + map + ", " + value);
        }
       String method  = name.toString();
       if(method.equalsIgnoreCase("timeSeries") ){
           if(value != null) {
               this.series = (TimeSeries)value;
           }else if(map != null){
               if(map.containsKey("series")) {
                   this.series = (TimeSeries)map.get(series);
               }else {
                   if(map.containsKey("name"))
                       this.name = (String)map.get("name");
                   if (map.containsKey("domain"))
                       this.domain = (String)map.get("domain");
                   if (map.containsKey("range"))
                       this.range = (String)map.get("range");
                   if(map.containsKey("timePeriodClass"))
                       this.timePeriodClass = (String)map.get("timePeriodClass");
               }
               beanMap = new HashMap(map);
               beanMap.remove("series");
               beanMap.remove("name");
               beanMap.remove("domain");
               beanMap.remove("range");
               beanMap.remove("timePeriodClass");
           }
           
       }
       else if(method.equalsIgnoreCase("add")) {
           addValue = map;
       }
    }

    public void setChartBuilder(ChartBuilder chartBuilder) {
        //ignore
    }

    
    public void setParent(Object parent) {
        this.parent = parent;

    }
    public Object getParent() {
        return parent;
    }    
    
    public void nodeCompleted(Object parent) {
        TimeSeriesCollectionBuilder tsc = null;
        try {
            if(parent != null && parent instanceof TimeSeriesCollectionBuilder)
                tsc = (TimeSeriesCollectionBuilder)parent;
            if(this.series == null) {
                this.series = new TimeSeries(name, domain, range, Class.forName(timePeriodClass) );
                this.setProperties(this.series, beanMap );
            }
            if(addValue != null) {
                   boolean notify = false;
                   if(addValue.containsKey(("notify")))
                       notify = Boolean.valueOf((String)addValue.get("notify"));
                   System.out.println("Seires " + addValue.get("period") + " = " + addValue.get("value"));
                   this.series.add((RegularTimePeriod)addValue.get("period"), ((Number)addValue.get("value")).doubleValue(), notify);
                   addValue = null;
            }
            if(tsc != null)
                tsc.addSeries(this.series);
        } catch (Throwable ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    
    private String domain;

    /**
     * Getter for property domain.
     * @return Value of property domain.
     */
    public String getDomain() {
        return this.domain;
    }

    /**
     * Setter for property domain.
     * @param domain New value of property domain.
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Holds value of property range.
     */
    private String range;

    /**
     * Getter for property range.
     * @return Value of property range.
     */
    public String getRange() {
        return this.range;
    }

    /**
     * Setter for property range.
     * @param range New value of property range.
     */
    public void setRange(String range) {
        this.range = range;
    }

    /**
     * Holds value of property timePeriodClass.
     */
    private String timePeriodClass = "org.jfree.data.time.Day";

    /**
     * Getter for property timePeriodClass.
     * @return Value of property timePeriodClass.
     */
    public String getTimePeriodClass() {
        return this.timePeriodClass;
    }

    /**
     * Setter for property timePeriodClass.
     * @param timePeriodClass New value of property timePeriodClass.
     */
    public void setTimePeriodClass(String timePeriodClass) {
        this.timePeriodClass = timePeriodClass;
    }
    
}
