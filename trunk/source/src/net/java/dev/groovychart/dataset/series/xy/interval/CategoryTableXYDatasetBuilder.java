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
 * CategoryTableXYDatasetBuilder.java
 *
 * Created on December 7, 2006, 3:13 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.dataset.series.xy.interval;

import java.util.Map;
import net.java.dev.groovychart.dataset.BaseDatasetBuilder;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.CategoryTableXYDataset;

/**
 *
 * @author jclarke
 */
public class CategoryTableXYDatasetBuilder extends BaseDatasetBuilder {
    
    /**
     * Creates a new instance of CategoryTableXYDatasetBuilder
     */
    public CategoryTableXYDatasetBuilder() {
    }
    
    public Dataset getDataset() {
        return getXyDataset();
    }    

    /**
     * Holds value of property xyDataset.
     */
    private CategoryTableXYDataset xyDataset;

    /**
     * Getter for property xyDataset.
     * @return Value of property xyDataset.
     */
    public CategoryTableXYDataset getXyDataset() {
        return this.xyDataset;
    }

    /**
     * Setter for property xyDataset.
     * @param xyDataset New value of property xyDataset.
     */
    public void setXyDataset(CategoryTableXYDataset xyDataset) {
        this.xyDataset = xyDataset;
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
    }
}