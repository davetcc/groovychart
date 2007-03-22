/*
 * RangeAxisBuilder.java
 *
 * Created on March 20, 2007, 8:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.java.dev.groovychart.axis;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.dev.groovychart.chart.Buildable;
import net.java.dev.groovychart.plot.Plotable;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;

/**
 *
 * @author jclarke
 */
public class RangeAxisBuilder extends AxisBuilder {
    
    private static final Logger logger = Logger.getLogger(RangeAxisBuilder.class.getPackage().getName());
    
    /** Creates a new instance of RangeAxisBuilder */
    public RangeAxisBuilder() {
        super();
    }
    
    public void nodeCompleted(Object parent) {
        if(parent != null && parent instanceof Plotable) {
            if(logger.isLoggable(Level.FINEST))
                logger.finest("nodeCompleted parent: " + parent.getClass());            
            Plot plot = ((Plotable)parent).getPlot();
            if(plot instanceof CategoryPlot) {
                logger.finest("CategoryPlot");
                if(this.getIndex() != -1) {
                    ((CategoryPlot)plot).setRangeAxis(getIndex(), (ValueAxis)axis);
                }else {
                    ((CategoryPlot)plot).setRangeAxis((ValueAxis)axis);
                }
            }else if ( plot instanceof XYPlot) {
                logger.finest("XYPlot");
                if(this.getIndex() != -1) {
                    ((XYPlot)plot).setRangeAxis(getIndex(), (ValueAxis)axis);
                }else {
                    ((XYPlot)plot).setRangeAxis((ValueAxis)axis);
                }
            }else {
                logger.warning("Need to add support for Plot class: " + plot.getClass());
            }
        }
    }      
    
}
