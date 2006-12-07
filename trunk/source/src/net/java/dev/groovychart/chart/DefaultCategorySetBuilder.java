/*
 * DefaultCategorySetBuilder.java
 *
 * Created on November 12, 2006, 12:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

/**
 *
 * @author jclarke
 */
public class DefaultCategorySetBuilder extends BaseDatasetBuilder  {
    
    /** Creates a new instance of DefaultCategorySetBuilder */
    public DefaultCategorySetBuilder() {
    }

    public Dataset getDataset() {
        return this.categoryDataset;
    }
    public void processNode(Object name, Map map, Object value) throws Exception {
        System.out.println("DefaultCategorySetBuilder: " + name + ", " + map + ", " + value);
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
        }
    }    
    /**
     * Holds value of property categorySet.
     */
    private DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
    


    /**
     * Getter for property categorySet.
     * @return Value of property categorySet.
     */
    public DefaultCategoryDataset getDefaultCategoryDataset() {
        return this.categoryDataset;
    }

    /**
     * Setter for property categorySet.
     * @param categorySet New value of property categorySet.
     */
    public void setDefaultCategoryDataset(DefaultCategoryDataset categoryDataset) {
        this.categoryDataset = categoryDataset;
    }



 
    
}
