import view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        final MainFrame mainFrame = new MainFrame();

        mainFrame.setIconImage(new ImageIcon("./src/main/resources/code.png").getImage());
        mainFrame.setSize(800, 600);
        mainFrame.setMinimumSize(new Dimension(400, 300));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Compilador - José Diego Rascón Amador (20130375)");
        mainFrame.setVisible(true);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
