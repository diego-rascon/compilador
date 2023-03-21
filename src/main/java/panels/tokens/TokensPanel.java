package panels.tokens;

import panels.PanelTemplate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TokensPanel extends PanelTemplate {

    private final List<Token> tokens = new ArrayList<>();
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
        tokens.add(new Token(token, lexeme, line));
        updateTable();

    }

    private void updateTable() {
        // Implementar un método para que se actualice la tabla
    }
}
