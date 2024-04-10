package course.work.gui;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import course.work.SirModel;
import course.work.controller.SDEController;
import course.work.model.ModelEvents;
import course.work.model.sdesolvers.RK4Solver;

/**
 * Панель, в которой отображаются графики заданной системы уравнений
 */
public class PlotView extends ChartPanel implements PropertyChangeListener {

    public PlotView(SDEController controller) {
        super(new JFreeChart(
                "default",
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
        for (int i = 1; i < solution.size(); i++) {
            double[][] data = new double[2][solution.get(i).right.size()];
            for (int j = 0; j < solution.get(i).right.size(); j++) {
                data[0][j] = solution.get(0).right.get(j);
                data[1][j] = solution.get(i).right.get(j);
            }
            System.out.println(solution.get(i).left);
            dataset.addSeries(solution.get(i).left, data);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "решение",
                "time",
                "value",
                dataset);
        setChart(chart);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if(event.getPropertyName().equals(ModelEvents.SOLUTION_READY.toString())) {
            plot((List<ImmutablePair<String, List<Double>>>) event.getNewValue());
        }
    }
}
