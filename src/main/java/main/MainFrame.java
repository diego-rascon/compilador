package main;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import panels.CodePanel;
import panels.CountersPanel;
import panels.errors.ErrorsPanel;
import panels.tokens.TokensPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public static final byte padding = 8;

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

        final JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainSplit.setBorder(new EmptyBorder(padding, padding, 0, padding));
        mainSplit.setResizeWeight(0.66);
        mainSplit.setDividerSize(padding);
        mainPanel.add(mainSplit, BorderLayout.CENTER);

        final TokensPanel tokensPanel = new TokensPanel();
        final CountersPanel countersPanel = new CountersPanel();
        final CodePanel codePanel = new CodePanel(tokensPanel, countersPanel);

        final JSplitPane rightSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tokensPanel, countersPanel);
        rightSplit.setResizeWeight(0.5);
        rightSplit.setDividerSize(padding);

        final JSplitPane leftSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, codePanel, rightSplit);
        leftSplit.setResizeWeight(0.5);
        leftSplit.setDividerSize(padding);

        mainSplit.add(leftSplit);

        final ErrorsPanel errorsPanel = new ErrorsPanel();
        mainSplit.add(errorsPanel);

        final JPanel buttonsPanel = new JPanel();
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        final JButton openButton = new JButton("Abrir");
        buttonsPanel.add(openButton);

        final JButton exportButton = new JButton("Exportar");
        buttonsPanel.add(exportButton);

        final JButton compileButton = new JButton("Compilar");
        compileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codePanel.compile();
            }
        });
        buttonsPanel.add(compileButton);
    }
}
