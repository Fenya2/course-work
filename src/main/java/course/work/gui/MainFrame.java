package course.work.gui;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Главное окно всей программы
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        setLayout(new BorderLayout());

        JButton westButton = new JButton("West");
        ChartPanel plot = new PlotView();

        add(westButton, BorderLayout.WEST);
        add(plot, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
