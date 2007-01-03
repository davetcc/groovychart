package demo

import groovy.swing.SwingBuilder
import javax.swing.JOptionPane
import javax.swing.JSplitPane
import javax.swing.JScrollPane
import javax.swing.BorderFactory
import javax.swing.tree.*
import javax.swing.ScrollPaneConstants
import demo.DemoDescription

import java.awt.*

SwingBuilder swing = new SwingBuilder()


def frame


def border = BorderFactory.createEmptyBorder(4, 4, 4, 4)
def compoundBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(4, 4, 4, 4),
                BorderFactory.createLineBorder(Color.black)
            )

def noDemoSelectedPanel = swing.panel(preferredSize:[600, 400]) {
        flowLayout()
        label("No demo selected")
}

def chartContainer = swing.panel(border:compoundBorder) {
        borderLayout()
        panel(constraints:BorderLayout.CENTER) {
            widget(noDemoSelectedPanel)
        }
}


descriptionPane = swing.textPane(editable:false)

def descriptionContainer = swing.panel(border:border, preferredSize:[600, 140] ) {
	borderLayout()
	scrollPane(constraints:BorderLayout.CENTER, 
                verticalScrollBarPolicy:ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		horizontalScrollBarPolicy:ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER) {
		widget(descriptionPane)
        }
}
displayDescription("select.html")

def displayPanel = swing.panel() {
     borderLayout()
     splitPane(constraints:BorderLayout.CENTER, orientation:JSplitPane.VERTICAL_SPLIT, dividerLocation:-1,
         topComponent:widget(chartContainer),
         bottomComponent:widget(descriptionContainer))
}


def exportToPDF = {System.out.println("Export")}

def attemptExit = {
	def title = 'Confirm'
        def message = 'Are you sure you want to exit the demo?'
        def result = JOptionPane.showConfirmDialog(
            frame, message, title, JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        )
        if (result == JOptionPane.YES_OPTION) {
            frame.dispose();
            System.exit(0);
        }

}


def demoTree = swing.tree(model:createTreeModel())

demoTree.valueChanged = { event ->
  def node = (DefaultMutableTreeNode) demoTree.getLastSelectedPathComponent();
  if (node != null) {
    System.out.println(node.getUserObject());
  }
}


def treePanel = swing.scrollPane() {
    widget(demoTree)
}

def memoryUsage = swing.panel() // need to duplicate MemoryUsage chart


frame = swing.frame(
    title:'groovychart: JFreeChart 1.0.2 Demo Collection',
    defaultCloseOperation:javax.swing.WindowConstants.EXIT_ON_CLOSE
    ){
	menuBar(){
		menu('File',mnemonic:'F') {
                    menuItem() {
			action(name:'Export to PDF...', mnemonic:'p',closure:exportToPDF) 
                    }
                    separator()
                    menuItem() {
			action(name:'Exit',  mnemonic:'x',closure:attemptExit)
                    }
                }
        }
         panel() {
             borderLayout()
             tabbedPane(border:border) {
	         panel(name:'Demos', border:border) {
                     borderLayout()
                     splitPane(orientation:JSplitPane.HORIZONTAL_SPLIT,
			leftComponent:treePanel,
                        rightComponent:displayPanel)
                 }
                 widget(name:'Memory Usage', memoryUsage){}
             }
         }
}

frame.pack();
frame.setVisible(true)


private displayDescription(def fileName) {
        def descriptionURL = this.getClass().getResource(fileName);
        if (descriptionURL != null) {
            try {
                descriptionPane.page = descriptionURL;
            }
            catch (IOException e) {
                System.err.println(
                    "Attempted to read a bad URL: " + descriptionURL
                );
            }
        }
        else {
            System.err.println("Couldn't find file: " + fileName);
        }
}


private TreeModel createTreeModel() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("JFreeChart");
        root.add(createPieChartsNode());
        root.add(createBarChartsNode());
        root.add(createLineChartsNode());
        root.add(createAreaChartsNode());
        root.add(createTimeSeriesChartsNode());
        root.add(createFinancialChartsNode());
        root.add(createXYChartsNode());
        root.add(createMeterChartsNode());
        root.add(createMultipleAxisChartsNode());
        root.add(createCombinedAxisChartsNode());
        root.add(createGanttChartsNode());
        root.add(createMiscellaneousChartsNode());
        return new DefaultTreeModel(root);
    }
    
    /**
     * Creates the tree node for the pie chart demos.
     * 
     * @return A populated tree node.
     */
    private MutableTreeNode createPieChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Pie Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo1", 
                        "PieChartDemo1.java"));  
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo2", 
                        "PieChartDemo2.java"));            
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo3", 
                        "PieChartDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChartDemo8", 
                        "PieChartDemo8.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChart3DDemo1", 
                        "PieChart3DDemo1.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChart3DDemo2", 
                        "PieChart3DDemo2.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PieChart3DDemo3", 
                        "PieChart3DDemo3.java"));  
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MultiplePieChartDemo1", 
                        "MultiplePieChartDemo1.java"));
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
            new DemoDescription("demo.RingChartDemo1", "RingChartDemo1.java"));
                
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        return root;
    }
    
    /**
     * Creates a tree node containing sample bar charts.
     * 
     * @return The tree node.
     */
    private MutableTreeNode createBarChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Bar Charts");            
        root.add(createCategoryBarChartsNode());
        root.add(createXYBarChartsNode());
        return root;        
    }
    
    /**
     * Creates a tree node containing bar charts based on the 
     * {@link CategoryPlot} class.
     * 
     * @return The tree node.
     */
    def createCategoryBarChartsNode() {
        root = new DefaultMutableTreeNode("CategoryPlot");
        
        n1 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo1", "BarChartDemo1.java"));                
        n2 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo2", "BarChartDemo2.java"));                
        n3 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo3", "BarChartDemo3.java"));                
        n4 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo4", "BarChartDemo4.java"));                
        n5 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo5", "BarChartDemo5.java"));                
        n6 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo6", "BarChartDemo6.java"));                
        n7 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo7", "BarChartDemo7.java"));                
        n8 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo8", "BarChartDemo8.java"));                
        n9 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo9", "BarChartDemo9.java"));                
        n10 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChartDemo10", "BarChartDemo10.java"));                
        n11 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChart3DDemo1", "BarChart3DDemo1.java"));                
        n12 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChart3DDemo2", "BarChart3DDemo2.java"));                
        n13 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChart3DDemo3", "BarChart3DDemo3.java"));
        n13a = new DefaultMutableTreeNode(new DemoDescription(
                "demo.BarChart3DDemo4", "BarChart3DDemo4.java"));
        n13b = new DefaultMutableTreeNode(new DemoDescription(
                "demo.CylinderChartDemo1", "CylinderChartDemo1.java"));
        n14 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.IntervalBarChartDemo1", "IntervalBarChartDemo1.java"));
        n15 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.LayeredBarChartDemo1", "LayeredBarChartDemo1.java"));
        n16 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.LayeredBarChartDemo2", "LayeredBarChartDemo2.java"));
        n17 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo1", "StackedBarChartDemo1.java"));
        n18 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo2", "StackedBarChartDemo2.java"));
        n19 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo3", "StackedBarChartDemo3.java"));
        n20 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo4", "StackedBarChartDemo4.java"));
        n21 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo5", "StackedBarChartDemo5.java"));
        n22 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StackedBarChartDemo6", "StackedBarChartDemo6.java"));
        n23 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.StatisticalBarChartDemo1", 
                "StatisticalBarChartDemo1.java"));
        n24 = new DefaultMutableTreeNode(new DemoDescription(
                "demo.WaterfallChartDemo1", "WaterfallChartDemo1.java"));
            
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        root.add(n12);
        root.add(n13);
        root.add(n13a);
        root.add(n13b);
        root.add(n14);
        root.add(n15);
        root.add(n16);
        root.add(n17);
        root.add(n18);
        root.add(n19);
        root.add(n20);
        root.add(n21);
        root.add(n22);
        root.add(n23);
        root.add(n24);
        
        return root;        
    }
    
    private MutableTreeNode createXYBarChartsNode() {
        root = new DefaultMutableTreeNode("XYPlot");
        n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.HistogramDemo1", 
                        "HistogramDemo1.java"));
        n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBarChartDemo1", 
                        "XYBarChartDemo1.java"));                
        n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBarChartDemo2", 
                        "XYBarChartDemo2.java"));                
        n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBarChartDemo3", 
                        "XYBarChartDemo3.java"));                
        n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBarChartDemo4", 
                        "XYBarChartDemo4.java"));                
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        return root;
    }
    
    /**
     * Creates a tree node containing line chart items.
     * 
     * @return A tree node.
     */
    private MutableTreeNode createLineChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Line Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.AnnotationDemo1", 
                "AnnotationDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo1", 
                "LineChartDemo1.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo2", 
                "LineChartDemo2.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo3", 
                "LineChartDemo3.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo4", 
                "LineChartDemo4.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo5", 
                "LineChartDemo5.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo6", 
                "LineChartDemo6.java"));
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo7", 
                "LineChartDemo7.java"));
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LineChartDemo8", 
                "LineChartDemo8.java"));
        DefaultMutableTreeNode n10 = new DefaultMutableTreeNode(
                new DemoDescription("demo.NormalDistributionDemo", 
                "NormalDistributionDemo.java"));
        DefaultMutableTreeNode n11 = new DefaultMutableTreeNode(
                new DemoDescription("demo.StatisticalLineChartDemo1", 
                "StatisticalLineChartDemo1.java"));
        DefaultMutableTreeNode n12 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYStepRendererDemo1", 
                "XYStepRendererDemo1.java"));
        DefaultMutableTreeNode n13 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYStepRendererDemo2", 
                "XYStepRendererDemo2.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        root.add(n12);
        root.add(n13);
        
        return root;
    }
    
    /**
     * A node for various area charts.
     * 
     * @return The node.
     */
    private MutableTreeNode createAreaChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Area Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription("demo.AreaChartDemo1", "AreaChartDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription("demo.StackedXYAreaChartDemo1", 
                "StackedXYAreaChartDemo1.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
            new DemoDescription("demo.StackedXYAreaChartDemo2", 
                "StackedXYAreaChartDemo2.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
            new DemoDescription("demo.XYAreaChartDemo1", 
                "XYAreaChartDemo1.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
            new DemoDescription("demo.XYAreaChartDemo2", 
                "XYAreaChartDemo2.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        
        return root;
    }
    
    /**
     * Creates a sub-tree for the time series charts.
     * 
     * @return The root node for the subtree.
     */
    private MutableTreeNode createTimeSeriesChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
            "Time Series Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo1", "TimeSeriesDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo2", "TimeSeriesDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo3", "TimeSeriesDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo4", "TimeSeriesDemo4.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo5", "TimeSeriesDemo5.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo6", "TimeSeriesDemo6.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo7", "TimeSeriesDemo7.java"));
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo8", "TimeSeriesDemo8.java"));
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo9", "TimeSeriesDemo9.java"));
        DefaultMutableTreeNode n10 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo10", 
                    "TimeSeriesDemo10.java"));
        DefaultMutableTreeNode n11 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo11", 
                    "TimeSeriesDemo11.java"));
        DefaultMutableTreeNode n12 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo12", 
                    "TimeSeriesDemo12.java"));
        DefaultMutableTreeNode n13 = new DefaultMutableTreeNode(
            new DemoDescription("demo.TimeSeriesDemo13", 
                    "TimeSeriesDemo13.java"));
        DefaultMutableTreeNode n14 = new DefaultMutableTreeNode(
            new DemoDescription("demo.PeriodAxisDemo1", "PeriodAxisDemo1.java"));
        DefaultMutableTreeNode n15 = new DefaultMutableTreeNode(
            new DemoDescription("demo.PeriodAxisDemo2", "PeriodAxisDemo2.java"));
        DefaultMutableTreeNode n16 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DynamicDataDemo1", 
                "DynamicDataDemo1.java"));
        DefaultMutableTreeNode n17 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DynamicDataDemo2", 
                "DynamicDataDemo2.java"));
        DefaultMutableTreeNode n18 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DynamicDataDemo3", 
                "DynamicDataDemo3.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        root.add(n12);
        root.add(n13);
        root.add(n14);
        root.add(n15);
        root.add(n16);
        root.add(n17);
        root.add(n18);
        
        return root;
    }
    
    /**
     * Creates a node for the tree model that contains financial charts.
     * 
     * @return The tree node.
     */
    private MutableTreeNode createFinancialChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Financial Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CandlestickChartDemo1", 
                "CandlestickChartDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.HighLowChartDemo1", 
                "HighLowChartDemo1.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.HighLowChartDemo2", 
                "HighLowChartDemo2.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PriceVolumeDemo1", 
                "PriceVolumeDemo1.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.YieldCurveDemo", 
                "YieldCurveDemo.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        return root;
    }

    private MutableTreeNode createXYChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("XY Charts");
        
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ScatterPlotDemo1", 
                "ScatterPlotDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ScatterPlotDemo2", 
                "ScatterPlotDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ScatterPlotDemo3", 
                "ScatterPlotDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYLineAndShapeRendererDemo1", 
                "XYLineAndShapeRendererDemo1.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYSeriesDemo1", 
                "XYSeriesDemo1.java"));                
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYSeriesDemo2", 
                "XYSeriesDemo2.java"));                
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYSeriesDemo3", 
                "XYSeriesDemo3.java"));        
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.WindChartDemo1", 
                "WindChartDemo1.java"));                
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        
        return root;
    }

    /**
     * Creates a node for the tree model that contains "meter" charts.
     * 
     * @return The tree node.
     */
    private MutableTreeNode createMeterChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Meter Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MeterChartDemo1", 
                "MeterChartDemo1.java"));
        
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MeterChartDemo2", 
                "MeterChartDemo2.java"));
        
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MeterChartDemo4", 
                "MeterChartDemo4.java"));
        
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ThermometerDemo1", 
                "ThermometerDemo1.java"));
       
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        return root;
    }

    private MutableTreeNode createMultipleAxisChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Multiple Axis Charts");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DualAxisDemo1", 
                        "DualAxisDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DualAxisDemo2", 
                        "DualAxisDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DualAxisDemo3", 
                        "DualAxisDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DualAxisDemo4", 
                        "DualAxisDemo4.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DualAxisDemo5", 
                        "DualAxisDemo5.java"));
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.MultipleAxisDemo1", 
                        "MultipleAxisDemo1.java"));
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ParetoChartDemo1", 
                        "ParetoChartDemo1.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        
        return root;
    }
    
    private MutableTreeNode createCombinedAxisChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
            "Combined Axis Charts"
        );

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedCategoryPlotDemo1", 
                "CombinedCategoryPlotDemo1.java"
            )
        );
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedCategoryPlotDemo2", 
                "CombinedCategoryPlotDemo2.java"
            )
        );
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedTimeSeriesDemo1", 
                "CombinedTimeSeriesDemo1.java"
            )
        );
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedXYPlotDemo1", 
                "CombinedXYPlotDemo1.java"
            )
        );
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedXYPlotDemo2", 
                "CombinedXYPlotDemo2.java"
            )
        );
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedXYPlotDemo3", 
                "CombinedXYPlotDemo3.java"
            )
        );
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.CombinedXYPlotDemo4", 
                "CombinedXYPlotDemo4.java"
            )
        );

        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        
        return root;
    }

    private MutableTreeNode createGanttChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
            "Gantt Charts"
        );

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription("demo.GanttDemo1", "GanttDemo1.java")
        );
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription("demo.GanttDemo2", "GanttDemo2.java")
        );
        
        root.add(n1);
        root.add(n2);
        
        return root;
    }
    
    /**
     * Creates the subtree containing the miscellaneous chart types.
     * 
     * @return A subtree.
     */
    private MutableTreeNode createMiscellaneousChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
                "Miscellaneous");

        DefaultMutableTreeNode n0 = new DefaultMutableTreeNode(
                new DemoDescription("demo.BoxAndWhiskerChartDemo1", 
                "BoxAndWhiskerChartDemo1.java"));

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.BubbleChartDemo1", 
                "BubbleChartDemo1.java"));
        
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.BubbleChartDemo2", 
                "BubbleChartDemo2.java"));

        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CategoryStepChartDemo1", 
                "CategoryStepChartDemo1.java"));
        
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CompassDemo1", "CompassDemo1.java"));
        
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CompassFormatDemo1", 
                "CompassFormatDemo1.java"));
        
        DefaultMutableTreeNode n6 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CompassFormatDemo2", 
                "CompassFormatDemo2.java"));
        
        DefaultMutableTreeNode n7 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DifferenceChartDemo1", 
                "DifferenceChartDemo1.java"));
        
        DefaultMutableTreeNode n8 = new DefaultMutableTreeNode(
                new DemoDescription("demo.DifferenceChartDemo2", 
                "DifferenceChartDemo2.java"));
        
        DefaultMutableTreeNode n9 = new DefaultMutableTreeNode(
                new DemoDescription("demo.EventFrequencyDemo1", 
                "EventFrequencyDemo1.java"));

        DefaultMutableTreeNode n10 = new DefaultMutableTreeNode(
                new DemoDescription("demo.HideSeriesDemo1", 
                "HideSeriesDemo1.java"));
        
        DefaultMutableTreeNode n11 = new DefaultMutableTreeNode(
                new DemoDescription("demo.OverlaidBarChartDemo1", 
                "OverlaidBarChartDemo1.java"));
        
        DefaultMutableTreeNode n12 = new DefaultMutableTreeNode(
                new DemoDescription("demo.OverlaidBarChartDemo2", 
                "OverlaidBarChartDemo2.java"));
        
        DefaultMutableTreeNode n13 = new DefaultMutableTreeNode(
                new DemoDescription("demo.PolarChartDemo1", 
                "PolarChartDemo1.java"));

        DefaultMutableTreeNode n14 = new DefaultMutableTreeNode(
                new DemoDescription("demo.SpiderWebChartDemo1", 
                "SpiderWebChartDemo1.java"));
        
        DefaultMutableTreeNode n15 = new DefaultMutableTreeNode(
                new DemoDescription("demo.SymbolAxisDemo1", 
                "SymbolAxisDemo1.java"));
        
        DefaultMutableTreeNode n16 = new DefaultMutableTreeNode(
                new DemoDescription("demo.YIntervalChartDemo1", 
                "YIntervalChartDemo1.java"));

        root.add(createAnnotationsNode());
        root.add(createCrosshairChartsNode());
        root.add(createItemLabelsNode());
        root.add(createLegendNode());
        root.add(createMarkersNode());
        root.add(createOrientationNode());
        root.add(n0);
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        root.add(n6);
        root.add(n7);
        root.add(n8);
        root.add(n9);
        root.add(n10);
        root.add(n11);
        root.add(n12);
        root.add(n13);
        root.add(n14);
        root.add(n15);
        root.add(n16);
        
        return root;
    }
    
    private MutableTreeNode createAnnotationsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Annotations");

        DefaultMutableTreeNode n0 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYBoxAnnotationDemo1", 
                "XYBoxAnnotationDemo1.java"));
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.XYPolygonAnnotationDemo1", 
                "XYPolygonAnnotationDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.AnnotationDemo2", 
                "AnnotationDemo2.java"));
        
        root.add(n0);
        root.add(n1);
        root.add(n2);
        return root;
    }

    private MutableTreeNode createCrosshairChartsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Crosshairs");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CrosshairDemo1", 
                "CrosshairDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CrosshairDemo2", 
                "CrosshairDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CrosshairDemo3", 
                "CrosshairDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.CrosshairDemo4", 
                "CrosshairDemo4.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        
        return root;
    }
    
    private MutableTreeNode createItemLabelsNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Item Labels");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ItemLabelDemo1", 
                "ItemLabelDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ItemLabelDemo2", 
                "ItemLabelDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ItemLabelDemo3", 
                "ItemLabelDemo3.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ItemLabelDemo4", 
                "ItemLabelDemo4.java"));
        DefaultMutableTreeNode n5 = new DefaultMutableTreeNode(
                new DemoDescription("demo.ItemLabelDemo5", 
                "ItemLabelDemo5.java"));
        
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        root.add(n5);
        
        return root;
    }
    
    private MutableTreeNode createLegendNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Legends");

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
                new DemoDescription("demo.LegendWrapperDemo1", 
                        "LegendWrapperDemo1.java"));
        
        root.add(n1);
        
        return root;
    }
    
    private MutableTreeNode createMarkersNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Markers");
        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription("demo.CategoryMarkerDemo1", 
            "CategoryMarkerDemo1.java"));
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription("demo.CategoryMarkerDemo2", 
            "CategoryMarkerDemo2.java"));
        DefaultMutableTreeNode n3 = new DefaultMutableTreeNode(
            new DemoDescription("demo.MarkerDemo1", "MarkerDemo1.java"));
        DefaultMutableTreeNode n4 = new DefaultMutableTreeNode(
            new DemoDescription("demo.MarkerDemo2", "MarkerDemo2.java"));
        root.add(n1);
        root.add(n2);
        root.add(n3);
        root.add(n4);
        return root;
    }
    
    private MutableTreeNode createOrientationNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(
            "Plot Orientation"
        );

        DefaultMutableTreeNode n1 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.PlotOrientationDemo1", "PlotOrientationDemo1.java"
            )
        );
        DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(
            new DemoDescription(
                "demo.PlotOrientationDemo2", "PlotOrientationDemo2.java"
            )
        );
       
        root.add(n1);
        root.add(n2);
        
        return root;
    }
    
