package course.work.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Панель, где размещается информация о функциях в модели
 */
public class FunctionsPanel extends JPanel {
    public FunctionsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Функции"), BorderLayout.NORTH);
    }
}
