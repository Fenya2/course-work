package course.work.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Боковая панель с вводом параметров модели
 */
public class SidePane extends JPanel {
    public SidePane() {
        setLayout(new BorderLayout());
        add(new ModelParamsPane());
        add(new JButton("plot"), BorderLayout.SOUTH);
    }
}
