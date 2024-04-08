package course.work.model;

/**
 * Система дифференциальных уравнений для SIR
 */
public class SirModel extends SDEModel {
    /**
     * Описывает систему диффуров модели sir
     */
    public SirModel() {
        super();
        p.put("b", 0.02);
        p.put("a", 0.2);
        p.put("end", 20.0);

        Function dx_dt = new Function(
                "группа риска",
                (args) -> {
                    return -p.get("b") * args.get(1) * args.get(2);
                });

        Function dy_dt = new Function(
                "зараженные",
                (args) -> {
                    return p.get("b") * args.get(1) * args.get(2) - p.get("a") * args.get(2);
                });

        Function dz_dt = new Function(
                "Умершие или с иммунитетом",
                (args) -> {
                    return p.get("a") * args.get(2);
                });

        sc.add(0.0);
        sc.add(99.0);
        sc.add(1.0);
        sc.add(0.0);

        f.add(dx_dt);
        f.add(dy_dt);
        f.add(dz_dt);
    }
}
