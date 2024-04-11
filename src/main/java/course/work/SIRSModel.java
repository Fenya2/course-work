package course.work;

import course.work.model.Function;
import course.work.model.SDEModel;

public class SIRSModel extends SDEModel {
    public SIRSModel() {
        super();
        p.put("b", 0.2);
        p.put("a", 0.1);
        p.put("g", 0.0);
        p.put("v", 2.0);
        p.put("end", 50.0);

        Function dx_dt = new Function(
                "группа риска",
                (args) -> {
                    return -p.get("b")*Math.cos(p.get("v")*args.get(0))*args.get(1)*args.get(2) + p.get("g")*args.get(3);
                });

        Function dy_dt = new Function(
                "зараженные",
                (args) -> {
                    return p.get("b") * Math.cos(p.get("v")* args.get(0)) * args.get(1) * args.get(2) - p.get("a") * args.get(2);
                });

        Function dz_dt = new Function(
                "С иммунитетом",
                (args) -> {
                    return p.get("a") * args.get(2) - p.get("g") * args.get(3);
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
