package panels.tokens;

import panels.PanelTemplate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TokensPanel extends PanelTemplate {

    private final List<Token> tokenList = new ArrayList<>();
    private final String[] columns = {"Token", "Lexema", "Línea"};
    private final DefaultTableModel tokensTableModel = new DefaultTableModel(columns, 0);
    private final JTable tokensTable = new JTable(tokensTableModel);

    public TokensPanel() {
        super("Tokens");
        final JScrollPane scrollPane = new JScrollPane(tokensTable);
        tokensTable.getTableHeader().setReorderingAllowed(false);
        add(scrollPane);
    }

    final public void addToken(int token, String lexeme, int line) {
        tokenList.add(new Token(token, lexeme, line));
        updateTable();

    }

    final public void emptyTokensList() {
        tokenList.clear();
    }

    private void updateTable() {
        for (Token token : tokenList) {
            System.out.println(token.toString());
        }
    }
}
