import com.thecoderscorner.groovychart.chart.ChartBuilder
import groovy.swing.SwingBuilder
import java.awt.BorderLayout
import java.awt.Dimension
import org.jfree.chart.ChartPanel
import org.jfree.data.time.Day

java.sql.Connection conn = null

ChartBuilder builder = new ChartBuilder();

def chart = builder.timeserieschart(title:'This is a title',
    timeAxisLabel:'date',
    valueAxisLabel:'seconds',
    legend:true,
    tooltips:false,
    urls:false
) {
    timeSeriesCollection {
        timeSeries(name:'trades', timePeriodClass:'org.jfree.data.time.Day') {
            add(period:new Day(1,1,2007), value:181.8)
            add(period:new Day(1,2,2007), value:167.3)
            add(period:new Day(1,3,2007), value:200)
        }
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
