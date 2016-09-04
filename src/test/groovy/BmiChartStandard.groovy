import com.thecoderscorner.groovychart.chart.ChartBuilder
import groovy.swing.SwingBuilder
import java.awt.BorderLayout
import java.awt.Dimension
import org.jfree.chart.ChartPanel
import org.jfree.chart.plot.PlotOrientation
import java.awt.Color
import java.awt.BasicStroke
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.DatasetRenderingOrder

/*
 * Donated to the project by cal4me.com, this is for entertainment purposes only. It shows the default BMI calculation
 * over a range of weights and heights.
 */

ChartBuilder builder = new ChartBuilder();

def areachart = builder.xyareachart(title:'BMI Index Graph',
    orientation:PlotOrientation.VERTICAL,
    legend:true,
    tooltips:false,
    XAxisLabel: 'weight (Kg)',
    YAxisLabel: 'height (m)',
    urls:false
) {

    xyseries {
        for(int weight = 0; weight < 150; weight += 10) {
            point('Overweight', x: weight, y: Math.sqrt(weight / 35.0))
            point('Normal', x: weight, y: Math.sqrt(weight / 25.0))
            point('Underweight', x: weight, y: Math.sqrt(weight / 18.0))
        }
    }

    xyplot {
        datasetRenderingOrder DatasetRenderingOrder.FORWARD
        foregroundAlpha 1.0f
        rangeGridlinePaint Color.BLACK
        domainGridlinePaint Color.BLACK
        domainaxis {
            lowerBound 30
            upperBound 135
        }
        rangeaxis {
            lowerBound 0.75
        }

        renderer {
            outlinePaint(Color.BLACK)
            outlineStroke(new BasicStroke())
            seriesPaint(0, paint: new Color(0xff, 0, 0))
            seriesPaint(1, paint: new Color(255,255,50))
            seriesPaint(2, paint: new Color(0xff, 0x66, 0x33))
        }
    }
}

def chartPanel = new ChartPanel((JFreeChart)areachart.chart, false);
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

// TODO, take this into another demo.
// example using xydataset instead of series ..
//def areachart = builder.xyareachart(title:'This is a title',
//    orientation:PlotOrientation.VERTICAL,
//    legend:true,
//    tooltips:false,
//    urls:false
//) {
//    xAxisLabel 'weight'
//    yAxisLabel 'height'
//    defaultxydataset {
//        series('Normal') {
//            def xl = [], yl = []
//            for (int weight = 0; weight < 151; weight+= 10) {
//                xl << weight;
//                yl << Math.sqrt(weight / 25.0);
//            }
//            X(xl)
//            Y(yl)
//        }
//    }
//}
