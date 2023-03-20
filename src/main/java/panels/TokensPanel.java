package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TokensPanel extends PanelTemplate {

    private final String[] columns = {"Token", "Lexema", "Línea"};
    private final DefaultTableModel tokensTableModel = new DefaultTableModel(columns, 0);
    private final JTable tokensTable = new JTable(tokensTableModel);

    public TokensPanel() {
        super("Tokens");
        final JScrollPane scrollPane = new JScrollPane(tokensTable);
        tokensTable.getTableHeader().setReorderingAllowed(false);
        add(scrollPane);
    }
}
