package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ErrorsPanel extends PanelTemplate {

    private final String[] columns = {"Error", "Lexema", "Tipo", "Línea", "Descripción"};
    private final DefaultTableModel errorsTableModel = new DefaultTableModel(columns, 0);
    private final JTable errorsTable = new JTable(errorsTableModel);

    public ErrorsPanel() {
        super("Errores");
        final JScrollPane scrollPane = new JScrollPane(errorsTable);
        errorsTable.getTableHeader().setReorderingAllowed(false);
        add(scrollPane);
    }

    public JTable getErrorsTable() {
        return errorsTable;
    }
}
