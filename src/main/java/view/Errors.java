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
            case 550 -> description = "Variable duplicada.";
            case 551 -> description = "Variable let duplicada.";
            case 552 -> description = "Arreglo duplicado.";
            case 553 -> description = "Función duplicada.";
            case 554 -> description = "Función anónima duplicada.";
            case 555 -> description = "Método duplicado.";
            case 556 -> description = "Método anónimo duplicado.";
            case 557 -> description = "Clase duplicada.";
            case 558 -> description = "Clase anónima duplicada.";
            case 559 -> description = "Interfaz duplicada.";
            case 560 -> description = "Método get duplicado.";
            case 561 -> description = "Método set duplicado.";
            case 600 -> description = "Suma de tipos no compatibles.";
            case 601 -> description = "Resta de tipos no compatibles.";
            case 602 -> description = "Multiplicación de tipos no compatibles.";
            case 603 -> description = "División de tipos no compatibles.";
            case 604 -> description = "División de tipos no compatibles.";
            case 605 -> description = "Comparación de tipos no compatibles.";
            case 606, 607 -> description = "Igualación de tipos no compatibles.";
            case 608 -> description = "Operación lógica con tipos no compatibles.";
            case 609 -> description = "Asignación de diferentes tipos.";
            case 1010, 1011, 1012 -> description = "Se esperaba un boolean.";
            case 1020, 1021, 1022 -> description = "Operación aritmética no válida.";
            case 1030 -> description = "Se esperaba un number o un string.";
            case 1031 -> description = "Se esperaba un number o un string.";
            case 1040, 1060 -> description = "Se esperaba una dimensión correcta.";
            case 1050 -> description = "Se esperaba un number.";
            case 1080 -> description = "Se esperaba una asignación.";
            case 1081 -> description = "Se esperaba una comparación.";
            case 1082 -> description = "Se esperaba un incremento/decremento.";
            case 1083 -> description = "Se esperaba un string.";
            case 1084 -> description = "Se esperaba un array.";
            case 1090 -> description = "Se esperaba una variable o un array.";
            case 1100 -> description = "Se enviaron menos parámetros de los requeridos.";
            case 1110 -> description = "Se enviaron más parámetros de los requeridos.";
            case 1120 -> description = "Parámetro de tipo incorrecto.";
            case 1130 -> description = "Se esperaba un procedimiento.";
            case 1140 -> description = "Se esperaba una función.";
            case 1160 -> description = "La función debe regresar un valor.";
            case 1170 -> description = "El procedimiento no puede regresar un valor.";
        }
        String type = "";
        switch (errorType) {
            case LEXIC -> type = "Léxico";
            case SINTAXIS -> type = "Sintaxis";
            case AMBIT -> type = "Ámbito";
            case SEMANTICS_1 -> type = "Semántica 1";
            case SEMANTICS_2 -> type = "Semántica 2";
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
