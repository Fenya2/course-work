package course.work.model;

import java.util.List;

/**
 * Функциональный интерфейс, представляющий в контексте Function ее тело.
 */
@FunctionalInterface
public interface FunctionBody {
    /**
     * Возвращает значение функции в указанной точке
     */
    public double of(List<Double> args);
}