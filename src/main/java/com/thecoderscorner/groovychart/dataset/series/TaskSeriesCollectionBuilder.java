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
 * TaskSeriesCollectionBuilder.java
 *
 * Created on December 7, 2006, 3:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.dataset.series;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import com.thecoderscorner.groovychart.dataset.BaseDatasetBuilder;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.Dataset;

/**
 *
 *
 *  taskSeriesCollection() {
 *     taskSeries('Scheduled') {
 *         task('Write Proposal',start:'2006/04/01',end:'2006/04/01')
 *         task('Obtain Approval',start:java.util.Date,end:java.util.Date))
 *         task('Requirements Analysis',start:java.util.Calendar,end:java.util.Calendar))
 *     }
 *     taskSereies('Actual') {
 *     }
 *  }
 * @author jclarke
 */
public class TaskSeriesCollectionBuilder extends BaseDatasetBuilder {
    
    private TaskSeries currentSeries;
    
    /** Creates a new instance of TaskSeriesCollectionBuilder */
    public TaskSeriesCollectionBuilder() {
    }
    
    public Dataset getDataset() {
        return getCollection();
    }    

    /**
     * Holds value of property collection.
     */
    private TaskSeriesCollection collection = new TaskSeriesCollection();

    /**
     * Getter for property collection.
     * @return Value of property collection.
     */
    public TaskSeriesCollection getCollection() {
        return this.collection;
    }

    /**
     * Setter for property collection.
     * @param collection New value of property collection.
     */
    public void setCollection(TaskSeriesCollection collection) {
        this.collection = collection;
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
        
        String method = name.toString();
        
        if(value != null && value instanceof TaskSeriesCollection) {
            this.collection = (TaskSeriesCollection)value;
        } else if(method.equalsIgnoreCase("taskSeries")) {
            currentSeries = new TaskSeries(value.toString());
        }else if(method.equalsIgnoreCase("task")) {
            String description = null;
            if(value != null)
                description = value.toString();
            else
                description = (String)map.get("description");
            Date start = null;
            Date end = null;
            Object tmp = map.get("start");
            if(tmp instanceof Date)
                start = (Date)tmp;
            else if(tmp instanceof Calendar)
                start = ((Calendar)tmp).getTime();
            else{ 
                String str = tmp.toString();
                DateFormat df = DateFormat.getDateInstance();
                start = df.parse(str);
            }
            tmp = map.get("end");
            Task task = new Task(description, start, end);
        }
    }
}
