package course.work.gui;

import java.awt.Color;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import course.work.model.SirModel;
import course.work.sdesolvers.RK4Solver;

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

        // TODO сделать нормально (это проверка)
        SirModel sm = new SirModel();
        RK4Solver solver = new RK4Solver();
        List<List<Double>> solution = solver.solve(sm);
        for (int i = 1; i < solution.size(); i++) {
            double[][] data = new double[2][solution.get(0).size()];
            for (int j = 0; j < solution.get(i).size(); j++) {
                data[0][j] = solution.get(0).get(j);
                data[1][j] = solution.get(i).get(j);
            }
            dataset.addSeries(sm.getF().get(i).getName(), data);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "решение",
                "Время",
                "Особи",
                dataset
        );
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesPaint(2, Color.BLACK);
        setChart(chart);
    }
}
