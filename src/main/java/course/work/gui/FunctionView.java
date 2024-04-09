package course.work.gui;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;

import course.work.model.Function;

/**
 * Представление для пользователя функции в модели (имя и рисовать ли)
 */
public class FunctionView extends JPanel {
    public FunctionView(Function function) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(new JCheckBox(function.getName(), function.needPlot()));
    }
}
