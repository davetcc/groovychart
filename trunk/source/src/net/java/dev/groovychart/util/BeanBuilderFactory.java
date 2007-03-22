/*
 * BeanBuilderFactory.java
 *
 * Created on March 20, 2007, 8:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.util;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Map;
import net.java.dev.groovychart.chart.BeanBuilder;

/**
 * Class to map arbritary builder methods to a Java Bean
 * @author jclarke
 */
public class BeanBuilderFactory {
    
    private static Map<String,Class> map = new HashMap<String,Class>();
    
    static {
        
        
    }
    
    public BeanBuilder getBeanBuilder(String method) throws IntrospectionException {
        if(map.containsKey(method)) {
            return new BeanBuilder(map.get(method));
        }else {
            return null;
        }
    }
    
    public Object newInstance(String method) throws InstantiationException, IllegalAccessException {
        Class beanClass = map.get(method);
        if(beanClass != null) {
            return beanClass.newInstance();
        }else {
            return null;
        }
    }
}
