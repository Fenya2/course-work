package course.work.gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;

/**
 * Панель, в которой отображаются графики заданной системы уравнений
 */
public class PlotView extends ChartPanel {

    public PlotView() {
        super(new JFreeChart(
            "default",
            JFreeChart.DEFAULT_TITLE_FONT,
            new XYPlot(),
            false));
        test();
    }

    private void test() {
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] data = {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}};
        dataset.addSeries("Series 1", data);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Линейный график", // Заголовок графика
                "X", // Название оси X
                "Y", // Название оси Y
                dataset // Набор данных
        );
        setChart(chart);
    }
}
