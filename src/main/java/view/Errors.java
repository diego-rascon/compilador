package view;

import model.ErrorType;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;

public class Errors extends PanelTemplate {

    private final LinkedList<model.Error> errorList = new LinkedList<>();
    private final String[] columns = {"Error", "Descripción", "Lexema", "Tipo", "Linea"};
    private final DefaultTableModel errorTableModel = new DefaultTableModel(columns, 0);
    final JTable errorTable = new JTable(errorTableModel) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Errors(int padding) {
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
            case 506 -> description = "se esperaba un \"fuction\".";
            case 507 -> description = "Se esperaba un \"class\".";
            case 508 -> description = "Se esperaba un ;.";
            case 509 -> description = "Se esperaba un identificador.";
            case 510 -> description = "Se esperaba un ,.";
            case 511 -> description = "Se esperaba un :.";
            case 512 -> description = "Se esperaba un \"get\" o un \"set\".";
            case 513 -> description = "Se esperaba una variable y un tipo.";
            case 514 -> description = "Se esperaba un =.";
            case 515 -> description = "Se esperaba una constante.";
            case 516 -> description = "Se esperaba un \"let\".";
            case 517 -> description = "Se esperaba un \"interface\".";
            case 518 -> description = "Se esperaba un = o un :.";
            case 519 -> description = "Se esperaba un ( o un \"fuction\".";
            case 520 -> description = "Se esperaba un identificador, un tipo o un \"Array\".";
            case 521 -> description = "Se esperaba un identificador o un tipo.";
            case 522 -> description = "Se esperaba un [ o un \"new\".";
            case 523 -> description = "Se esperaba un ++, --, !, ~, (, una constante, un identificador o una función.";
            case 524 -> description = "Se esperana un { o una constante.";
            case 525 -> description = "Se esperana un =, <, o \"Map\".";
            case 526 -> description = "Se esperana un \"Map\".";
            case 527 -> description = "Se esperaba un operador de asignación.";
            case 528 -> description = "Se esperaba un ++, --, {, (, !, ~, \"if\", \"switch\", \"for\", \"while\", \"do\", \"fuction\", \"return\", una constante o un identificador.";
            case 529 -> description = "Se esperaba un \"read\" o un \"log\".";
            case 530 -> description = "Se esperaba un \"else\".";
            case 531 -> description = "Se esperaba un \"case\".";
            case 532 -> description = "Se esperaba un \"case\" o un \"default\".";
            case 533 -> description = "Se esperaba un ++, --, (, !, ~, \"let\", \"fuction\", una constante o un identificador.";
            case 534 -> description = "Se esperaba un \"of\" o un \"in\".";
            case 535, 536 -> description = "Se esperaba un método de cadena.";
            case 537 -> description = "Se esperaba un [.";
            case 538 -> description = "Se esperaba un | o un ||.";
            case 539 -> description = "Se esperaba un &, && o un ^.";
            case 540 -> description = "Se esperaba un <, <=, >, =>, ==, ===, != o !==.";
            case 541 -> description = "Se esperaba un -, +, <<, >>, o un >>>.";
            case 542 -> description = "Se esperaba un *, /, o un %.";
            case 543 -> description = "Se esperaba un **.";
            case 545 -> description = "Se esperaba un ++ o un --.";
            case 546 -> description = "Se esperaba un ( o un [.";
            case 547 -> description = "Se esperaba un ?.";
            case 548 -> description = "Se esperaba un ! o un ~.";
            case 549 -> description = "Variable no declarada.";
        }
        String type = "";
        switch (errorType) {
            case LEXIC -> type = "Léxico";
            case SINTAXIS -> type = "Sintaxis";
            case AMBIT -> type = "Ámbito";
        }
        final model.Error newError = new model.Error(error, description, lexeme, type, line);
        errorList.add(newError);
    }

    public void updateTable() {
        errorTableModel.setRowCount(0);
        for (model.Error error : errorList) {
            final Object[] errorRow = {error.error(), error.description(), error.lexeme(), error.type(), error.line()};
            errorTableModel.addRow(errorRow);
        }
    }

    public JTable getErrorTable() {
        return errorTable;
    }
}
