package course.work.model.sdesolvers;

import java.util.ArrayList;
import java.util.List;

import course.work.model.Function;
import course.work.model.SDEModel;

public class RK4Solver implements SDESolver {

    @Override
    public List<List<Double>> solve(SDEModel sde) {
        List<Function> f = sde.getF();
        List<Double> sc = new ArrayList<>(f.size());
        for (int i = 0; i < f.size(); i++)
            sc.add(sde.getSc().get(i));
        Double e = sde.getP().get("end");
        Double dt = sde.getP().get("dt");

        List<List<Double>> res = new ArrayList<>(f.size());
        List<Double> args = new ArrayList<>(res.size());
        List<Double> k1 = new ArrayList<>(f.size());
        List<Double> k2 = new ArrayList<>(f.size());
        List<Double> k3 = new ArrayList<>(f.size());
        List<Double> k4 = new ArrayList<>(f.size());
        for (int i = 0; i < f.size(); i++) {
            res.add(new ArrayList<>());
            res.get(i).add(sc.get(i));
            args.add(0.0);
            k1.add(0.0);
            k2.add(0.0);
            k3.add(0.0);
            k4.add(0.0);
        }

        while (sc.get(0) <= e) {
            /* заполняем k1 */
            for (int i = 1; i < f.size(); i++)
                k1.set(i - 1, f.get(i).of(sc) * dt);

            args.set(0, sc.get(0) + dt / 2);
            for (int i = 1; i < f.size(); i++) {
                args.set(i, sc.get(i) + k1.get(i - 1) / 2);
            }
            // /* заполняем k2 */
            for (int i = 1; i < f.size(); i++) {
                k2.set(i - 1, f.get(i).of(args) * dt);
            }

            for (int i = 1; i < f.size(); i++) {
                args.set(i, sc.get(i) + (k2.get(i - 1) / 2));
            }

            /* заполняем k3 */
            for (int i = 1; i < f.size(); i++) {
                k3.set(i - 1, f.get(i).of(args) * dt);
            }
            args.set(0, sc.get(0) + dt);
            for (int i = 1; i < f.size(); i++) {
                args.set(i, sc.get(i) + k3.get(i - 1));
            }

            /* заполняем k4 */
            for (int i = 1; i < f.size(); i++) {
                k4.set(i - 1, f.get(i).of(args) * dt);
            }

            sc.set(0, sc.get(0) + dt);
            res.get(0).add(sc.get(0));
            for (int i = 1; i < f.size(); i++) {
                sc.set(i, sc.get(i) + (k1.get(i - 1) + 2 * (k2.get(i - 1) + k3.get(i - 1)) + k4.get(i - 1)) / 6);
                res.get(i).add(sc.get(i));
            }
        }
        return res;
    }
}
