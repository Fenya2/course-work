package course.work.gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import course.work.RandModel;
import course.work.SirModel;
import course.work.controller.SDEController;
import course.work.model.SDEModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Главное окно всей программы
 */
public class MainFrame extends JFrame {
    private SDEController controller;

    public MainFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        setJMenuBar(menuBar);

        SDEModel model = new RandModel();
        controller = new SDEController(model);

        SidePane sidePane = new SidePane(controller);
        PlotView plot = new PlotView(controller);

        add(sidePane, BorderLayout.WEST);
        add(plot, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        model.init();
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("file");
        JMenuItem openItem = new JMenuItem("open");
        fileMenu.add(openItem);

        openItem.addActionListener((event) -> {
            openModel();
        });
        return fileMenu;
    }

    /**
     * Меняет модель на ту, что лежит в файле, указанном пользователем
     */
    private void openModel() {
        //TODO сделать загрузку класса модели и создание экземпляра
        System.out.println("open new model");
    }
}
