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
 * TimeSeriesCollectionBuilder.java
 *
 * Created on December 7, 2006, 3:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.dataset.series.xy.interval;

import java.util.Map;
import com.thecoderscorner.groovychart.dataset.BaseDatasetBuilder;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author jclarke
 */
public class TimeSeriesCollectionBuilder extends BaseDatasetBuilder {
    
    /** Creates a new instance of TimeSeriesCollectionBuilder */
    public TimeSeriesCollectionBuilder() {
    }
    
    
    public Dataset getDataset() {
        return getCollection();
    }

    /**
     * Holds value of property collection.
     */
    private TimeSeriesCollection collection = new TimeSeriesCollection();

    /**
     * Getter for property collection.
     * @return Value of property collection.
     */
    public TimeSeriesCollection getCollection() {
        return this.collection;
    }

    /**
     * Setter for property collection.
     * @param collection New value of property collection.
     */
    public void setCollection(TimeSeriesCollection collection) {
        this.collection = collection;
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        if(value != null && value instanceof TimeSeriesCollection) {
            this.collection = (TimeSeriesCollection)value;
        }else {
            // TODO
        }          
    }
    
    public void addSeries(TimeSeries series) {
        collection.addSeries(series);
    }
    
}
