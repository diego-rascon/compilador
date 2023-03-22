package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class CounterPanel extends PanelTemplate {

    private final HashMap<String, Integer> counterMap = new HashMap<String, Integer>();
    private final String[][] data = {
            {"Errores", "0"},
            {"Identificadores", "0"},
            {"Comentarios", "0"},
            {"Palabras reservadas", "0"},
            {"Constantes enteras", "0"},
            {"Constantes de texto", "0"},
            {"Constantes cecimales", "0"},
            {"Constantes exponenciales", "0"},
            {"Operadores aritméticos", "0"},
            {"Operadores monógamos", "0"},
            {"Operadores lógicos", "0"},
            {"Operadores relacionales", "0"},
            {"Operadores de desplazamiento", "0"},
            {"Signos de puntuación", "0"},
            {"Signos de agrupación", "0"},
            {"Signos de asignación", "0"}
    };
    private final String[] columns = {"Tipo", "Cantidad"};
    private final DefaultTableModel countersTableModel = new DefaultTableModel(data, columns);
    private final JTable countersTable = new JTable(countersTableModel);

    public CounterPanel() {
        super("Contadores");
        final JScrollPane scrollPane = new JScrollPane(countersTable);
        countersTable.getTableHeader().setReorderingAllowed(false);
        add(scrollPane);

        counterMap.put("Errores", 0);
    }
}
