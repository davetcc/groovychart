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
 * JDBCCategoryDatasetBuilder.java
 *
 * Created on December 6, 2006, 2:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.dataset.category;

import java.sql.Connection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thecoderscorner.groovychart.dataset.*;
import org.jfree.data.general.Dataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author jclarke
 */
public class JDBCCategoryDatasetBuilder extends BaseDatasetBuilder {
    private static final Logger logger = Logger.getLogger(JDBCCategoryDatasetBuilder.class.getPackage().getName());
    private Connection connection;
    private String url;
    private String driverName;
    private String user;
    private String password;
    private String query;
    private boolean transpose = false;
    
    /** Creates a new instance of JDBCCategoryDatasetBuilder */
    public JDBCCategoryDatasetBuilder() {
    }
    
    public Dataset getDataset() {
        if(logger.isLoggable(Level.FINEST)) {
            logger.finest("JDBCCategoryDatasetBuilder: getDataset()");
        }
        return getJDBCCategoryDataset();
    }
    
    public void processNode(Object name, Map map, Object value) throws Exception {
        if(logger.isLoggable(Level.FINEST)) {
            logger.finest("JDBCCategoryDatasetBuilder: method=" + name + ", " + map + ", " + value);
        }
        if(value != null) {
            this.categoryDataset = (JDBCCategoryDataset)value;
        }else {
            String method = name.toString();
            String tmp = (String)map.get("user");
            if(tmp != null)
                this.user = tmp;
            tmp = (String)map.get("passwd");
            if(tmp != null)
                this.password = tmp;
            tmp = (String)map.get("url");
            if(tmp != null)
                this.url = tmp;
            tmp = (String)map.get("driverName");
            if(tmp != null)
                this.driverName = tmp;  
            tmp = (String)map.get("query");
            if(tmp != null)
                this.query = tmp; 
            Boolean bTmp = (Boolean)map.get("transpose");
            if(bTmp)
                this.transpose = bTmp.booleanValue();

            connection = (Connection)map.get("conn");

            if(method.equalsIgnoreCase("query")) {
                if(logger.isLoggable(Level.FINEST)) {
                    logger.finest("Query: " + value.toString());
                }
                this.query = value.toString();
                if(this.categoryDataset != null) {
                    this.categoryDataset.executeQuery(query);
                }
            }
        }
    }    
    /**
     * Holds value of property categorySet.
     */
    private JDBCCategoryDataset categoryDataset = null;
    


    /**
     * Getter for property categorySet.
     * @return Value of property categorySet.
     */
    public JDBCCategoryDataset getJDBCCategoryDataset() {
        if(this.categoryDataset == null) {
            try {
                if(connection != null)
                    this.categoryDataset = new JDBCCategoryDataset(connection);
                else 
                    this.categoryDataset = new JDBCCategoryDataset(url, driverName, user, password);
                this.categoryDataset.setTranspose(this.transpose);
                if(query != null)
                    this.categoryDataset.executeQuery(query);
            } catch (Throwable ex) {
                logger.log(Level.WARNING, ex.getMessage(), ex);
            }
            if(logger.isLoggable(Level.FINEST)) {
                logger.finest("JDBCCategoryDatasetBuilder: get dataset()");
            }
        }
        if(logger.isLoggable(Level.FINEST)) {
            logger.finest ("Dataset = " + categoryDataset);
        }
        return this.categoryDataset;
    }

    /**
     * Setter for property categorySet.
     * @param categorySet New value of property categorySet.
     */
    public void setJDBCCategoryDataset(JDBCCategoryDataset categoryDataset) {
        this.categoryDataset = categoryDataset;
    }
    
    
}
