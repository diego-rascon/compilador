package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorTypesPanel extends PanelTemplate {

    private final LinkedHashMap<String, Integer> counterMap = new LinkedHashMap<>();
    private final String[] columns = {"Tipo", "Cantidad"};
    private final String[] errorTypes = {"Léxico", "Sintaxis"};
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
