package course.work.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Панель, содержащая информацию о модели и интерфейс для изменения ее
 * параметров
 */
public class ModelParamsPane extends JPanel {
    public ModelParamsPane() {
        setLayout(new GridLayout(2, 1));
        add(new FunctionsPanel());
        add(new ParamsPanel());
    }
}
