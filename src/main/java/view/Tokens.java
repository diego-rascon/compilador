package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;

public class Tokens extends PanelTemplate {

    private final LinkedList<model.Token> tokenList = new LinkedList<>();
    private final String[] columns = {"Estado", "Lexema", "Línea"};
    private final DefaultTableModel tokenTableModel = new DefaultTableModel(columns, 0);
    private final JTable tokenTable = new JTable(tokenTableModel) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Tokens(int padding) {
        super("Tokens", padding);

        tokenTable.getTableHeader().setReorderingAllowed(false);
        tokenTable.getColumnModel().getColumn(0).setPreferredWidth(1);
        tokenTable.getColumnModel().getColumn(2).setPreferredWidth(1);

        final DefaultTableCellRenderer centeredCell = new DefaultTableCellRenderer();
        centeredCell.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

        tokenTable.getColumnModel().getColumn(0).setCellRenderer(centeredCell);
        tokenTable.getColumnModel().getColumn(2).setCellRenderer(centeredCell);

        final JScrollPane scrollPane = new JScrollPane(tokenTable);
        add(scrollPane);
    }

    final public void emptyTokensList() {
        tokenList.clear();
    }

    final public void addToken(int state, String lexeme, int line) {
        final model.Token newToken = new model.Token(state, lexeme, line);
        tokenList.add(newToken);
    }

    public void updateTable() {
        tokenTableModel.setRowCount(0);
        for (model.Token token : tokenList) {
            final Object[] tokenRow = {token.token(), token.lexeme(), token.line()};
            tokenTableModel.addRow(tokenRow);
        }
    }

    public JTable getTokenTable() {
        return tokenTable;
    }
}
