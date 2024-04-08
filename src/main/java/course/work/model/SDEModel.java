package course.work.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Заготовка для составления системы дифференциальных уравнений
 */
public class SDEModel {
    /**
     * Параметры - числа, предоставляемые пользователю в gui.
     * хранит в том числе dt и конечную точку.
     */
    protected Map<String, Double> p;
    /**
     * Параметры системы, заданные в виде функций
     */
    protected List<Function> f;
    /**
     * Начальные условия
     */
    protected List<Double> sc;

    /**
     * Объект, для реализации паттерна observer-observable
     */
    protected PropertyChangeSupport pcs;

    /**
     * Инициализирует поля. Задает линейную функцию времени t
     */
    public SDEModel() {
        p = new HashMap<String, Double>();
        f = new ArrayList<Function>();
        sc = new ArrayList<Double>();
        p.put("dt", 0.1);
        p.put("end", 10.0);
        f.add(new Function("t", (args) -> {
            return args.get(0);
        }));
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

    public Map<String, Double> getP() {
        return p;
    }

    public List<Function> getF() {
        return f;
    }

    public List<Double> getSc() {
        return sc;
    }
}
