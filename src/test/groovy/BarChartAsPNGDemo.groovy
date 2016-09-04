import org.jfree.chart.plot.PlotOrientation
import com.thecoderscorner.groovychart.chart.ChartBuilder
import java.awt.Color
import java.awt.GradientPaint

import org.jfree.chart.axis.NumberAxis

ChartBuilder builder = new ChartBuilder();

// we don't need to store the chart, as the chart as PNG method
// knows all about the built chart.
builder.barchart(title:'This is a title',
    categoryAxisLabel:'category',
    valueAxisLabel:'value',
    orientation:PlotOrientation.VERTICAL,
    legend:true,
    tooltips:false,
    urls:false
) {
    defaultcategorydataset {
        addValue(4.0, row:"Series", column:"A")                     
        addValue(3.0, row:"Series", column:"B")
        addValue(5.0, row:"Series", column:"C")
        addValue(6.0, row:"Series", column:"D")
        addValue(10.0, row:"Series", column:"E")
        addValue(14.0, row:"Series", column:"F")
        addValue(12.0, row:"Series", column:"G")
        addValue(4.0, row:"Series", column:"H")
    }

    categoryplot {
        rangeGridlinePaint Color.GRAY
        domainGridlinePaint Color.GRAY
        foregroundAlpha 1.0f

        rangeaxis {
            upperBound 20.0
            lowerBound 2.0
            standardTickUnits NumberAxis.createIntegerTickUnits()
        }

        renderer {
            seriesPaint(0, paint: new GradientPaint(0,0, Color.BLUE, 0, 100, Color.BLUE.brighter()))
            seriesOutlinePaint(0, paint: Color.BLACK)
  //          autoPopulateSeriesOutlinePaint false
        }
    }
}

// this method call renders the built chart into a PNG to the selected output stream.
builder.chartAsPNG(new FileOutputStream('barchart.png'), 400, 300);
