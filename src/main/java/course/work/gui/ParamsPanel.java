package course.work.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import course.work.controller.SDEController;
import course.work.model.ModelEvents;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Панель, где размещается информация о параметрах модели
 * с возможностью их изменить
 */
public class ParamsPanel extends JPanel implements PropertyChangeListener {
    /**
     * Панель, куда добавляются параметры модели
     */
    private JPanel paramsPanel; // TODO поменять на список
    private SDEController controller;

    public ParamsPanel(SDEController controller) {
        setLayout(new BorderLayout());
        add(new JLabel("Параметры", SwingConstants.CENTER), BorderLayout.NORTH);
        paramsPanel = new JPanel();
        paramsPanel.setLayout(new BoxLayout(paramsPanel, BoxLayout.Y_AXIS));
        add(paramsPanel);
        controller.getModel().addPropertyChangeListener(this);
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals(ModelEvents.INIT.toString())) {
            Map<String, Double> params;
            params = (Map<String, Double>) ((Map<String, Object>) event.getNewValue()).get("params"); // TODO знаю, ужас
            initParams(params);
            revalidate();
        }
    }

    /**
     * Возвращает панель с именем параметра и текстовым полем с привязанным
     * контроллером на изменение параметра с этим именем.
     * 
     * @return
     */
    private JPanel getParamEntry(String name, Double startValue) {
        JPanel paramEntry = new JPanel();
        paramEntry.setLayout(new FlowLayout());
        JLabel label = new JLabel(name);
        JTextField tf = new JTextField(startValue.toString(), 10);
        tf.addActionListener(event -> { // TODO сделать обработчик по tab
            controller.setParam(name, Double.parseDouble(tf.getText())); // TODO сделать проверку на корректный ввод с диалоговым окном
        });
        paramEntry.add(label);
        paramEntry.add(tf);
        return paramEntry;
    }

    /**
     * Перерисовывет панель с параметрами
     */
    private void initParams(Map<String, Double> params) {
        SwingUtilities.invokeLater(() -> {
            paramsPanel.removeAll();
            for (Entry<String, Double> param : params.entrySet()) {
                paramsPanel.add(getParamEntry(param.getKey(), param.getValue()));
            }
        });
    }
}
