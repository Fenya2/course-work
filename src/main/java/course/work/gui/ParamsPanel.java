package course.work.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Панель, где размещается информация о параметрах модели
 * с возможностью их изменить
 */
public class ParamsPanel extends JPanel {
    public ParamsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Параметры"), BorderLayout.NORTH);
    }
}
