import net.java.dev.groovy.chart.ChartBuilder
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.JFreeChart
import org.jfree.chart.ChartPanel;
import java.awt.BorderLayout
import groovy.swing.SwingBuilder;
import java.awt.Dimension


java.sql.Connection conn = null

ChartBuilder builder = new ChartBuilder();
 
builder.areaChart(title:'This is a title', 
    categoryAxisLabel:'category',
    valueAxisLabel:'value',
    orientation:PlotOrientation.HORIZONTAL,
    legend:true,
    tooltips:false,
    urls:false
) {
    //jdbcCategoryDataset(conn:conn, transpose:false) {
    //jdbcCategoryDataset(url:'', driverName:'', user:'scott', passwd:'tiger', transpose:false){
    //     query('select foo from bar')
    //}

    defaultCategoryDataset(){
        addValue(1.0, row:'Series 1', column:'Type 1')
        addValue(4.0, row:'Series 1', column:'Type 2')
        addValue(3.0, row:'Series 1', column:'Type 3')
        addValue(5.0, row:'Series 1', column:'Type 4')
        addValue(5.0, row:'Series 1', column:'Type 5')
        addValue(7.0, row:'Series 1', column:'Type 6')
        addValue(7.0, row:'Series 1', column:'Type 7')
        addValue(8.0, row:'Series 1', column:'Type 8')
        addValue(5.0, row:'Series 2', column:'Type 1')
        addValue(7.0, row:'Series 2', column:'Type 2')
        addValue(6.0, row:'Series 2', column:'Type 3')
        addValue(8.0, row:'Series 2', column:'Type 4')
        addValue(4.0, row:'Series 2', column:'Type 5')
        addValue(4.0, row:'Series 2', column:'Type 6')
        addValue(2.0, row:'Series 2', column:'Type 7')
        addValue(1.0, row:'Series 2', column:'Type 8')
        addValue(4.0, row:'Series 3', column:'Type 1')
        addValue(3.0, row:'Series 3', column:'Type 2')
        addValue(2.0, row:'Series 3', column:'Type 3')
        addValue(3.0, row:'Series 3', column:'Type 4')
        addValue(6.0, row:'Series 3', column:'Type 5')
        addValue(3.0, row:'Series 3', column:'Type 6')
        addValue(4.0, row:'Series 3', column:'Type 7')
        addValue(3.0, row:'Series 3', column:'Type 8')

    }
**/
}

def chart = builder.getChart();
def chartPanel = new ChartPanel(chart, false);
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