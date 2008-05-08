import com.thecoderscorner.groovychart.chart.ChartBuilder
import groovy.swing.SwingBuilder
import java.awt.BorderLayout
import java.awt.Dimension
import org.jfree.chart.ChartPanel

ChartBuilder builder = new ChartBuilder();

def calendar = Calendar.getInstance()
calendar.set(2007,12,17,12,0)
def dates = []
for (i in 1..4) {
    dates += calendar.getTime()
    calendar.add(Calendar.DATE, 1)
}
def chart = builder.CandleStickChart(
    title:      'This is a title',
    legend: true
) {
    defaultHighLowDataSet() {
       series("Acme Shares")
       date(dates)
       high(  [100d, 110d, 90d,  80d])
       low(   [ 95d, 101d, 70d,  71d])
       open(  [ 96d, 105d, 75d,  80d])
       close( [100d, 110d, 85d,  78d])
       volume([100d, 200d, 99d, 100d])
    }
}

ChartPanel chartPanel = new ChartPanel(chart.chart, false)
chartPanel.setPreferredSize(new Dimension(1000, 500))
chartPanel.setMouseZoomable(true, false)

SwingBuilder swing = new SwingBuilder()

def frame = swing.frame(
            title:'This is a Frame',
            location:[100,100],
            size:[800,400],
            defaultCloseOperation:javax.swing.WindowConstants.EXIT_ON_CLOSE);

frame.getContentPane().setLayout(new BorderLayout()); frame.getContentPane().add( chartPanel, java.awt.BorderLayout.CENTER);
frame.setVisible(true)
