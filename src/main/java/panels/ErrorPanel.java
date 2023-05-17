package panels;

import model.Error;
import model.ErrorType;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;

public class ErrorPanel extends PanelTemplate {

    private final LinkedList<Error> errorList = new LinkedList<>();
    private final String[] columns = {"Error", "Descripción", "Lexema", "Tipo", "Linea"};
    private final DefaultTableModel errorTableModel = new DefaultTableModel(columns, 0);
    final JTable errorTable = new JTable(errorTableModel) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ErrorPanel(int padding) {
        super("Errores", padding);

        errorTable.getTableHeader().setReorderingAllowed(false);
        errorTable.getColumnModel().getColumn(0).setPreferredWidth(1);
        errorTable.getColumnModel().getColumn(3).setPreferredWidth(1);
        errorTable.getColumnModel().getColumn(4).setPreferredWidth(1);

        final DefaultTableCellRenderer centeredCell = new DefaultTableCellRenderer();
        centeredCell.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        errorTable.getColumnModel().getColumn(0).setCellRenderer(centeredCell);
        errorTable.getColumnModel().getColumn(4).setCellRenderer(centeredCell);

        final JScrollPane scrollPane = new JScrollPane(errorTable);
        add(scrollPane);
    }

    final public void emptyErrorsList() {
        errorList.clear();
    }

    final public void addError(int error, String lexeme, ErrorType errorType, int line) {
        String description = "Caracter no válido";
        switch (error) {
            case 501 -> description = "Se esperaba un \".";
            case 502 -> description = "Se esperaba un '.";
            case 503 -> description = "Se esperaba un número.";
            case 504 -> description = "Se esperaba un \"+\", un \"-\" o un número.";
            case 505 -> description = "Se esperaba que se cerrara el comentario con */.";
            case 510 -> description = "Se esperaba un ,.";
            case 523 -> description = "Se esperaba un ++, --, ~, !, (, ), \"\", '', un número, un identificador, un valor o un método.";
            case 543, 547 -> description = "Se esperaba un operador o una comparación";
            case 548 -> description = "Se esperaba que con ~.";
        }
        String type = "";
        switch (errorType) {
            case LEXIC -> type = "Léxico";
            case SINTAXIS -> type = "Sintaxis";
        }
        final Error newError = new Error(error, description, lexeme, type, line);
        errorList.add(newError);
    }

    public void updateTable() {
        errorTableModel.setRowCount(0);
        for (Error error : errorList) {
            final Object[] errorRow = {error.error(), error.description(), error.lexeme(), error.type(), error.line()};
            errorTableModel.addRow(errorRow);
        }
    }

    public JTable getErrorTable() {
        return errorTable;
    }
}
