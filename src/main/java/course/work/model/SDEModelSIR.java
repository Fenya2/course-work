package course.work.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Система дифференциальных уравнений для SIR
 */
public class SDEModelSIR {
    /**
     * Параметры - числа, предоставляемые пользователю в gui.
     */
    private List<Double> userParams;
    /**
     * Параметры системы, заданные в виде функций
     */
    private List<Function> functionParams;
    /**
     * Функции от переменных и параметров (стоят справа от dxi/dt)
     */
    private List<Function> functions;
    /**
     * Начальные условия
     */
    private List<Double> startConditions;

    private PropertyChangeSupport pcs;

    /**
     * Описывает систему диффуров.
     */
    public SDEModelSIR() {
        functionParams = new ArrayList<Function>();
        Function param1 = new Function(
                "b (вероятность передачи болезни)",
                (vars, params) -> {
                    return userParams.get(0);
                });
        Function param2 = new Function(
                "a (вероятность попасть в группу переболевших)",
                (vars, params) -> {
                    return userParams.get(1);
                });

        functionParams.add(param1);
        functionParams.add(param2);

        Function dx_dt = new Function(
                "Группа риска",
                (vars, params) -> {
                    return -functionParams.get(0).calculate(vars, params) * vars.get(1) * vars.get(2);
                });

        Function dy_dt = new Function(
                "Зараженные",
                (vars, params) -> {
                    return functionParams.get(0).calculate(vars, params) * vars.get(1) * vars.get(2) - functionParams.get(1).calculate(vars, params) * vars.get(2);
                });

        Function dz_dt = new Function(
                "Умершие или получившие иммунитет",
                (vars, params) -> {
                    return functionParams.get(1).calculate(vars, params) * vars.get(2); 
                });
    }

    /**
     * Метод для добавления слушателя изменения модели
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Метод для удаления слушателя модели
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    /**
     * Уведомляет слушателей об изменении состояния модели.
     */
    private void notifyListeners() {
        pcs.firePropertyChange("model_update", null, getViewData());
    }

    /**
     * Формирует данные дли отображения модели пользователю
     */
    private Object getViewData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getViewData'");
    }
}
