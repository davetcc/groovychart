import net.java.dev.groovychart.chart.ChartBuilder
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.JFreeChart
import org.jfree.chart.ChartPanel;
import java.awt.BorderLayout
import groovy.swing.SwingBuilder;
import java.awt.Dimension
import org.jfree.data.time.Day
import java.awt.GradientPaint
import java.awt.Color
import org.jfree.ui.RectangleInsets



java.sql.Connection conn = null

ChartBuilder builder = new ChartBuilder();

def series = builder.timeSeries(name:'trades', timePeriodClass:'org.jfree.data.time.Day') {
            add(period:new Day(1,1,2007), value:181.8)
            add(period:new Day(1,2,2007), value:167.3)
            add(period:new Day(1,3,2007), value:200)
        }
 
println("Series DONE ****************************************************************")
def chart = builder.timeserieschart(title:'This is a title', 
    timeAxisLabel:'date',
    valueAxisLabel:'seconds',
    legend:true,
    tooltips:false,
    urls:false
) {
    timeSeriesCollection() {
        timeSeries(series.timeSeries)
    }

}

def chartPanel = new ChartPanel(chart.chart, false);
chartPanel.setPreferredSize(new Dimension(1000, 500));
chartPanel.setMouseZoomable(true, false);

SwingBuilder swing = new SwingBuilder();

def frame = swing.frame(
            title:'This is a Frame',
            location:[100,100],
            size:[800,400],
            defaultCloseOperation:javax.swing.WindowConstants.EXIT_ON_CLOSE);

frame.getContentPane().setLayout(new BorderLayout());
frame.getContentPane().add( chartPanel, java.awt.BorderLayout.CENTER);
frame.setVisible(true)
