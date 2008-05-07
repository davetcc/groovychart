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
 * DefaultHighLowDatasetBuilder.java
 *
 * Created on December 7, 2006, 3:07 PM
 *
 */

package com.thecoderscorner.groovychart.dataset.series.xy;

import java.util.*;
import com.thecoderscorner.groovychart.dataset.*;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultHighLowDataset;

/**
 *
 * @author jclarke
 * @author Tiago Antao <tiagoantao@gmail.com>
 */
public class DefaultHighLowDatasetBuilder extends BaseDatasetBuilder {
    private String seriesTitle;
    private Date[] date;
    private double[] high;
    private double[] low;
    private double[] open;
    private double[] close;
    private double[] volume;

    /** Creates a new instance of DefaultHighLowDatasetBuilder */
    public DefaultHighLowDatasetBuilder() {
    }
    
    public Dataset getDataset() {
        return getHlDataset();
    }    

    /**
     * Holds value of property hlDataset.
     */
    private DefaultHighLowDataset hlDataset;

    /**
     * Getter for property hlDataset.
     * @return Value of property xyDataset.
     */
    public DefaultHighLowDataset getHlDataset() {
        return this.hlDataset;
    }

    /**
     * Setter for property xyDataset.
     * @param hlDataset New value of property hlDataset.
     */
    public void setHlDataset(DefaultHighLowDataset hlDataset) {
        this.hlDataset = hlDataset;
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        String method = name.toString();

        if(value != null && value instanceof DefaultHighLowDataset) {
            this.hlDataset = (DefaultHighLowDataset)value;
         }else if(method.equalsIgnoreCase("series")) {
            if(value == null)
                value = map.get("value");
            seriesTitle = value.toString();
            doSet();
        }else if(method.equalsIgnoreCase("DATE")) {
            List dateArray = (List)value;
            date = new Date[dateArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                date[i] = (Date)it.next();
            }
            doSet();
        }else if(method.equalsIgnoreCase("HIGH")) {
            List highArray = (List)value;
            high = new double[highArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                high[i] = ((Double)it.next()).doubleValue();
            }
            doSet();
        }else if(method.equalsIgnoreCase("LOW")) {
            List lowArray = (List)value;
            low = new double[lowArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                low[i] = ((Double)it.next()).doubleValue();
            }
            doSet();
        }else if(method.equalsIgnoreCase("OPEN")) {
            List openArray = (List)value;
            open = new double[openArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                open[i] = ((Double)it.next()).doubleValue();
            }
            doSet();
        }else if(method.equalsIgnoreCase("CLOSE")) {
            List closeArray = (List)value;
            close = new double[closeArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                close[i] = ((Double)it.next()).doubleValue();
            }
            doSet();
        }else if(method.equalsIgnoreCase("VOLUME")) {
            List volumeArray = (List)value;
            volume = new double[volumeArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                volume[i] = ((Double)it.next()).doubleValue();
            }
            doSet();
        }
    }

   private void doSet() {
        if(seriesTitle != null && date != null &&
           high != null && low != null &&
           open != null && close != null &&
           volume != null) {
            hlDataset = new DefaultHighLowDataset(seriesTitle, date, high, low, open, close, volume);
            seriesTitle = null;
            date = null;
            high = low = open = close = volume = null;
        }

    }
}
