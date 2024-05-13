package course.work;

import course.work.model.Function;
import course.work.model.SDEModel;

public class SimpleModel extends SDEModel {
    public SimpleModel() {
        super();
        p.put("a", 0.1);
        p.put("end", 50.0);

        Function dx_dt = new Function(
                "здоровые",
                (args) -> {
                    return -p.get("a") * args.get(1) * (100 - args.get(2));
                });

        Function dy_dt = new Function(
                "зараженные",
                (args) -> {
                    return p.get("a") * args.get(1) * (100 - args.get(2));
                });

        sc.add(0.0);
        sc.add(99.0);
        sc.add(1.0);

        f.add(dx_dt);
        f.add(dy_dt);
    }
}
