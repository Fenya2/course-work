package course.work.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Панель, где размещается информация о функциях в модели
 */
public class FunctionsPanel extends JPanel implements PropertyChangeListener {

    /**
     * Панель, куда добавляются представления функций
     */
    private JPanel fJPanel; // TODO поменять на список

    public FunctionsPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Функции"), BorderLayout.NORTH);
        fJPanel = new JPanel();
        fJPanel.setLayout(new GridLayout(0, 1));
        add(fJPanel, BorderLayout.CENTER);
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        // TODO сделать заполнение по INIT
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
}
