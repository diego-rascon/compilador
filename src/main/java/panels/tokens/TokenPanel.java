package panels.tokens;

import panels.PanelTemplate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;

public class TokenPanel extends PanelTemplate {

    private final LinkedList<Token> tokenList = new LinkedList<>();
    private final String[] columns = {"Token", "Lexema", "Línea"};
    private final DefaultTableModel tokenTableModel = new DefaultTableModel(columns, 0);

    public TokenPanel() {
        super("Tokens");
        JTable tokensTable = new JTable(tokenTableModel);
        final JScrollPane scrollPane = new JScrollPane(tokensTable);
        tokensTable.getTableHeader().setReorderingAllowed(false);
        add(scrollPane);
    }

    final public void emptyTokensList() {
        tokenList.clear();
    }

    final public void addToken(int token, String lexeme, int line) {
        final Token newToken = new Token(token, lexeme, line);
        tokenList.add(newToken);
    }

    public void updateTable() {
        tokenTableModel.setRowCount(0);
        for (Token token : tokenList) {
            final Object[] tokenData = {token.token(), token.lexeme(), token.line()};
            tokenTableModel.addRow(tokenData);
        }
    }
}
