package course.work;

import javax.swing.SwingUtilities;

import course.work.gui.MainFrame;;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
