package course.work.model.sdesolvers;

import java.util.List;

import course.work.model.SDEModel;

/**
 * Решает систему дифференциальных уравнений
 */
@FunctionalInterface
public interface SDESolver {
    /**
     * Возвращает решение системы в виде таблицы значений функций
     */
    public List<List<Double>> solve(SDEModel sde);
}
