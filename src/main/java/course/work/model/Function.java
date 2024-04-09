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

    /**
     * Нужно ли рисовать ее график
     */
    private boolean needPlot;

    public Function(String name, FunctionBody functionBody) {
        this.name = name;
        this.functionBody = functionBody;
        needPlot = true;
    }

    /**
     * Возвращает значение функции от указанных переменных
     * и функциональных параметров.
     */
    public double of(List<Double> args) {
        return functionBody.of(args);
    }

    public String getName() {
        return name;
    }

    public boolean needPlot() {
        return needPlot;
    }

    public void setNeedPlot(boolean needPlot) {
        this.needPlot = needPlot;
    }
}
