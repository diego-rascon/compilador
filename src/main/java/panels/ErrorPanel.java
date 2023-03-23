package panels;

import model.Error;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;

public class ErrorPanel extends PanelTemplate {

    private final LinkedList<Error> errorList = new LinkedList<>();
    private final String[] columns = {"Error", "Descripción", "Lexema", "Tipo", "Linea"};
    private final DefaultTableModel errorTableModel = new DefaultTableModel(columns, 0);

    public ErrorPanel() {
        super("Errores");

        final JTable errorTable = new JTable(errorTableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        errorTable.getTableHeader().setReorderingAllowed(false);

        final JScrollPane scrollPane = new JScrollPane(errorTable);
        add(scrollPane);
    }

    final public void emptyErrorsList() {
        errorList.clear();
    }

    final public void addError(int error, String lexeme, int line) {
        String description = "Caracter no válido";
        switch (error) {
            case 501 -> description = "Se esperaba un \"";
            case 502 -> description = "Se esperaba un '";
            case 503 -> description = "Se esperaba un número";
            case 504 -> description = "Se esperaba un \"+\", un \"-\" o un número";
            case 505 -> description = "Se esperaba que se cerrara el comentario con */";
        }
        final String type = "Léxico";
        final Error newError = new Error(error, description, lexeme, type, line);
        errorList.add(newError);
    }

    public void updateTable() {
        errorTableModel.setRowCount(0);
        for (Error error : errorList) {
            final Object[] errorRow = {
                    error.error(),
                    error.description(),
                    error.lexeme(),
                    error.type(),
                    error.line()
            };
            errorTableModel.addRow(errorRow);
        }
    }
}
