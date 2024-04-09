package course.work.gui;

import javax.swing.JFrame;

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

        SidePane sidePane = new SidePane();
        PlotView plot = new PlotView();

        add(sidePane, BorderLayout.WEST);
        add(plot, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
