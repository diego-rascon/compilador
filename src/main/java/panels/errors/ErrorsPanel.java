package panels.errors;

import panels.PanelTemplate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ErrorsPanel extends PanelTemplate {

    private final List<Error> errorList = new ArrayList<>();
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
