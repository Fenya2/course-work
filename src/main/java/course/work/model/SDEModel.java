package course.work.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;

import course.work.model.sdesolvers.RK4Solver;

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
    private PropertyChangeSupport pcs;

    /**
     * Инициализирует поля. Задает линейную функцию времени t
     */
    public SDEModel() {
        p = new LinkedHashMap<String, Double>();
        f = new ArrayList<Function>();
        sc = new ArrayList<Double>();
        pcs = new PropertyChangeSupport(this);
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
     * Возвращает структуру модели для ее отрисовки.
     */
    private Map<String, Object> getInitData() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("functions", getF());
        ret.put("params", getP());
        return ret;
    }

    /**
     * Уведомляет слушателей о создании модели и передает данные о ней
     * для отрисовки ее представления
     */
    public void init() {
        pcs.firePropertyChange(ModelEvents.INIT.toString(), null, getInitData());
    }

    /**
     * Уведомляет слушателей, что найдено решение системы с заданными
     * параметрами, передает их для отрисовки
     */
    private void onSolution(List<ImmutablePair<String, List<Double>>> solution) {
        pcs.firePropertyChange(ModelEvents.SOLUTION_READY.toString(), null, solution);
    }

    /**
     * Решает систему диффуров, по решении отправляет его слушателям.
     */
    public void solve() {
        List<ImmutablePair<String, List<Double>>> solution = new ArrayList<ImmutablePair<String, List<Double>>>();
        List<List<Double>> result = new RK4Solver().solve(this);
        for (int i = 0; i < result.size(); i++) {
            solution.add(new ImmutablePair<String, List<Double>>(f.get(i).getName(), result.get(i)));
        }
        onSolution(solution);
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
