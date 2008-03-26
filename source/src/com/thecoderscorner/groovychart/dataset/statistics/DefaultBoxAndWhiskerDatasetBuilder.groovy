/*
 *
 * Copyright 2008 Tiago Antao
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
 * DefaultBoxAndWhiskerCategoryDatasetBuilder.groocy
 *
 * Created on March 26, 2008, 12:00 PM
 *
 */

package com.thecoderscorner.groovychart.dataset.statistics;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.dataset.*;
import org.jfree.data.general.Dataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

/**
 *
 * @author Tiago Antao <tiagoantao@gmail.com>
 */
public class DefaultBoxAndWhiskerCategoryDatasetBuilder extends BaseDatasetBuilder  {
    private static final Logger logger = Logger.getLogger(DefaultBoxAndWhiskerCategoryDatasetBuilder.class.getPackage().getName())

    private String seriesTitle
    private String categoryName
    private List<Double> values
    
    /** Creates a new instance of DefaultBoxAndWhiskerCategoryDatasetBuilder */
    public DefaultBoxAndWhiskerCategoryDatasetBuilder() {
    }

    public Dataset getDataset() {
        return this.bwDataset;
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        if(logger.isLoggable(Level.FINE)) {
            logger.fine("DefaultBoxAndWhiskerCategoryDatasetBuilder: " + name + ", " + map + ", " + value);
            if(value != null)
                logger.fine("Value object = " + value.getClass());
        }
        String method = name.toString();
        if(value != null && value instanceof DefaultBoxAndWhiskerCategoryDataset) {
            this.xyDataset = (DefaultBoxAndWhiskerCategoryDataset)value;
        }else if(method.equalsIgnoreCase("series")) {
            if(value == null)
                value = map.get("value")
            seriesTitle = value.toString()
            if(categoryName != null && values != null) {
                addSeriesCategory()
            }
        }else if(method.equalsIgnoreCase("category")) {
            if(value == null)
                value = map.get("value")
            categoryName = value.toString()
            if(seriesTitle != null && values != null) {
                addSeriesCategory()
            }
            
        }else if(method.equalsIgnoreCase("values")) {
            List valuesArray = (List)value;
            values = valuesArray.clone()
                /*
            Iterator it = ((List)value).iterator();
            for(int i = 0; it.hasNext(); i++) {
                values[i] = ((Double)it.next()).doubleValue();
            }
            */
            if(seriesTitle != null && categoryName != null) {
                addSeriesCategory()
            }
        }
    }  
    
    private void addSeriesCategory() {
        bwDataset.add(values, seriesTitle, categoryName)
        seriesTitle = categoryName = values = null
    }
    /**
     * Holds value of property xySet.
     */
    private DefaultBoxAndWhiskerCategoryDataset bwDataset = new DefaultBoxAndWhiskerCategoryDataset();
    


    /**
     * Getter for property bwSet.
     * @return Value of property bwSet.
     */
    public DefaultBoxAndWhiskerCategoryDataset getDefaultBoxAndWhiskerCategoryDataset() {
        return this.bwDataset;
    }

    /**
     * Setter for property bwSet.
     * @param bwSet New value of property bwSet.
     */
    public void setDefaultBoxAndWhiskerCategoryDataset(DefaultBoxAndWhiskerCategoryDataset bwDataset) {
        this.bwDataset = bwDataset
    }



 
    
}
