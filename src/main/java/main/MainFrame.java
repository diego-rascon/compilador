package main;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import panels.CodePanel;
import panels.CounterPanel;
import panels.ErrorPanel;
import panels.TokenPanel;
import util.FileHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        initComponents();
    }

    final void initComponents() {
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        final byte padding = 8;

        final JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainSplit.setBorder(new EmptyBorder(padding, padding, 0, padding));
        mainSplit.setResizeWeight(0.66);
        mainSplit.setDividerSize(padding);
        mainPanel.add(mainSplit, BorderLayout.CENTER);

        final TokenPanel tokenPanel = new TokenPanel(padding);
        final CounterPanel counterPanel = new CounterPanel(padding);
        final ErrorPanel errorPanel = new ErrorPanel(padding);
        final CodePanel codePanel = new CodePanel(padding, tokenPanel, counterPanel, errorPanel);

        final JSplitPane rightSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tokenPanel, counterPanel);
        rightSplit.setResizeWeight(0.5);
        rightSplit.setDividerSize(padding);

        final JSplitPane leftSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, codePanel, rightSplit);
        leftSplit.setResizeWeight(0.5);
        leftSplit.setDividerSize(padding);

        mainSplit.add(leftSplit);
        mainSplit.add(errorPanel);

        final JPanel buttonsPanel = new JPanel();
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        final FileHandler fileHandler = new FileHandler(
                this, codePanel, tokenPanel, counterPanel, errorPanel
        );

        final JButton openButton = new JButton("Abrir");
        openButton.addActionListener(e -> fileHandler.openFile());
        openButton.setMnemonic('A');
        buttonsPanel.add(openButton);

        final JButton exportButton = new JButton("Exportar");
        exportButton.addActionListener(e -> fileHandler.exportFile());
        exportButton.setMnemonic('E');
        buttonsPanel.add(exportButton);

        final JButton compileButton = new JButton("Compilar");
        compileButton.addActionListener(e -> codePanel.compile());
        compileButton.setMnemonic('C');
        buttonsPanel.add(compileButton);
    }
}
