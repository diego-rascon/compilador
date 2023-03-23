package panels;

import javax.swing.*;
import java.awt.*;

public abstract class PanelTemplate extends JPanel {

    protected PanelTemplate(String title, int padding) {
        setLayout(new BorderLayout(padding, padding));

        final JLabel codeLabel = new JLabel(title);
        add(codeLabel, BorderLayout.NORTH);
    }
}
