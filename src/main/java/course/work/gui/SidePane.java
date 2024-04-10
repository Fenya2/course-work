package course.work.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import course.work.controller.SDEController;

/**
 * Боковая панель с вводом параметров модели
 */
public class SidePane extends JPanel {
    public SidePane(SDEController controller) {
        setLayout(new BorderLayout());
        add(new ParamsPanel(controller), BorderLayout.CENTER);
        JButton plotButton = new JButton("plot");
        plotButton.addActionListener(event -> controller.solve());
        add(plotButton, BorderLayout.SOUTH);
    }
}
