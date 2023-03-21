package panels;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class CodePanel extends PanelTemplate {

    private int[][] matrix;
    private final RSyntaxTextArea codeArea = new RSyntaxTextArea();

    public CodePanel() {
        super("Código");

        final RTextScrollPane scrollPane = new RTextScrollPane(codeArea);

        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_TYPESCRIPT);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setBracketMatchingEnabled(true);
        codeArea.setAntiAliasingEnabled(true);

        try {
            Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
            theme.apply(codeArea);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo cargar el tema del editor de código.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        add(scrollPane, BorderLayout.CENTER);
        loadMatrix();
    }

    public void compile() {
        final String code = codeArea.getText() + "\n";
        int state = 0;
        for (int i = 0; i < code.length(); i++) {
            final char character = code.charAt(i);
            int column = getColumn(character);
            state = matrix[state][column];

            if (state < 0) {
                switch (state) {
                    case -1 -> System.out.println("+");
                    case -2 -> System.out.println("++");
                    case -3 -> System.out.println("+=");
                    case -4 -> System.out.println("-");
                    case -5 -> System.out.println("--");
                    case -6 -> System.out.println("-=");
                    case -7 -> System.out.println("~");
                    case -8 -> System.out.println("|");
                    case -9 -> System.out.println("||");
                }
            }
            if (state >= 500) {
                state = 0;
            }
        }
    }

    private int getColumn(char character) {
        switch (character) {
            case '+' -> {
                return 0;
            }
            case '-' -> {
                return 1;
            }
            case '~' -> {
                return 2;
            }
            case '|' -> {
                return 3;
            }
            case '\n' -> {
                return 31;
            }
            default -> {
                return 30;
            }
        }
    }

    final void loadMatrix() {
        try {
            FileInputStream matrixFile = new FileInputStream("./src/main/resources/matrix.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(matrixFile);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                int columnCount = row.getLastCellNum();

                if (i == 1) matrix = new int[rowCount][columnCount - 1];

                for (int j = 1; j < columnCount; j++) {
                    Cell cell = row.getCell(j);
                    int cellValue = (int) cell.getNumericCellValue();
                    matrix[i - 1][j - 1] = cellValue;
                }
            }
        } catch (IOException e) {
            final Object[] options = {"OK"};
            final int selection = JOptionPane.showOptionDialog(
                    JOptionPane.getFrameForComponent(this),
                    "El archivo .xlsx de la matríz no pudo ser encontrado.",
                    "Error",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            if (selection == JOptionPane.OK_OPTION) System.exit(0);
        }
    }
}
