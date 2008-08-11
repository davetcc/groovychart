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
 * DefaultXYSetBuilder.java
 *
 * Created on November 12, 2006, 12:00 PM
 *
 */

package com.thecoderscorner.groovychart.dataset.series.xy;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.dataset.*;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultXYDataset;

/**
 *
 * @author jclarke
 */
public class DefaultXYDatasetBuilder extends BaseDatasetBuilder  {
    private static final Logger logger = Logger.getLogger(DefaultXYDatasetBuilder.class.getPackage().getName());

    private String seriesTitle;
    private double[] x;
    private double[] y;
    
    /** Creates a new instance of DefaultXYSetBuilder */
    public DefaultXYDatasetBuilder() {
    }

    public Dataset getDataset() {
        return this.xyDataset;
    }
    public void processNode(Object name, Map map, Object value) throws Exception {
        if(logger.isLoggable(Level.FINE)) {
            logger.fine("DefaultXYDatasetBuilder: " + name + ", " + map + ", " + value);
            if(value != null)
                logger.fine("Value object = " + value.getClass());
        }
        String method = name.toString();
        if(value != null && value instanceof DefaultXYDataset) {
            this.xyDataset = (DefaultXYDataset)value;
        }else if(method.equalsIgnoreCase("series")) {
            if(seriesTitle != null) {

                
            }
            if(value == null)
                value = map.get("name");
            seriesTitle = value.toString();
        }else if(method.equalsIgnoreCase("X")) {
            List xArray = (List)value;
            x = new double[xArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                x[i] = ((Number)it.next()).doubleValue();
            }
            if(seriesTitle != null && y != null) {
                addSeries();
            }
            
        }else if(method.equalsIgnoreCase("Y")) {
            List yArray = (List)value;
            y = new double[yArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                y[i] = ((Number)it.next()).doubleValue();
            }
            if(seriesTitle != null && x != null) {
                addSeries();
            }
        }
    }  
    
    private void addSeries() {
        double[][] data = {x, y };
        xyDataset.addSeries(seriesTitle, data);
        seriesTitle = null;
        x = y = null;
    }
    /**
     * Holds value of property xySet.
     */
    private DefaultXYDataset xyDataset = new DefaultXYDataset();
    


    /**
     * Getter for property xySet.
     * @return Value of property xySet.
     */
    public DefaultXYDataset getDefaultXYDataset() {
        return this.xyDataset;
    }

    /**
     * Setter for property xySet.
     * @param xySet New value of property xySet.
     */
    public void setDefaultXYDataset(DefaultXYDataset xyDataset) {
        this.xyDataset = xyDataset;
    }



 
    
}
