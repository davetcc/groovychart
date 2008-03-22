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
 * DefaultXYZDatasetBuilder.java
 *
 * Created on December 7, 2006, 3:19 PM
 *
 */

package com.thecoderscorner.groovychart.dataset.series.xyz;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.dataset.BaseDatasetBuilder;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultXYZDataset;

/**
 *
 * @author jclarke
 * @author Tiago Antao <tiagoantao@gmail.com>
 */
public class DefaultXYZDatasetBuilder extends BaseDatasetBuilder {
    private static final Logger logger = Logger.getLogger(DefaultXYZDatasetBuilder.class.getPackage().getName());

    private String seriesTitle;
    private double[] x;
    private double[] y;
    private double[] z;
    
    /**
     * Creates a new instance of DefaultXYZDatasetBuilder
     */
    public DefaultXYZDatasetBuilder() {
    }
    
    public Dataset getDataset() {
        return getXyzDataset();
    }    

    /**
     * Holds value of property xyzDataset.
     */
    private DefaultXYZDataset xyzDataset;

    /**
     * Getter for property xyzDataset.
     * @return Value of property xyzDataset.
     */
    public DefaultXYZDataset getXyzDataset() {
        return this.xyzDataset;
    }

    /**
     * Setter for property xyzDataset.
     * @param xyzDataset New value of property xyzDataset.
     */
    public void setXyzDataset(DefaultXYZDataset xyzDataset) {
        this.xyzDataset = xyzDataset;
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        if(logger.isLoggable(Level.FINE)) {
            logger.fine("DefaultXYZDatasetBuilder: " + name + ", " + map + ", " + value);
            if(value != null)
                logger.fine("Value object = " + value.getClass());
        }
        // Done as in the DefaultXYDatasetBuilder case to maintain consistency
        String method = name.toString();
        if(value != null && value instanceof DefaultXYZDataset) {
            this.xyzDataset = (DefaultXYZDataset)value;
        }else if(method.equalsIgnoreCase("series")) {

            if(seriesTitle != null) {

                
            }
            if(value == null)
                value = map.get("value");
            seriesTitle = value.toString();
        }else if(method.equalsIgnoreCase("X")) {
            List xArray = (List)value;
            x = new double[xArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                x[i] = ((Double)it.next()).doubleValue();
            }
            if(seriesTitle != null && y != null && z != null) {
                addSeries();
            }
            
        }else if(method.equalsIgnoreCase("Y")) {
            List yArray = (List)value;
            y = new double[yArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                y[i] = ((Double)it.next()).doubleValue();
            }
            if(seriesTitle != null && x != null && z != null) {
                addSeries();
            }
        }else if(method.equalsIgnoreCase("Z")) {
            List zArray = (List)value;
            z = new double[zArray.size()];
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                z[i] = ((Double)it.next()).doubleValue();
            }
            if(seriesTitle != null && x != null && y != null) {
                addSeries();
            }
        }
    }

    private void addSeries() {
        double[][] data = {x, y, z};
        xyzDataset.addSeries(seriesTitle, data);
        seriesTitle = null;
        x = y = z = null;
    }

}
