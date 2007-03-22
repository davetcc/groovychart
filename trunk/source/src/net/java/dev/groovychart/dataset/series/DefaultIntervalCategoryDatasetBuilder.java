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
 * DefaultIntervalCategoryDatasetBuilder.java
 *
 * Created on December 7, 2006, 3:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.dataset.series.xyz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.java.dev.groovychart.dataset.BaseDatasetBuilder;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.general.Dataset;

/**
 *  DefaultIntervalCategoryDataset() {
 *      categories(['Category1', 'Category2', 'Category3'])
 *      series() {
 *          values([[0.3,0.7],[0.4,0.8],[0.5,0.9]])
 *      }
 *      series('series2') {
 *          values([['Category1',0.3,0.7],['Category2',0.4,0.8],['Category3',0.5,0.9]])
 *      }
 *  }
 *  
 * @author jclarke
 */
public class DefaultIntervalCategoryDatasetBuilder extends BaseDatasetBuilder {
    private List<String> seriesList = new ArrayList<String>();
    private List<String> categoryList = new ArrayList<String>();
    private int seriesIndex;
    
    /** Creates a new instance of DefaultIntervalCategoryDatasetBuilder */
    public DefaultIntervalCategoryDatasetBuilder() {
    }
    
    public Dataset getDataset() {
        return getIntervalCategory();
    }

    /**
     * Holds value of property intervalCategory.
     */
    private  DefaultIntervalCategoryDataset intervalCategory =
            new DefaultIntervalCategoryDataset(new Number[0][0], new Number[0][0]);

    /**
     * Getter for property series.
     * @return Value of property series.
     */
    public  DefaultIntervalCategoryDataset getIntervalCategory() {
        if(this.intervalCategory == null) {
        }
        return this.intervalCategory;
    }

    /**
     * Setter for property series.
     * @param series New value of property series.
     */
    public void setIntervalCategory( DefaultIntervalCategoryDataset intervalCategory) {
        this.intervalCategory = intervalCategory;
    }

    public void processNode(Object name, Map map, Object value) throws Exception {
       String method = name.toString();
       if(value != null && value instanceof DefaultIntervalCategoryDataset) {
           this.intervalCategory = (DefaultIntervalCategoryDataset)value;
       } else if(method.equalsIgnoreCase("categories")) {
           List valueList = (List)value;
           String[] categoryKeys = (String[])valueList.toArray(new String[valueList.size()]);
           this.intervalCategory.setCategoryKeys(categoryKeys);
           for(int i = 0; i < categoryKeys.length; i++) {
               categoryList.add(categoryKeys[i]);
           }
       }else if(method.equalsIgnoreCase("series")) {

           seriesIndex = seriesList.size();
           String title = value!=null? value.toString():
                "Series " + seriesIndex + 1;           
           seriesList.add(title);
       }else if(method.equals("values")) {
           List<List> valuesList = (List<List>)value;
           int i = 0;
           for(List category : valuesList) {
               String categoryTitle = null;
               Number start = null;
               Number end = null;
               if(category.size() == 3) {
                    categoryTitle = (String)category.get(0);
                    start = (Number)category.get(1);
                    end = (Number)category.get(2);
               }else {
                   if(categoryList.size() < i+1) {
                       categoryTitle = "Category "+(i+1);
                       categoryList.add(categoryTitle);
                   }else {
                       categoryTitle = categoryList.get(i);
                       
                   }
                   start = (Number)category.get(0);
                   end = (Number)category.get(1);
               }
               this.intervalCategory.setStartValue(this.seriesIndex, categoryTitle, start);
               this.intervalCategory.setEndValue(this.seriesIndex, categoryTitle, end);
               
           }
           
       }
    }
    
}
