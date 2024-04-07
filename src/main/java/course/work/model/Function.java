package course.work.model;

import java.util.List;

/**
 * Класс, описывающий функцию нескольких переменных
 */
public class Function {
    /**
     * Человекочитаемое название функции
     */
    private final String name;

    /**
     * Тело функции
     */
    private final FunctionBody functionBody;

    public Function(String name, FunctionBody functionBody) {
        this.name = name;
        this.functionBody = functionBody;
    }

    /**
     * Возвращает значение функции от указанных переменных
     * и функциональных параметров.
     */
    public double calculate(List<Double> vars, List<Function> params) {
        return functionBody.calculate(vars, params);
    }
}
