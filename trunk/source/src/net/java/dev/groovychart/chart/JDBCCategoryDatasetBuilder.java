/*
 * JDBCCategoryDatasetBuilder.java
 *
 * Created on December 6, 2006, 2:17 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import java.sql.Connection;
import java.util.Map;
import org.jfree.data.general.Dataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author jclarke
 */
public class JDBCCategoryDatasetBuilder extends BaseDatasetBuilder {
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
        System.out.println("JDBCCategoryDatasetBuilder: getDataset()");
        return getJDBCCategoryDataset();
    }
    
    public void processNode(Object name, Map map, Object value) throws Exception {
        System.out.println("JDBCCategoryDatasetBuilder: method=" + name + ", " + map + ", " + value);
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
            System.out.println("Query: " + value.toString());
            this.query = value.toString();
            if(this.categoryDataset != null) {
                this.categoryDataset.executeQuery(query);
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
                ex.printStackTrace();
            }
            System.out.println("JDBCCategoryDatasetBuilder: get dataset()");
        }
        System.out.println ("Dataset = " + categoryDataset);
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
