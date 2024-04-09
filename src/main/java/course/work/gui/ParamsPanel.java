package course.work.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Панель, где размещается информация о параметрах модели
 * с возможностью их изменить
 */
public class ParamsPanel extends JPanel implements PropertyChangeListener {
    /**
     * Панель, куда добавляются представления функций
     */
    private JPanel paramsPanel; // TODO поменять на список

    public ParamsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Параметры"), BorderLayout.NORTH);
        paramsPanel = new JPanel();

    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        // TODO Сделать заполнение по INIT
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
}
