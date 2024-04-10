package course.work.gui;

import javax.swing.JFrame;

import course.work.SirModel;
import course.work.controller.SDEController;
import course.work.model.SDEModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Главное окно всей программы
 */
public class MainFrame extends JFrame {
    private SDEModel model;
    private SDEController controller;

    public MainFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        setLayout(new BorderLayout());

        model = new SirModel();
        controller = new SDEController(model);

        SidePane sidePane = new SidePane(controller);
        PlotView plot = new PlotView(controller);

        add(sidePane, BorderLayout.WEST);
        add(plot, BorderLayout.CENTER);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        model.init();
    }
}
