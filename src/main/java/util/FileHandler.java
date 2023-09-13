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
    private final String[] ambitHeaders = {"Ámbito", "string", "number", "boolean", "real", "null", "#id", "void", "errores", "total"};

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
            int rowNum = 0;
            final XSSFRow sheetRow = sheet.createRow(rowNum++);
            for (int i = 0; i < ambitHeaders.length; i++) {
                final XSSFCell sheetCell = sheetRow.createCell(i);
                sheetCell.setCellValue(ambitHeaders[i]);
            }

            int[] totals = {0, 0, 0, 0, 0, 0, 0, 0, 0};

            LinkedList<Ambit> ambits = codePanel.getAmbits();
            for (Ambit ambit : ambits) {
                var column = 0;
                final XSSFRow row = sheet.createRow(rowNum++);

                final XSSFCell ambitCell = row.createCell(column++);
                ambitCell.setCellValue(ambit.getId());

                final XSSFCell stringCell = row.createCell(column++);
                int tempStrings = ambit.getStrings();
                totals[0] += tempStrings;
                stringCell.setCellValue(tempStrings);

                final XSSFCell numberCell = row.createCell(column++);
                int tempNumbers = ambit.getNumbers();
                totals[1] += tempNumbers;
                numberCell.setCellValue(tempNumbers);

                final XSSFCell boolCell = row.createCell(column++);
                int tempBooleans = ambit.getBooleans();
                totals[2] += tempBooleans;
                boolCell.setCellValue(tempBooleans);

                final XSSFCell realCell = row.createCell(column++);
                int tempReals = ambit.getReals();
                totals[3] += tempReals;
                realCell.setCellValue(tempReals);

                final XSSFCell nullCell = row.createCell(column++);
                int tempNulls = ambit.getNulls();
                totals[4] += tempNulls;
                nullCell.setCellValue(tempNulls);

                final XSSFCell customCell = row.createCell(column++);
                int tempCustoms = ambit.getCustoms();
                totals[5] += tempCustoms;
                customCell.setCellValue(tempCustoms);

                final XSSFCell voidCell = row.createCell(column++);
                int tempVoids = ambit.getVoids();
                totals[6] += tempVoids;
                voidCell.setCellValue(tempVoids);

                final XSSFCell errorCell = row.createCell(column++);
                int tempErrors = ambit.getErrors();
                totals[7] += tempErrors;
                errorCell.setCellValue(tempErrors);

                final XSSFCell totalCell = row.createCell(column);
                int tempTotals = ambit.getTotal();
                totals[8] += tempTotals;
                totalCell.setCellValue(tempTotals);
            }

            int totalsColumns = 0;
            final XSSFRow totalsRow = sheet.createRow(rowNum);
            final XSSFCell labelCell = totalsRow.createCell(totalsColumns++);
            labelCell.setCellValue("total");

            for (int total : totals) {
                final XSSFCell totalCell = totalsRow.createCell(totalsColumns++);
                totalCell.setCellValue(total);
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