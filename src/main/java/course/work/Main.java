package course.work;

import java.util.List;

import javax.swing.SwingUtilities;

import course.work.gui.MainFrame;
import course.work.model.SirModel;
import course.work.sdesolvers.RK4Solver;;

public class Main {
    public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> {
        //     MainFrame frame = new MainFrame();
        //     frame.setVisible(true);
        // });

        SirModel sir = new SirModel();
        RK4Solver solver = new RK4Solver();
        List<List<Double>> result = solver.solve(sir);

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j));
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}
