package course.work.model;

import java.util.List;

/**
 * Функциональный интерфейс, представляющий в контексте Function ее тело.
 */
@FunctionalInterface
public interface FunctionBody {
    /**
     * Принимает точку n-мерного пространства и Список параметров - функций от
     * n переменных. На основе их считает значение функции в этой точке
     */
    double calculate(List<Double> vars, List<Function> params);
}