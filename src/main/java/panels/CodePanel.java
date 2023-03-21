package panels;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
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
import java.util.Arrays;

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
        } catch (IOException ioe) { // Never happens
            ioe.printStackTrace();
        }

        add(scrollPane, BorderLayout.CENTER);
        loadMatrix();
    }

    public void compile() {
        final String code = codeArea.getText();
        //int state = 0;
        for (int i = 0; i < code.length(); i++) {
            final char character = code.charAt(i);
            System.out.println(character);
            System.out.println(Arrays.deepToString(matrix));
        }
    }

    final void loadMatrix() {
        try {
            FileInputStream matrixFile = new FileInputStream("./src/main/resources/matrix.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(matrixFile);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i < rowCount; i++) {
                XSSFRow row = sheet.getRow(i);
                int columnCount = row.getLastCellNum();

                matrix = new int[rowCount - 1][columnCount - 1];

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

    public RSyntaxTextArea getCodeArea() {
        return codeArea;
    }
}
