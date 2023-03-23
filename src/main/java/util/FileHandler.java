package util;

import panels.CodePanel;
import panels.CounterPanel;
import panels.ErrorPanel;
import panels.TokenPanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

    private final JFrame mainFrame;
    private final CodePanel codePanel;
    private final TokenPanel tokenPanel;
    private final CounterPanel counterPanel;
    private final ErrorPanel errorPanel;
    private final JFileChooser fileChooser = new JFileChooser();

    public FileHandler(
            JFrame mainFrame,
            CodePanel codePanel,
            TokenPanel tokenPanel,
            CounterPanel counterPanel,
            ErrorPanel errorPanel
    ) {
        this.mainFrame = mainFrame;
        this.codePanel = codePanel;
        this.tokenPanel = tokenPanel;
        this.counterPanel = counterPanel;
        this.errorPanel = errorPanel;
    }

    public void openFile() {
        int result = fileChooser.showOpenDialog(mainFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                StringBuilder newText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    newText.append(line).append("\n");
                }
                reader.close();
                String fileContent = newText.toString();
                codePanel.setCode(fileContent);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        mainFrame,
                        "No fue posible abrir el archivo seleccionado"
                );
            }
        }
    }

    public void exportFile() {
        System.out.println("export");
    }
}
