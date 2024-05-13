package course.work.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import course.work.controller.SDEController;
import course.work.model.ModelEvents;

import java.awt.BasicStroke;
import java.awt.Color;

/**
 * Панель, в которой отображаются графики заданной системы уравнений
 */
public class PlotView extends ChartPanel implements PropertyChangeListener {

    public PlotView(SDEController controller) {
        super(new JFreeChart(
                "Здесь буден нарисован график",
                JFreeChart.DEFAULT_TITLE_FONT,
                new XYPlot(),
                false));
        controller.getModel().addPropertyChangeListener(this);
    }

    /**
     * Строит график решения
     */
    private void plot(List<ImmutablePair<String, List<Double>>> solution) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        for (int i = 1; i < solution.size() - 1; i++) {
            double[][] data = new double[2][solution.get(i).right.size()];
            for (int j = 0; j < solution.get(i).right.size(); j++) {
                data[0][j] = solution.get(0).right.get(j);
                data[1][j] = solution.get(i).right.get(j);
            }
            dataset.addSeries(solution.get(i).left, data);
        }

        double[][]data = new double[2][solution.get(4).right.size()];
        for(int i = 0; i < data[0].length; i++) {
            data[0][i] = i / 10.0;
            data[1][i] = solution.get(4).right.get(i);
        }
        dataset.addSeries("splash", data);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "решение",
                "time",
                "value",
                dataset);
        paintChart(chart);
        setChart(chart);
    }

    /**
     * Красит графики в контрастные цвета
     */
    private void paintChart(JFreeChart chart) {
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRenderer().setSeriesPaint(0, Color.RED);
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(3));
        plot.getRenderer().setSeriesPaint(1, Color.BLUE);
        plot.getRenderer().setSeriesStroke(1, new BasicStroke(3));
        plot.getRenderer().setSeriesPaint(2, Color.GREEN);
        plot.getRenderer().setSeriesStroke(2, new BasicStroke(3));
        plot.getRenderer().setSeriesPaint(3, Color.BLACK);
        plot.getRenderer().setSeriesStroke(3, new BasicStroke(3));
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals(ModelEvents.SOLUTION_READY.toString())) {
            plot((List<ImmutablePair<String, List<Double>>>) event.getNewValue());
        }
    }
}
