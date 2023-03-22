package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;

public class CounterPanel extends PanelTemplate {

    private final HashMap<String, Integer> counterMap = new HashMap<String, Integer>();
    private final String[] columns = {"Tipo", "Cantidad"};
    private final DefaultTableModel counterTableModel = new DefaultTableModel(columns, 0);

    public CounterPanel() {
        super("Contadores");

        final JTable counterTable = new JTable(counterTableModel);
        counterTable.getTableHeader().setReorderingAllowed(false);
        
        final JScrollPane scrollPane = new JScrollPane(counterTable);
        add(scrollPane);

        initTable();
    }

    final void initTable() {
        counterMap.put("Operadores de postfix", 0);
        counterMap.put("Operadores lógicos binarios", 0);
        counterMap.put("Operadores de control", 0);
        counterMap.put("Operadores matemáticos", 0);
        counterMap.put("Operadores de exponente", 0);
        counterMap.put("Operadores de turno", 0);
        counterMap.put("Operadores relacionales", 0);
        counterMap.put("Operadores sin igualdad", 0);
        counterMap.put("Operadores lógicos", 0);
        counterMap.put("Operadores ternarios", 0);
        counterMap.put("Operadores de asignación", 0);
        counterMap.put("Operadores de agrupamiento", 0);
        counterMap.put("Palabras reservadas", 0);
        counterMap.put("Comentarios", 0);
        counterMap.put("Constantes de cadena", 0);
        counterMap.put("Constantes numéricas", 0);
        counterMap.put("Constantes reales", 0);
        counterMap.put("Constantes booleanas", 0);
        counterMap.put("Constantes nulas", 0);
        counterMap.put("Identificadores", 0);
        counterMap.put("Errores", 0);

        for (Map.Entry<String, Integer> counterEntry : counterMap.entrySet()) {
            final String label = counterEntry.getKey();
            final Integer value = counterEntry.getValue();
            final Object[] counter = {label, value};
            counterTableModel.addRow(counter);
        }
    }

    public void updateTable() {
        int row = 0;
        for (Map.Entry<String, Integer> counterEntry : counterMap.entrySet()) {
            final Integer value = counterEntry.getValue();
            counterTableModel.setValueAt(value, row++, 1);
        }
    }
}
