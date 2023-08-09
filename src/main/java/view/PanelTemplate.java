package view;

import javax.swing.*;
import java.awt.*;

public abstract class PanelTemplate extends JPanel {

    private final JLabel panelLabel;

    protected PanelTemplate(String title, int padding) {
        setLayout(new BorderLayout(padding, padding));

        panelLabel = new JLabel(title);
        add(panelLabel, BorderLayout.NORTH);
    }

    public String getLabel() {
        return panelLabel.getText();
    }
}
