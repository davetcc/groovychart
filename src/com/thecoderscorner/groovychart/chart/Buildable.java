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
 * Buildable.java
 *
 * Created on November 12, 2006, 12:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.thecoderscorner.groovychart.chart;

import java.util.Map;

/**
 *
 * @author jclarke
 */
public interface Buildable {
    
    public void setChartBuilder(ChartBuilder chartBuilder);
    
    public void processNode(Object name, Map map, Object value) throws Exception;

    
    public void nodeCompleted(Object parent);
    
    public void setParent(Object parent);

    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName();

    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name);
    
    
}
