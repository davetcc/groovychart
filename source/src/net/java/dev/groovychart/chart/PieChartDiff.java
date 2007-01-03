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
 * PieChartDiff.java
 *
 * Created on November 11, 2006, 10:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author jclarke
 */
public class PieChartDiff extends BasicChart {
    
    /** Creates a new instance of PieChartDiff */
    public PieChartDiff() {
    }

    public JFreeChart getChart() {
         return ChartFactory.createPieChart(getTitle(),
                (PieDataset)getDataset(), getPreviousDataset(),
                 getPercentDiffForMaxScale(), isGreenForIncrease(), 
                isLegend(), isTooltips(), isUrls(),
                 isSubTitle(), isShowDifference());
    }

    /**
     * Holds value of property percentDiffForMaxScale.
     */
    private int percentDiffForMaxScale;

    /**
     * Getter for property percentDiffForMaxScale.
     * @return Value of property percentDiffForMaxScale.
     */
    public int getPercentDiffForMaxScale() {
        return this.percentDiffForMaxScale;
    }

    /**
     * Setter for property percentDiffForMaxScale.
     * @param percentDiffForMaxScale New value of property percentDiffForMaxScale.
     */
    public void setPercentDiffForMaxScale(int percentDiffForMaxScale) {
        this.percentDiffForMaxScale = percentDiffForMaxScale;
    }

    /**
     * Holds value of property greenForIncrease.
     */
    private boolean greenForIncrease;

    /**
     * Getter for property greenForIncrease.
     * @return Value of property greenForIncrease.
     */
    public boolean isGreenForIncrease() {
        return this.greenForIncrease;
    }

    /**
     * Setter for property greenForIncrease.
     * @param greenForIncrease New value of property greenForIncrease.
     */
    public void setGreenForIncrease(boolean greenForIncrease) {
        this.greenForIncrease = greenForIncrease;
    }

    /**
     * Holds value of property subTitle.
     */
    private boolean subTitle;

    /**
     * Getter for property subTitle.
     * @return Value of property subTitle.
     */
    public boolean isSubTitle() {
        return this.subTitle;
    }

    /**
     * Setter for property subTitle.
     * @param subTitle New value of property subTitle.
     */
    public void setSubTitle(boolean subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * Holds value of property showDifference.
     */
    private boolean showDifference;

    /**
     * Getter for property showDifference.
     * @return Value of property showDifference.
     */
    public boolean isShowDifference() {
        return this.showDifference;
    }

    /**
     * Setter for property showDifference.
     * @param showDifference New value of property showDifference.
     */
    public void setShowDifference(boolean showDifference) {
        this.showDifference = showDifference;
    }

    /**
     * Holds value of property previousDataset.
     */
    private PieDataset previousDataset;

    /**
     * Getter for property previousDataset.
     * @return Value of property previousDataset.
     */
    public PieDataset getPreviousDataset() {
        return this.previousDataset;
    }

    /**
     * Setter for property previousDataset.
     * @param previousDataset New value of property previousDataset.
     */
    public void setPreviousDataset(PieDataset previousDataset) {
        this.previousDataset = previousDataset;
    }
    
}
