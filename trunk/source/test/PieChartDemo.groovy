import java.awt.BorderLayout as BL

import com.thecoderscorner.groovychart.chart.ChartBuilder
import groovy.swing.SwingBuilder
import java.awt.Color
import javax.swing.JFrame
import org.jfree.chart.ChartPanel

ChartBuilder cb = new ChartBuilder();
def pieChart = cb.piechart3d(title: "Simple Pie Chart") {
    defaultPieDataset {
        Series1(40.0f)
        Series2(30.0f)
        Series3(30.0f)
    }
    antiAlias = true
    backgroundPaint(Color.WHITE)
}

def sb = new SwingBuilder()
def fr = sb.frame( title : 'Simple Pie Chart', size:[600, 400], defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {
    widget(new ChartPanel(pieChart.chart), constraints: BL.CENTER)
}
fr.pack();
fr.show();
