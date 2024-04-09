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
     * хранит в том числе dt и конечную точку("end").
     */
    protected Map<String, Double> p;
    /**
     * Функции системы (стоят справа от производных в математической записи)
     * Первый элемент списка занят свободной переменной, то есть
     * нумерация их начинается с 1
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
     * Уведомляет слушателей о создании модели и передает данные о ней
     * для отрисовки ее представления
     */
    private void init() {
        // TODO написать INIT модели
        pcs.firePropertyChange(ModelEvents.INIT.toString(), null, null);
    }

    /**
     * Уведомляет слушателей, что найдено решение системы с заданными
     * параметрами, передает их для отрисовки
     */
    private void solution() {
        // TODO написать SOLUTION_READY модели
        pcs.firePropertyChange(ModelEvents.SOLUTION_READY.toString(), null, null);
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
