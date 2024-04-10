package course.work.controller;

import course.work.model.SDEModel;

/**
 * Контроллер модели системы дифференциальных уравнений
 */
public class SDEController {
    private SDEModel model;
    public SDEController(SDEModel model) {
        this.model = model;
    }

    /**
     * Устанавливает новый параметр модели
     */
    public void setParam(String name, Double value) {
        model.getP().put(name, value);
    }

    /**
     * отображать ли i-ю функцию на графике
     */
    public void hideFunction(int number, boolean value) {
        // TODO сделать
    }

    /**
     * Решает систему
     */
    public void solve() {
        model.solve();
    }

    /**
     * Возвращает связанную модель
     */
    public SDEModel getModel() {
        return model;
    }
}
