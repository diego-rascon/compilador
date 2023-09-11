package util;

import model.Ambit;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.*;
import view.Errors;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class FileHandler {

    private final JFrame mainFrame;
    private final Code codePanel;
    private final Tokens tokenPanel;
    private final Counters countersPanel;
    private final Errors errorsPanel;
    private final ErrorTypes errorTypesPanel;
    private final JFileChooser fileChooser = new JFileChooser();
    private final String[] ambitHeaders = {"Ámbito", "string", "number", "boolean", "real", "null", "#id", "errores", "total"};

    public FileHandler(JFrame mainFrame, Code codePanel, Tokens tokenPanel, Counters countersPanel, Errors errorsPanel, ErrorTypes errorTypesPanel) {
        this.mainFrame = mainFrame;
        this.codePanel = codePanel;
        this.tokenPanel = tokenPanel;
        this.countersPanel = countersPanel;
        this.errorsPanel = errorsPanel;
        this.errorTypesPanel = errorTypesPanel;
    }

    public void openFile() {
        fileChooser.setDialogTitle("Abrir un archivo de texto");
        final int result = fileChooser.showOpenDialog(mainFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            final File selectedFile = fileChooser.getSelectedFile();
            try {
                final BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                final StringBuilder newText = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    newText.append(line).append("\n");
                }
                reader.close();
                final String fileContent = newText.toString();
                codePanel.setCode(fileContent);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(mainFrame, "No fue posible abrir el archivo seleccionado.");
            }
        }
    }

    public void exportFile() {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            final LinkedHashMap<String, JTable> tablesMap = new LinkedHashMap<>();
            tablesMap.put(tokenPanel.getLabel(), tokenPanel.getTokenTable());
            tablesMap.put(countersPanel.getLabel(), countersPanel.getCounterTable());
            tablesMap.put(errorsPanel.getLabel(), errorsPanel.getErrorTable());
            tablesMap.put(errorTypesPanel.getLabel(), errorTypesPanel.getErrorTypesTable());

            for (Map.Entry<String, JTable> tableEntry : tablesMap.entrySet()) {
                final JTable table = tableEntry.getValue();
                final XSSFSheet sheet = workbook.createSheet(tableEntry.getKey());

                final boolean onCounters = tableEntry.getKey().equals("Contadores") || tableEntry.getKey().equals("Tipos de errores");

                if (onCounters) {
                    final int rowCount = table.getColumnCount();
                    for (int row = 0; row < rowCount; row++) {
                        final XSSFRow sheetRow = sheet.createRow(row);
                        final int columnCount = table.getRowCount();

                        for (int column = 0; column < columnCount; column++) {
                            final XSSFCell sheetCell = sheetRow.createCell(column);
                            final Object cellValue = table.getValueAt(column, row);
                            sheetCell.setCellValue(cellValue.toString());
                        }
                    }
                } else {
                    final JTableHeader tableHeader = table.getTableHeader();
                    final TableColumnModel columnModel = tableHeader.getColumnModel();

                    final int headingsCount = columnModel.getColumnCount();
                    final String[] headers = new String[headingsCount];
                    for (int i = 0; i < headingsCount; i++) {
                        final TableColumn column = columnModel.getColumn(i);
                        headers[i] = column.getHeaderValue().toString();
                    }
                    final XSSFRow headerRow = sheet.createRow(0);
                    for (int i = 0; i < headers.length; i++) {
                        final XSSFCell sheetCell = headerRow.createCell(i);
                        sheetCell.setCellValue(headers[i]);
                    }

                    final int rowCount = table.getRowCount();
                    int rowOffset = 0;
                    for (int row = 0; row < rowCount; row++) {
                        final String lexerValue = table.getValueAt(row, 1).toString();
                        if (lexerValue.startsWith("/*") || lexerValue.startsWith("//")) {
                            rowOffset++;
                            continue;
                        }
                        final XSSFRow sheetRow = sheet.createRow(row + 1 - rowOffset);
                        final int columnCount = table.getColumnCount();

                        for (int column = 0; column < columnCount; column++) {
                            final XSSFCell sheetCell = sheetRow.createCell(column);
                            final Object cellValue = table.getValueAt(row, column);
                            sheetCell.setCellValue(cellValue.toString());
                        }
                    }
                }
            }

            final XSSFSheet sheet = workbook.createSheet("Ámbitos");
            final XSSFRow sheetRow = sheet.createRow(0);
            for (int i = 0; i < ambitHeaders.length; i++) {
                final XSSFCell sheetCell = sheetRow.createCell(i);
                sheetCell.setCellValue(ambitHeaders[i]);
            }

            LinkedList<Ambit> ambits = codePanel.getAmbits();
            for (int i = 0; i < ambits.size(); i++) {
                var column = 0;
                final XSSFRow row = sheet.createRow(i + 1);
                final XSSFCell ambitCell = row.createCell(column++);
                ambitCell.setCellValue(ambits.get(i).getId());
                final XSSFCell stringCell = row.createCell(column++);
                stringCell.setCellValue(ambits.get(i).getStrings());
                final XSSFCell numberCell = row.createCell(column++);
                numberCell.setCellValue(ambits.get(i).getNumbers());
                final XSSFCell boolCell = row.createCell(column++);
                boolCell.setCellValue(ambits.get(i).getBooleans());
                final XSSFCell realCell = row.createCell(column++);
                realCell.setCellValue(ambits.get(i).getReals());
                final XSSFCell nullCell = row.createCell(column++);
                nullCell.setCellValue(ambits.get(i).getNulls());
                final XSSFCell customCell = row.createCell(column++);
                customCell.setCellValue(ambits.get(i).getCustoms());
                final XSSFCell errorCell = row.createCell(column++);
                errorCell.setCellValue(ambits.get(i).getErrors());
                final XSSFCell totalCell = row.createCell(column);
                totalCell.setCellValue(ambits.get(i).getTotal());
            }

            File defaultFile = new File("Compilador - Diego Rascón (20130375)");

            fileChooser.setSelectedFile(defaultFile);
            fileChooser.setDialogTitle("Exportar los resultados a una hoja de cálculo");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Excel (*.xlsx)", "xlsx"));
            final int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".xlsx")) {
                    filePath += ".xlsx";
                }
                try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                    workbook.write(outputStream);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mainFrame, "No fue posible exportar el archivo.");
        }
    }
}