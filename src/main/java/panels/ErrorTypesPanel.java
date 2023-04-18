package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorTypesPanel extends PanelTemplate {

    private final LinkedHashMap<String, Integer> counterMap = new LinkedHashMap<>();
    private final String[] errorTypes = {"Operadores de postfix", "Operadores lógicos binarios"};
    private final String[] columns = {"Tipo", "Cantidad"};
    private final DefaultTableModel errorTypesTableModel = new DefaultTableModel(columns, 0);
    private final JTable errorTypesTable = new JTable(errorTypesTableModel) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ErrorTypesPanel(int padding) {
        super("Tipos de errores", padding);

        errorTypesTable.getTableHeader().setReorderingAllowed(false);
        errorTypesTable.getColumnModel().getColumn(1).setPreferredWidth(1);

        final DefaultTableCellRenderer centeredCell = new DefaultTableCellRenderer();
        centeredCell.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        errorTypesTable.getColumnModel().getColumn(1).setCellRenderer(centeredCell);

        final JScrollPane scrollPane = new JScrollPane(errorTypesTable);
        add(scrollPane);

        initTable();
    }

    final void initTable() {
        for (String errorType : errorTypes) {
            counterMap.put(errorType, 0);
            final Object[] counterRow = {errorType, 0};
            errorTypesTableModel.addRow(counterRow);
        }
    }

    public void restartCounter() {
        for (Map.Entry<String, Integer> counterEntry : counterMap.entrySet()) {
            counterEntry.setValue(0);
        }
    }

    public void addCounter(int state) {
        switch (state) {
            case -2, -5 -> {
                String rowLabel = errorTypes[0];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -7, -8, -10, -13 -> {
                String rowLabel = errorTypes[1];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -15, -16, -17, -18 -> {
                String rowLabel = errorTypes[2];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -1, -4, -19, -22, -26 -> {
                String rowLabel = errorTypes[3];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -20 -> {
                String rowLabel = errorTypes[4];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -29, -33, -34 -> {
                String rowLabel = errorTypes[5];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -28, -32, -31, -37, -39, -43 -> {
                String rowLabel = errorTypes[6];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -40, -44 -> {
                String rowLabel = errorTypes[7];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -42, -11, -9 -> {
                String rowLabel = errorTypes[8];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -45 -> {
                String rowLabel = errorTypes[9];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -38, -3, -6, -21, -24, -27, -12, -14, -30, -36, -35, -41 -> {
                String rowLabel = errorTypes[10];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -46, -47, -48, -49, -50, -51 -> {
                String rowLabel = errorTypes[11];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -58, -59, -60, -61, -62, -63, -64, -65, -66, -67, -68, -69, -70, -71, -72, -73, -74, -75, -76, -77, -78, -79, -80, -81, -82, -83, -84, -85, -86, -87, -88, -89, -90, -91, -92, -93, -94, -95, -96, -97, -98, -99, -100, -101, -102, -103 -> {
                String rowLabel = errorTypes[12];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -25, -23 -> {
                String rowLabel = errorTypes[13];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -52, -53 -> {
                String rowLabel = errorTypes[14];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -54 -> {
                String rowLabel = errorTypes[15];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -55, -56 -> {
                String rowLabel = errorTypes[16];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -104, -105 -> {
                String rowLabel = errorTypes[17];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -106 -> {
                String rowLabel = errorTypes[18];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -57 -> {
                String rowLabel = errorTypes[19];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case 500, 501, 502, 503, 504, 505 -> {
                String rowLabel = errorTypes[20];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
        }
    }

    public void updateTable() {
        int row = 0;
        for (Map.Entry<String, Integer> counterEntry : counterMap.entrySet()) {
            final Integer newValue = counterEntry.getValue();
            errorTypesTableModel.setValueAt(newValue, row++, 1);
        }
    }

    public JTable getErrorTypesTable() {
        return errorTypesTable;
    }
}
