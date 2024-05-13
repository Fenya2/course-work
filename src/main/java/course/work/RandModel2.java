package course.work;

import java.util.Set;
import java.util.TreeSet;

import course.work.model.Function;
import course.work.model.SDEModel;

import java.util.Random;

public class RandModel2 extends SDEModel {

    public RandModel2() {
        super();
        p.put("a", 0.02);
        p.put("g", 0.05);
        p.put("b", 0.2);
        p.put("disease max amplitude", 0.02);
        p.put("end", 50.0);

        double[] paramFunc = generateParamFunc(7, 51.0, 10);
        pf = paramFunc;

        Function dx_dt = new Function(
                "группа риска",
                (args) -> {
                    return -p.get("a") * args.get(1) * args.get(2)
                            + p.get("g") * args.get(3) - paramFunc[(int) (args.get(0) * 10)] * args.get(1);
                });

        Function dy_dt = new Function(
                "зараженные",
                (args) -> {
                    return p.get("a") * args.get(1) * args.get(2)
                            - p.get("b") * args.get(2) + paramFunc[(int) (args.get(0) * 10)] * args.get(1);
                });

        Function dz_dt = new Function(
                "С иммунитетом",
                (args) -> {
                    return p.get("b") * args.get(2) - p.get("g") * args.get(3);
                });

        sc.add(0.0);
        sc.add(99.0);
        sc.add(1.0);
        sc.add(0.0);

        f.add(dx_dt);
        f.add(dy_dt);
        f.add(dz_dt);
    }

    /**
     * Возвращает массив double - параметрическую функцию, определенную на
     * [0, end], везде 0, кроме n промежутков.
     */
    private double[] generateParamFunc(int N, double end, double maxAmplitude) {
        double[] points = generateUniqueFloats(2 * N, 0, end);
        double[] paramFunc = new double[(int) (end * 10)];
        Random random = new Random();

        for (int i = 0; i < points.length; i += 2) {
            double amplitude = maxAmplitude * random.nextDouble();
            for (double j = points[i]; j < points[i + 1]; j += 0.1) {
                paramFunc[(int) (j * 10)] = amplitude
                        * Math.sin(Math.PI * (j - points[i]) / (points[i + 1] - points[i]));
            }
        }
        return paramFunc;
    }

    /**
     * Возвращает отсортированный массив n случайных чисел с плавающей точкой
     * с округлением до десятых от a до b
     */
    public double[] generateUniqueFloats(int n, double a, double b) {

        Random random = new Random();
        Set<Double> resultSet = new TreeSet<>();
        while (resultSet.size() < n) {
            double val = a + (b - a) * random.nextDouble();
            double randomValue = Math.round((a + (b - a) * random.nextDouble()) * 10) / 10.0;
            resultSet.add(randomValue);
        }

        double[] resultArray = new double[n];
        int index = 0;
        for (Double value : resultSet) {
            resultArray[index++] = value;
        }

        return resultArray;
    }
}
