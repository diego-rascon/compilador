package util;

import model.Ambit;
import model.Operation;
import model.Quadruple;
import model.Semantics;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import view.*;

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
    private final String[] semHeaders = {"Linea", "Strings", "Numbers", "Booleans", "Reales", "Variables #", "Voids", "Variants", "Asignaciones", "Errores"};
    private final String[] sem2Headers = {"Regla", "Tope de pila", "Valor real", "Linea", "Estado", "Ámbito"};
    private final String[] quadruplesHeaders = {
            "temp booleans", "temp numbers", "temp reals", "temp strings", "temp nulls", "temp variants",
            "calls", "assignaciones", "op relacional", "op lógicas", "op aritméticas", "op unarias",
            "jf", "jt", "jmp",
            "e if", "e for", "e while", "e switch", "e función", "main"
    };

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

            final XSSFSheet ambSheet = workbook.createSheet("Ámbitos");
            int rowNum = 0;
            XSSFRow sheetRow = ambSheet.createRow(rowNum++);
            for (int i = 0; i < ambitHeaders.length; i++) {
                final XSSFCell sheetCell = sheetRow.createCell(i);
                sheetCell.setCellValue(ambitHeaders[i]);
            }

            int[] ambTotals = {0, 0, 0, 0, 0, 0, 0, 0, 0};

            LinkedList<Ambit> ambits = codePanel.getAmbits();
            for (Ambit ambit : ambits) {
                var column = 0;
                final XSSFRow row = ambSheet.createRow(rowNum++);

                final XSSFCell ambitCell = row.createCell(column++);
                ambitCell.setCellValue(ambit.getId());

                final XSSFCell stringCell = row.createCell(column++);
                int tempStrings = ambit.getStrings();
                ambTotals[0] += tempStrings;
                stringCell.setCellValue(tempStrings);

                final XSSFCell numberCell = row.createCell(column++);
                int tempNumbers = ambit.getNumbers();
                ambTotals[1] += tempNumbers;
                numberCell.setCellValue(tempNumbers);

                final XSSFCell boolCell = row.createCell(column++);
                int tempBooleans = ambit.getBooleans();
                ambTotals[2] += tempBooleans;
                boolCell.setCellValue(tempBooleans);

                final XSSFCell realCell = row.createCell(column++);
                int tempReals = ambit.getReals();
                ambTotals[3] += tempReals;
                realCell.setCellValue(tempReals);

                final XSSFCell nullCell = row.createCell(column++);
                int tempNulls = ambit.getNulls();
                ambTotals[4] += tempNulls;
                nullCell.setCellValue(tempNulls);

                final XSSFCell customCell = row.createCell(column++);
                int tempCustoms = ambit.getCustoms();
                ambTotals[5] += tempCustoms;
                customCell.setCellValue(tempCustoms);

                final XSSFCell voidCell = row.createCell(column++);
                int tempVoids = ambit.getVoids();
                ambTotals[6] += tempVoids;
                voidCell.setCellValue(tempVoids);

                final XSSFCell errorCell = row.createCell(column++);
                int tempErrors = ambit.getErrors();
                ambTotals[7] += tempErrors;
                errorCell.setCellValue(tempErrors);

                final XSSFCell totalCell = row.createCell(column);
                int tempTotals = ambit.getTotal();
                ambTotals[8] += tempTotals;
                totalCell.setCellValue(tempTotals);
            }

            int totalsColumns = 0;
            final XSSFRow ambTotalsRow = ambSheet.createRow(rowNum);
            final XSSFCell ambLabelCell = ambTotalsRow.createCell(totalsColumns++);
            ambLabelCell.setCellValue("total");

            for (int total : ambTotals) {
                final XSSFCell totalCell = ambTotalsRow.createCell(totalsColumns++);
                totalCell.setCellValue(total);
            }

            final XSSFSheet semSheet = workbook.createSheet("Semántica 1");
            rowNum = 0;
            sheetRow = semSheet.createRow(rowNum++);
            for (int i = 0; i < semHeaders.length; i++) {
                final XSSFCell sheetCell = sheetRow.createCell(i);
                sheetCell.setCellValue(semHeaders[i]);
            }

            int[] semTotals = {0, 0, 0, 0, 0, 0, 0, 0, 0};

            LinkedList<Operation> operations = codePanel.getOperations();
            for (Operation operation : operations) {
                int columns = 0;
                final XSSFRow row = semSheet.createRow(rowNum++);

                final XSSFCell lineCell = row.createCell(columns++);
                lineCell.setCellValue(operation.getLine());

                final XSSFCell stringsCell = row.createCell(columns++);
                int tempStrings = operation.getStrings();
                semTotals[0] += tempStrings;
                stringsCell.setCellValue(tempStrings);

                final XSSFCell numbersCell = row.createCell(columns++);
                int tempNumbers = operation.getNumbers();
                semTotals[1] += tempNumbers;
                numbersCell.setCellValue(tempNumbers);

                final XSSFCell booleansCell = row.createCell(columns++);
                int tempBooleans = operation.getBooleans();
                semTotals[2] += tempBooleans;
                booleansCell.setCellValue(tempBooleans);

                final XSSFCell realsCell = row.createCell(columns++);
                int tempReals = operation.getReals();
                semTotals[3] += tempReals;
                realsCell.setCellValue(tempReals);

                final XSSFCell customsCell = row.createCell(columns++);
                int tempCustoms = operation.getCustoms();
                semTotals[4] += tempCustoms;
                customsCell.setCellValue(tempCustoms);

                final XSSFCell voidsCell = row.createCell(columns++);
                int tempVoids = operation.getVoids();
                semTotals[5] += tempVoids;
                voidsCell.setCellValue(tempVoids);

                final XSSFCell variantsCell = row.createCell(columns++);
                int tempVariants = operation.getVariants();
                semTotals[6] += tempVariants;
                variantsCell.setCellValue(tempVariants);

                final XSSFCell assignationsCell = row.createCell(columns++);
                String tempAssignation = operation.getAssignation();
                semTotals[7]++;
                assignationsCell.setCellValue(tempAssignation);

                final XSSFCell errorsCell = row.createCell(columns);
                int tempErrors = operation.getErrors();
                semTotals[8] += tempErrors;
                errorsCell.setCellValue(tempErrors);
            }

            totalsColumns = 0;
            final XSSFRow semTotalsRow = semSheet.createRow(rowNum);
            final XSSFCell semLabelCell = semTotalsRow.createCell(totalsColumns++);
            semLabelCell.setCellValue("Totales");

            for (int total : semTotals) {
                final XSSFCell totalCell = semTotalsRow.createCell(totalsColumns++);
                totalCell.setCellValue(total);
            }

            final XSSFSheet sem2Sheet = workbook.createSheet("Semántica 2");
            rowNum = 0;
            sheetRow = sem2Sheet.createRow(rowNum++);
            for (int i = 0; i < sem2Headers.length; i++) {
                final XSSFCell sheetCell = sheetRow.createCell(i);
                sheetCell.setCellValue(sem2Headers[i]);
            }

            LinkedList<Semantics> semanticsList = codePanel.getSemanticsList();
            for (Semantics semanticsElement : semanticsList) {
                int columns = 0;
                final XSSFRow row = sem2Sheet.createRow(rowNum++);

                final XSSFCell ruleCell = row.createCell(columns++);
                ruleCell.setCellValue(semanticsElement.getRule());

                final XSSFCell topStackCell = row.createCell(columns++);
                topStackCell.setCellValue(semanticsElement.getRealValue());

                final XSSFCell valueCell = row.createCell(columns++);
                valueCell.setCellValue(semanticsElement.getTopStack());

                final XSSFCell lineCell = row.createCell(columns++);
                lineCell.setCellValue(semanticsElement.getLine());

                final XSSFCell stateCell = row.createCell(columns++);
                stateCell.setCellValue(semanticsElement.isAccepted() ? "Aceptado" : "Error");

                final XSSFCell ambitCell = row.createCell(columns);
                ambitCell.setCellValue(semanticsElement.getAmbit());
            }

            // Quadruples

            final XSSFSheet quadSheet = workbook.createSheet("Cuádruplos");
            rowNum = 0;
            sheetRow = quadSheet.createRow(rowNum++);
            for (int i = 0; i < quadruplesHeaders.length; i++) {
                final XSSFCell sheetCell = sheetRow.createCell(i + 1);
                if (i == 0) {
                    final XSSFCell ambitCell = sheetRow.createCell(i);
                    ambitCell.setCellValue("ámbito");
                }
                sheetCell.setCellValue(quadruplesHeaders[i]);
            }

            int[] quadTotals = new int[quadruplesHeaders.length];
            for (int total : quadTotals) total = 0;

            LinkedList<Quadruple> quadruples = codePanel.getQuadruples();

            for (Quadruple quadruple : quadruples) {
                int columns = 0;
                final XSSFRow row = quadSheet.createRow(rowNum++);

                final XSSFCell ambitCell = row.createCell(columns++);
                ambitCell.setCellValue(quadruple.getAmbit());

                final XSSFCell boolCell = row.createCell(columns++);
                int tempBools = quadruple.getTempBooleans();
                quadTotals[0] += tempBools;
                boolCell.setCellValue(tempBools);

                final XSSFCell numCell = row.createCell(columns++);
                int tempNums = quadruple.getTempNumbers();
                quadTotals[1] += tempNums;
                numCell.setCellValue(tempNums);

                final XSSFCell realCell = row.createCell(columns++);
                int tempReals = quadruple.getTempReals();
                quadTotals[2] += tempReals;
                realCell.setCellValue(tempReals);

                final XSSFCell stringCell = row.createCell(columns++);
                int tempStrings = quadruple.getTempStrings();
                quadTotals[3] += tempStrings;
                stringCell.setCellValue(tempStrings);

                final XSSFCell nullCell = row.createCell(columns++);
                int tempNulls = quadruple.getTempNulls();
                quadTotals[4] += tempNulls;
                nullCell.setCellValue(tempNulls);

                final XSSFCell variantCell = row.createCell(columns++);
                int tempVariants = quadruple.getTempVariants();
                quadTotals[5] += tempVariants;
                variantCell.setCellValue(tempVariants);

                final XSSFCell callsCell = row.createCell(columns++);
                int tempCalls = quadruple.getCalls();
                quadTotals[6] += tempCalls;
                callsCell.setCellValue(tempCalls);

                final XSSFCell assignationsCell = row.createCell(columns++);
                int assignations = quadruple.getAssignations();
                quadTotals[7] += assignations;
                assignationsCell.setCellValue(assignations);

                final XSSFCell relOpCell = row.createCell(columns++);
                int relOps = quadruple.getRelationalOperators();
                quadTotals[8] += relOps;
                relOpCell.setCellValue(relOps);

                final XSSFCell logOpCell = row.createCell(columns++);
                int logOps = quadruple.getLogicalOperators();
                quadTotals[9] += logOps;
                logOpCell.setCellValue(logOps);

                final XSSFCell aritOpCell = row.createCell(columns++);
                int aritOps = quadruple.getArithmeticOperators();
                quadTotals[10] += aritOps;
                aritOpCell.setCellValue(aritOps);

                final XSSFCell unarOpCell = row.createCell(columns++);
                int unarOps = quadruple.getUnaryOperation();
                quadTotals[11] += unarOps;
                unarOpCell.setCellValue(unarOps);

                final XSSFCell jfCell = row.createCell(columns++);
                int jf = quadruple.getJumpFalses();
                quadTotals[12] += jf;
                jfCell.setCellValue(jf);

                final XSSFCell jtCell = row.createCell(columns++);
                int jt = quadruple.getJumpTrues();
                quadTotals[13] += jt;
                jtCell.setCellValue(jt);

                final XSSFCell jumpsCell = row.createCell(columns++);
                int jumps = quadruple.getJumps();
                quadTotals[14] += jumps;
                jumpsCell.setCellValue(jumps);

                final XSSFCell ifCell = row.createCell(columns++);
                int ifs = quadruple.getIfLabels();
                quadTotals[15] += ifs;
                ifCell.setCellValue(ifs);

                final XSSFCell forCell = row.createCell(columns++);
                int fors = quadruple.getForLabels();
                quadTotals[16] += fors;
                forCell.setCellValue(fors);

                final XSSFCell whileCell = row.createCell(columns++);
                int whiles = quadruple.getWhileLabels();
                quadTotals[17] += whiles;
                whileCell.setCellValue(whiles);

                final XSSFCell switchCell = row.createCell(columns++);
                int switches = quadruple.getSwitchLabels();
                quadTotals[18] += switches;
                switchCell.setCellValue(switches);

                final XSSFCell functionCell = row.createCell(columns++);
                int functions = quadruple.getFunctionLabels();
                quadTotals[19] += functions;
                functionCell.setCellValue(functions);

                final XSSFCell mainCell = row.createCell(columns++);
                int mains = quadruple.getMainLabels();
                quadTotals[20] += mains;
                mainCell.setCellValue(mains);
            }

            int quadTotalsColumns = 0;
            final XSSFRow quadTotalsRow = quadSheet.createRow(rowNum);
            final XSSFCell quadLabelCell = quadTotalsRow.createCell(quadTotalsColumns++);
            quadLabelCell.setCellValue("total");

            for (int total : quadTotals) {
                final XSSFCell totalCell = quadTotalsRow.createCell(quadTotalsColumns++);
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