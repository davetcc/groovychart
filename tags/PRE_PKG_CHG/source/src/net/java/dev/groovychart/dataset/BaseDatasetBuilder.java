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
 * BaseDatasetBuilder.java
 *
 * Created on November 12, 2006, 12:46 PM
 *
 */

package net.java.dev.groovychart.dataset;

import java.util.Map;
import net.java.dev.groovychart.chart.*;

/**
 *
 * @author jclarke
 */
public abstract class BaseDatasetBuilder implements Buildable, DatasetBuildable {
    
    private ChartBuilder chartBuilder;
    private Object parent;
    private String name;
    
    /** Creates a new instance of BaseDatasetBuilder */
    public BaseDatasetBuilder() {
    }

    public void setChartBuilder(ChartBuilder chartBuilder) {
        this.chartBuilder = chartBuilder;
    }


    public Object getParent() {
        return this.parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;

    }
    
    public void nodeCompleted(Object parent) {
        if(parent != null && parent instanceof Chartable) {
            ((Chartable)parent).setDataset(this.getDataset());
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
