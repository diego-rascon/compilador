package panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.Map;

public class CounterPanel extends PanelTemplate {

    private final LinkedHashMap<String, Integer> counterMap = new LinkedHashMap<>();
    private final String[] rowLabels = {
            "Operadores de postfix",
            "Operadores lógicos binarios",
            "Operadores de control",
            "Operadores matemáticos",
            "Operadores de exponente",
            "Operadores de turno",
            "Operadores relacionales",
            "Operadores sin igualdad",
            "Operadores lógicos",
            "Operadores ternarios",
            "Operadores de asignación",
            "Operadores de agrupamiento",
            "Palabras reservadas",
            "Comentarios",
            "Constantes de cadena",
            "Constantes numéricas",
            "Constantes reales",
            "Constantes booleanas",
            "Constantes nulas",
            "Identificadores",
            "Errores"
    };
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
        for (String rowLabel : rowLabels) {
            counterMap.put(rowLabel, 0);
            final Object[] counterRow = {rowLabel, 0};
            counterTableModel.addRow(counterRow);
        }
    }

    public void restartCounter() {
        for (Map.Entry<String, Integer> counterEntry : counterMap.entrySet()) {
            counterEntry.setValue(0);
        }
    }

    public void addCounter(int state) {
        switch (state) {
            case -2, -5 -> {
                String rowLabel = rowLabels[0];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -7, -8, -10, -13 -> {
                String rowLabel = rowLabels[1];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -15, -16, -17, -18 -> {
                String rowLabel = rowLabels[2];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -1, -4, -19, -22, -26 -> {
                String rowLabel = rowLabels[3];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -20 -> {
                String rowLabel = rowLabels[4];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -29, -33, -34 -> {
                String rowLabel = rowLabels[5];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -28, -32, -31, -37, -39, -43 -> {
                String rowLabel = rowLabels[6];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -40, -44 -> {
                String rowLabel = rowLabels[7];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -42, -11, -9 -> {
                String rowLabel = rowLabels[8];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -45 -> {
                String rowLabel = rowLabels[9];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -38, -3, -6, -21, -24, -27, -12, -14, -30, -36, -35, -41 -> {
                String rowLabel = rowLabels[10];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case -46, -47, -48, -49, -50, -51 -> {
                String rowLabel = rowLabels[11];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
            case 500, 501, 502, 503, 504, 505 -> {
                String rowLabel = rowLabels[20];
                int newValue = counterMap.get(rowLabel) + 1;
                counterMap.put(rowLabel, newValue);
            }
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
