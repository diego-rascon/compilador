package view;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
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

        final Tokens tokenPanel = new Tokens(padding);
        final Counters countersPanel = new Counters(padding);
        final Errors errorsPanel = new Errors(padding);
        final ErrorTypes errorTypesPanel = new ErrorTypes(padding);
        final Code codePanel = new Code(padding, tokenPanel, countersPanel, errorsPanel, errorTypesPanel);

        final JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainSplit.setBorder(new EmptyBorder(padding, padding, 0, padding));
        mainSplit.setResizeWeight(0.66);
        mainSplit.setDividerSize(padding);
        mainPanel.add(mainSplit, BorderLayout.CENTER);

        final JSplitPane rightSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tokenPanel, countersPanel);
        rightSplit.setResizeWeight(0.8);
        rightSplit.setDividerSize(padding);

        final JSplitPane leftSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, codePanel, rightSplit);
        leftSplit.setResizeWeight(0.9);
        leftSplit.setDividerSize(padding);

        final JSplitPane errorSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, errorsPanel, errorTypesPanel);
        errorSplit.setResizeWeight(1);
        leftSplit.setDividerSize(padding);

        mainSplit.add(leftSplit);
        mainSplit.add(errorSplit);

        final JPanel buttonsPanel = new JPanel();
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        final FileHandler fileHandler = new FileHandler(this, codePanel, tokenPanel, countersPanel, errorsPanel, errorTypesPanel);

        final JButton openButton = new JButton("Abrir");
        openButton.addActionListener(e -> fileHandler.openFile());
        openButton.setMnemonic('A');
        buttonsPanel.add(openButton);

        final JButton compileButton = new JButton("Compilar");
        compileButton.addActionListener(e -> codePanel.compile());
        compileButton.setMnemonic('C');
        buttonsPanel.add(compileButton);

        final JButton exportButton = new JButton("Exportar");
        exportButton.addActionListener(e -> fileHandler.exportFile());
        exportButton.setMnemonic('E');
        buttonsPanel.add(exportButton);
    }
}
