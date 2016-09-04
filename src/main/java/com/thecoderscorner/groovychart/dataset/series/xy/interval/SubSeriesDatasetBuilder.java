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
 * SubSeriesDatasetBuilder.java
 *
 * Created on December 7, 2006, 3:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.dataset.series.xy.interval;

import java.util.Map;
import com.thecoderscorner.groovychart.dataset.BaseDatasetBuilder;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.SubSeriesDataset;

/**
 *
 * @author jclarke
 */
public class SubSeriesDatasetBuilder extends BaseDatasetBuilder {
    
    /** Creates a new instance of SubSeriesDatasetBuilder */
    public SubSeriesDatasetBuilder() {
    }
    public Dataset getDataset() {
        return getSubSeries();
    }    

    /**
     * Holds value of property subSeries.
     */
    private SubSeriesDataset subSeries;

    /**
     * Getter for property subSeries.
     * @return Value of property subSeries.
     */
    public SubSeriesDataset getSubSeries() {
        return this.subSeries;
    }

    /**
     * Setter for property subSeries.
     * @param subSeries New value of property subSeries.
     */
    public void setSubSeries(SubSeriesDataset subSeries) {
        this.subSeries = subSeries;
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        if(value != null && value instanceof SubSeriesDataset) {
            this.subSeries = (SubSeriesDataset)value;
        }else {
            // TODO
        }             
    }
    
}
