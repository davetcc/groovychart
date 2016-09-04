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
 * DefaultWindDatasetBuilder.java
 *
 * Created on December 7, 2006, 3:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.dataset.series.xy;

import java.util.Map;
import com.thecoderscorner.groovychart.dataset.BaseDatasetBuilder;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultWindDataset;

/**
 *
 * @author jclarke
 */
public class DefaultWindDatasetBuilder extends BaseDatasetBuilder {
    
    /** Creates a new instance of DefaultWindDatasetBuilder */
    public DefaultWindDatasetBuilder() {
    }
    public Dataset getDataset() {
        return getXyDataset();
    }    

    /**
     * Holds value of property xyDataset.
     */
    private DefaultWindDataset xyDataset;

    /**
     * Getter for property xyDataset.
     * @return Value of property xyDataset.
     */
    public DefaultWindDataset getXyDataset() {
        return this.xyDataset;
    }

    /**
     * Setter for property xyDataset.
     * @param xyDataset New value of property xyDataset.
     */
    public void setXyDataset(DefaultWindDataset xyDataset) {
        this.xyDataset = xyDataset;
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        if(value != null && value instanceof DefaultWindDataset) {
            this.xyDataset = (DefaultWindDataset)value;
        }else {
            // TODO
        }          
    }
    
}
