package panels;

import javax.swing.*;
import java.awt.*;

import static main.MainFrame.padding;

public abstract class PanelTemplate extends JPanel {

    protected PanelTemplate(String title) {
        setLayout(new BorderLayout(padding, padding));

        final JLabel codeLabel = new JLabel(title);
        add(codeLabel, BorderLayout.NORTH);
    }
}
