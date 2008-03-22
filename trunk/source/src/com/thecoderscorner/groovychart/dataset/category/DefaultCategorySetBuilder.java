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
 * DefaultCategorySetBuilder.java
 *
 * Created on November 12, 2006, 12:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.dataset.category;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.dataset.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

/**
 *
 * @author jclarke
 */
public class DefaultCategorySetBuilder extends BaseDatasetBuilder  {
    private static final Logger logger = Logger.getLogger(DefaultCategorySetBuilder.class.getPackage().getName());
    /** Creates a new instance of DefaultCategorySetBuilder */
    public DefaultCategorySetBuilder() {
    }

    public Dataset getDataset() {
        return this.categoryDataset;
    }
    public void processNode(Object name, Map map, Object value) throws Exception {
        if(logger.isLoggable(Level.FINEST)) {
            logger.finest("DefaultCategorySetBuilder: " + name + ", " + map + ", " + value);
        }
        String method = name.toString();
        
        if(method.equalsIgnoreCase("addValue") || method.equalsIgnoreCase("setValue")) {
            if(value == null)
                value = map.get("value");
            Number valArg = null;
            if(value != null) {
                if(value instanceof Number)
                    valArg = (Number)value;
                else {
                    valArg = new Double(value.toString());
                }
            }
            if(method.equalsIgnoreCase("addValue")) {
                categoryDataset.addValue(valArg,
                        (String)map.get("row"), (String)map.get("column"));
            }else {
                categoryDataset.setValue(valArg,
                        (String)map.get("row"), (String)map.get("column"));
            }
        }else if(value != null && value instanceof  DefaultCategoryDataset) {
            this.categoryDataset = (DefaultCategoryDataset)value;
        }
    }    

    /**
     * Holds value of property categorySet.
     */
    private DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
    


    public DefaultCategoryDataset getDefaultCategoryDataset() {
        return this.categoryDataset;
    }

    public void setDefaultCategoryDataset(DefaultCategoryDataset categoryDataset) {
        this.categoryDataset = categoryDataset;
    }



 
    
}
