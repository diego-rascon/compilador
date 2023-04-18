package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        final MainFrame mainFrame = new MainFrame();

        mainFrame.setIconImage(new ImageIcon("./src/main/resources/code.png").getImage());
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Léxico - 20130375");
        mainFrame.setVisible(true);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
