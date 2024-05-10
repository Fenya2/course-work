package course.work;

import course.work.model.Function;
import course.work.model.SDEModel;
import java.util.Random;

public class RandModel extends SDEModel {
    /**
     * Включает болезнь
     */
    private boolean diseaseOn;

    /**
     * Время, которое болезнь уже распространялась.
     * Пробегает от 0 до времени жизни болезни.
     */
    private double diseaseT;

    /**
     * Время жизни болезни
     */
    private double diseasePeriod;

    /**
     * Амплитуда болезни (интенсивность)
     */
    private double diseaseAmplitude;

    private Random random;

    public RandModel() {
        super();
        diseaseOn = false;
        diseasePeriod = 0;
        diseaseT = 0;
        diseaseAmplitude = 0;
        random = new Random(87641);
        p.put("a", 0.1);
        p.put("g", 0.4);
        p.put("b", 0.01);
        p.put("disease probability", 0.3);
        p.put("disease min period", 10.0);
        p.put("disease max period", 50.0);
        p.put("disease min amplitude", 0.01);
        p.put("disease max amplitude", 0.02);
        p.put("end", 50.0);

        Function dx_dt = new Function(
                "группа риска",
                (args) -> {
                    return  -p.get("a") * args.get(1) * args.get(2)
                            + p.get("g") * args.get(3) - diseaseAmplitude * diseaseRun() * args.get(1);
                });

        Function dy_dt = new Function(
                "зараженные",
                (args) -> {
                    return p.get("a") * args.get(1) * args.get(2)
                            - p.get("b") * args.get(2) + diseaseAmplitude * diseaseRun() * args.get(1);
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
     * попытка начать болезнь (с определенной вероятностью) или продолжить
     * ее развитие (затухание)
     */
    private double diseaseRun() {
        if (diseaseOn) {
            return continueDisease();
        }
        return tryDisease();
    }

    /**
     * С указанной вероятностью пытается вызвать болезнь.
     * Если получается, меняет флаг на true, генерирует случайный период,
     * генерирует случайную амплитуду.
     */
    private double tryDisease() {
        double prob = random.nextDouble();
        if(prob < p.get("disease probability")) {
            diseasePeriod = p.get("disease min period") + random.nextDouble() * (p.get("disease max period") - p.get("disease min period"));
            diseaseAmplitude = p.get("disease min amplitude") + random.nextDouble() * (p.get("disease max amplitude") - p.get("disease min amplitude"));
            diseaseOn = true;
        }
        return 0;
    }

    /**
     * Продолжает болезнь, увеличивая deseaseT. Если она закончилась,
     * устанавливает deseaseT в 0, снимает флаг, возвращает 0. Если нет,
     * возвращает значение болезни
     */
    private double continueDisease() {
        diseaseT += p.get("dt");
        if(diseaseT > diseasePeriod) {
            diseaseT = 0;
            diseaseAmplitude = 0;
            diseaseOn = false;
            return 0;
        }
        return Math.sin(Math.PI * diseaseT / diseasePeriod);
    }
}
