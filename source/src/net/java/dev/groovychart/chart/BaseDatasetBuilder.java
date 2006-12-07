/*
 * BaseDatasetBuilder.java
 *
 * Created on November 12, 2006, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import java.util.Map;

/**
 *
 * @author jclarke
 */
public abstract class BaseDatasetBuilder implements Buildable, DatasetBuildable {
    
    private ChartBuilder chartBuilder;
    private Buildable parent;
    private String name;
    
    /** Creates a new instance of BaseDatasetBuilder */
    public BaseDatasetBuilder() {
    }

    public void setChartBuilder(ChartBuilder chartBuilder) {
        this.chartBuilder = chartBuilder;
    }


    public Buildable getParent() {
        return this.parent;
    }

    public void setParent(Buildable parent) {
        this.parent = parent;
        if(parent instanceof Chartable) {
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
