package view;

import model.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Code extends PanelTemplate {

    private final String[] keywords = {
            "true",
            "false",
            "null",
            "if",
            "else",
            "switch",
            "for",
            "do",
            "while",
            "Console",
            "log",
            "fuction",
            "real",
            "boolean",
            "Array",
            "new",
            "read",
            "case",
            "default",
            "return",
            "expo",
            "sqrtv",
            "ConvBase",
            "asc",
            "sen",
            "val",
            "cos",
            "tan",
            "break",
            "let",
            "interface",
            "number",
            "string",
            "set",
            "get",
            "class",
            "toLowerCase",
            "toUpperCase",
            "legth",
            "trim",
            "charAt",
            "startsWith",
            "endsWith",
            "indexOf",
            "includes",
            "slice",
            "replace",
            "split",
            "in",
            "of",
            "Map",
            "forEach",
            "any",
            "const",
            "continue",
            "concat",
            "find",
            "findIndex",
            "filter",
            "push",
            "shift",
            "sort",
            "reverse",
            "splice",
            "typeof",
            "undefined"
    };
    private final int[][] productions = {
            {1004, 1000, 201, 1005, -46, 1002, 254, 206, 1003, 1001, -47},                                                  // 0    A   DE  EJ
            {247, 201},                                                                                                     // 1
            {207, 201},                                                                                                     // 2
            {220, 202, 203},                                                                                                // 3
            {210, 204},                                                                                                     // 4
            {-17, 220, 202},                                                                                                // 5
            {210, 205},                                                                                                     // 6
            {-17, 210, 204},                                                                                                // 7
            {-17, 210, 205},                                                                                                // 8
            {-17, 254, 206},                                                                                                // 9
            {-17, 254, 206},                                                                                                // 10
            {2006, -94, -58, -46, 2007, 1004, 1000, 246, 208, 249, 209, 1005, 1001, -47},                                   // 11   A   DE
            {-17, 246, 208},                                                                                                // 12
            {249, 209},                                                                                                     // 13
            {2004, -70, -58, -50, 1004, 1000, 246, 211, 1005, -51, 2004, 212, 2005, -46, 1002, 254, 213, 1003, 1001, -47},  // 14   A   DE  EJ
            {-15, 246, 211},                                                                                                // 15
            {-18, 218},                                                                                                     // 16
            {-17, 254, 213},                                                                                                // 17
            {2012, -92, -58, -50, 1004, 1000, 246, 215, 1005, -51, 2013, -46, 1002, 254, 216, 1003, 1001, -47},             // 18   A   DE  EJ
            {-15, 246, 215},                                                                                                // 19
            {-17, 254, 216},                                                                                                // 20
            {2010, -93, -58, -50, 1004, 1000, 1005, -51, -18, 218, 2011, -46, 1002, 254, 217, 1003, 1001, -47},             // 21   A   DE  EJ
            {-17, 254, 217},                                                                                                // 22
            {-91},                                                                                                          // 23
            {-90},                                                                                                          // 24
            {-72},                                                                                                          // 25
            {-61},                                                                                                          // 26
            {-71},                                                                                                          // 27
            {-52},                                                                                                          // 28
            {-54},                                                                                                          // 29
            {-59},                                                                                                          // 30
            {-60},                                                                                                          // 31
            {-55},                                                                                                          // 32
            {-61},                                                                                                          // 33
            {-88, 2014, -58, 221},                                                                                          // 34
            {-38, 222},                                                                                                     // 35
            {2018, -70, -50, 1004, 1000, 246, 223, 1005, -51, 2018, 224, 2019, -46, 1002, 254, 225, 1003, 1001, -47},       // 36   A   DE  EJ
            {-15, 246, 223},                                                                                                // 37
            {-18, 218},                                                                                                     // 38
            {-17, 254, 226},                                                                                                // 39
            {2020, -50, 1004, 1000, 246, 226, 1005, -51, 2020, 2021, -41, 1002, 254, 1003, 1001},                           // 40   A   DE  EJ
            {-15, 246, 226},                                                                                                // 41
            {-18, 227},                                                                                                     // 42
            {-73, -28, 228, -32, -38, 229},                                                                                 // 43
            {218},                                                                                                          // 44
            {-58},                                                                                                          // 45
            {-46, 230, -47, 2017},                                                                                                // 46
            {273, 231},                                                                                                     // 47
            {-15, 273, 231},                                                                                                // 48
            {-74, -73, -50, -51, 2017},                                                                                           // 49
            {218, 2015, 232},                                                                                               // 50
            {-38, 233},                                                                                                     // 51
            {219, 2015},                                                                                                    // 52
            {2016, -46, 1004, 1000, 246, -15, 234, 214, 235, 249, 236, 1005, 1001, -47},                                    // 53   A   DE
            {246, -15},                                                                                                     // 54
            {-15, 214},                                                                                                     // 55
            {-15, 249},                                                                                                     // 56
            {-58, 237},                                                                                                     // 57
            {-38, 238},                                                                                                     // 58
            {219, 2015},                                                                                                    // 59
            {2016, -46, 1004, 1000, 246, -15, 239, 214, 240, 249, 241, 1005, 1001, -47},                                          // 60   A   DE
            {246, -15},                                                                                                     // 61
            {-15, 214},                                                                                                     // 62
            {-15, 249},                                                                                                     // 63
            {242, -28, 218, -15, 218, -32, -38, 243},                                                                       // 64
            {-109},                                                                                                         // 65
            {-74, -109, -50, -51},                                                                                          // 66
            {-48, 244, -49},                                                                                                // 67
            {273, 245},                                                                                                     // 68
            {-15, 273, 245},                                                                                                // 69
            {2000, -58, -18, 218, 2001},                                                                                    // 70               VA
            {2008, -89, -58, 2009, -46, 1004, 1000, 246, 248, 1005, 1001, -47},                                             // 71   A   DE
            {-17, 246, 248},                                                                                                // 72
            {2002, -58, -50, 1004, 1000, 246, 250, 1005, -51, 2002, 251, 2003, -46, 1002, 254, 252, 1003, 1001, -47},       // 73   A   DE  EJ
            {-15, 246, 250},                                                                                                // 74
            {-18, 218},                                                                                                     // 75
            {-17, 254, 252},                                                                                                // 76
            {-38},                                                                                                  // 77
            {-3},                                                                                                   // 78
            {-24},                                                                                                  // 79
            {-21},                                                                                                  // 80
            {-6},                                                                                                   // 81
            {-27},                                                                                                  // 82
            {-12},                                                                                                  // 83
            {-14},                                                                                                  // 84
            {-30},                                                                                                  // 85
            {-36},                                                                                                  // 86
            {-35},                                                                                                  // 87
            {-68, -16, 255},                                                                                        // 88
            {-62, -50, 4000, 3000, 273, 3001, 4001, -51, 254, 257},                                                 // 89
            {4006, -64, -50, 4008, 3000, 273, 3001, 4009, -51, -46, -76, 4010, 3000, 273, 3001, 4011, -18, 258, 254, 259, -87, 260, -47, 4007},                                 // 90
            {-46, 254, 263, -47},                                                                                   // 91
            {-67, -50, 4002, 3000, 273, 3001, 4003, -51, 254},                                                      // 92
            {3000, 273, 3001, -17},                                                 // 93
            {-78, 273, -17},                                                        // 94
            {-66, 254, -67, -50, 4004, 3000, 273, 3001, 4005, -51, -17},            // 95
            {4012, -65, -50, 264, -51, 4013, 254},                                              // 96
            {-75, -50, 273, 256, -51},                                              // 97
            {-69, -50, 273, -51},                                                   // 98
            {-15, 273, 256},                                                        // 99
            {-63, 254},                                                             // 100
            {-76, 4010, 3000, 273, 3001, 4011, -18, 258},                           // 101
            {-17, 254, 259},                                                        // 102
            {-76, 4010, 3000, 273, 3001, 4011, -18, 268, -87, 260},                                         // 103
            {-77, -18, 254, 262},                                                   // 104
            {-17, 254, 261},                                                        // 105
            {-17, 254, 262},                                                        // 106
            {-17, 254, 263},                                                        // 107
            {4014, 3000, 273, 3001, 4015, 265, -17, 4016, 3000, 273, 3001, 4017, -17, 4018, 3000, 273, 3001, 4019, 266},                                    // 108
            {4020, -88, 4022, -58, 4023, 267, 4024, -58, 4025, 4021},                                                   // 109
            {-15, 273, 265},                                                        // 110
            {-15, 273, 266},                                                        // 111
            {4026, -107},                                                                 // 112
            {4027, -108},                                                                 // 113
            {254, 261},                                                             // 114
            {-95, -50, -51},                                                        // 115
            {-96, -50, -51},                                                        // 116
            {-97},                                                                  // 117
            {-98, -50, -51},                                                        // 118
            {-99, -50, 273, -51},                                                   // 119
            {-100, -50, 273, -51},                                                  // 120
            {-101, -50, 273, -51},                                                  // 121
            {-102, -50, 273, -51},                                                  // 122
            {-103, -50, 273, -51},                                                  // 123
            {-104, -50, 273, -15, 273, -51},                                        // 124
            {-105, -50, 273, -15, 273, -51},                                        // 125
            {-106, -50, 273, -51},                                                  // 126
            {-79, -50, 273, -15, 273, -51},                                         // 127
            {-80, -50, 273, -15, 273, -51},                                         // 128
            {-81, -50, 273, -15, 273, -15, -51},                                    // 129
            {-82, -50, 273, -51},                                                   // 130
            {-83, -50, 273, -51},                                                   // 131
            {-84, -50, 273, -51},                                                   // 132
            {-85, -50, 273, -51},                                                   // 133
            {-86, -50, 273, -51},                                                   // 134
            {269},                                                                  // 135
            {-48, 273, 272, -49},                                                   // 136
            {-15, 273, 272},                                                        // 137
            {275, 274},                                                 // 138
            {-9, 275, 274},                                                         // 139
            {-8, 275, 274},                                                         // 140
            {277, 276},                                                             // 141
            {-11, 277, 276},                                                        // 142
            {-10, 277, 276},                                                        // 143
            {-13, 277, 276},                                                        // 144
            {279, 278},                                                             // 145
            {-28, 279, 278},                                                        // 146
            {-31, 279, 278},                                                        // 147
            {-39, 279, 278},                                                        // 148
            {-43, 279, 278},                                                        // 149
            {-37, 279, 278},                                                        // 150
            {-32, 279, 278},                                                        // 151
            {-40, 279, 278},                                                        // 152
            {-44, 279, 278},                                                        // 153
            {281, 280},                                                             // 154
            {-4, 281, 280},                                                         // 155
            {-1, 281, 280},                                                         // 156
            {-29, 281, 280},                                                        // 157
            {-33, 281, 280},                                                        // 158
            {-34, 281, 280},                                                        // 159
            {283, 282},                                                             // 160
            {-19, 283, 282},                                                        // 161
            {-22, 283, 282},                                                        // 162
            {-26, 283, 282},                                                        // 163
            {285, 284},                                                             // 164
            {-20, 285, 284},                                                        // 165
            {219},                                                                  // 166
            {286, -58, 287},                                                  // 167
            {292, -50, 273, -51},                                                   // 168
            {270},                                                                  // 169
            {-2},                                                                   // 170
            {-5},                                                                   // 171
            {271, 288},                                                             // 172
            {-50, 290, -51},                                                        // 173
            {253, 273, 289},                                                        // 174
            {-45, 273, -18, 273},                                                   // 175
            {273, 291},                                                             // 176
            {-15, 273, 291},                                                        // 177
            {-42},                                                                  // 178
            {-7},                                                                   // 179
            {-57, -58},                                                             // 180
            {253, 273, 289},                                                        // 181
            {-76, 273, -18, 268}                                                    // 182
    };

    private final Connection connection;
    private FileWriter txtResult;

    // UI Components
    private final RSyntaxTextArea codeArea = new RSyntaxTextArea();
    private final Tokens tokenPanel;
    private final Counters countersPanel;
    private final Errors errorsPanel;
    private final ErrorTypes errorTypesPanel;

    // Lexicon
    private int[][] lexicMatrix;

    // Syntaxis
    private final Stack<Integer> syntaxStack = new Stack<>();
    private final LinkedList<model.Token> syntaxTokens = new LinkedList<>();
    private int[][] syntaxMatrix;

    // Ambit
    private final Stack<model.Element> elementsStack = new Stack<>();
    private final Stack<Ambit> ambitStack = new Stack<>();
    private final LinkedList<Ambit> ambits = new LinkedList<>();
    private final LinkedList<String> tempArraySize = new LinkedList<>();
    private ElementType currentType = ElementType.NONE;
    private Operand tempOperand;
    private String tempLet = "";
    private String tempType = "";
    private int ambit = 0;
    private int tempParameters = 0;
    private int tempPosition = 0;
    private int tempArrayDim = 0;
    private boolean exeArea = false;
    private boolean decLet = false;
    private boolean decParameters = false;
    private boolean customType = false;
    private boolean idType = false;

    // Semantics 1
    private final Stack<Operator> operatorStack = new Stack<>();
    private final Stack<Operand> operandStack = new Stack<>();
    private final LinkedList<Operation> operations = new LinkedList<>();
    private final StringBuilder tempAssignation = new StringBuilder();

    // Semantics 2
    private final LinkedList<Semantics> semanticsList = new LinkedList<>();
    private int[][][] semMatrix;

    // // Regla 1
    private boolean inIf = false;
    private boolean inWhile = false;
    private boolean inDoWhile = false;

    // // Regla 2
    private boolean assignating = false;
    private boolean plusplus = false;
    private boolean minusminus = false;

    // // Regla 3
    private final Stack<Boolean> switchStack = new Stack<>(); // true = number, false = string
    private boolean inSwitch = false;
    private boolean inSwitchType = false;
    private boolean inSwitchCase = false;
    private boolean switchError = false;
    // // Regla 8
    private boolean inFor = false;
    private boolean inForInit = false;
    private boolean inForComp = false;
    private boolean inForInc = false;
    private boolean inForString = false;
    private boolean inForArray = false;
    private boolean inForNewId = false;
    private boolean inForId = false;
    private String tempForNewId = "";
    private String tempForId = "";

    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/a20130375", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Code(int padding, Tokens tokensPanel, Counters countersPanel, Errors errorsPanel, ErrorTypes errorTypesPanel) {
        super("Código", padding);

        final RTextScrollPane scrollPane = new RTextScrollPane(codeArea);

        this.tokenPanel = tokensPanel;
        this.countersPanel = countersPanel;
        this.errorsPanel = errorsPanel;
        this.errorTypesPanel = errorTypesPanel;

        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_TYPESCRIPT);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setBracketMatchingEnabled(true);
        codeArea.setAntiAliasingEnabled(true);

        try {
            Theme theme = Theme.load(getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
            theme.apply(codeArea);
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el tema del editor de código.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        add(scrollPane, BorderLayout.CENTER);
        loadLexicMatrix();
        loadSyntaxMatrix();
        loadSemanticaMatrix();
    }

    public void compile() {
        try {
            Statement statement = connection.createStatement();
            String deleteQuery = "DELETE FROM elementos WHERE id_element > 0";
            statement.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            txtResult = new FileWriter("Resultados - 20130375.txt");
        } catch (IOException e) {
            System.out.println("Ocurrió un error creando el archivo de resultados");
        }

        exeArea = false;
        customType = false;
        idType = false;
        decParameters = false;
        decLet = false;
        ambit = 0;
        tempParameters = 0;
        tempPosition = 0;
        tempArrayDim = 0;
        tempLet = "";
        tempType = "";
        syntaxTokens.clear();
        syntaxStack.clear();
        syntaxStack.push(200);
        ambits.clear();
        ambitStack.clear();
        elementsStack.clear();
        tempArraySize.clear();
        tokenPanel.emptyTokensList();
        countersPanel.restartCounter();
        errorsPanel.emptyErrorsList();
        errorTypesPanel.restartCounter();
        currentType = ElementType.NONE;

        // Semantics 1
        assignating = false;
        operations.clear();
        operatorStack.clear();
        operandStack.clear();
        tempAssignation.setLength(0);
        semanticsList.clear();

        checkLexic();
        checkSyntax();

        tokenPanel.updateTable();
        countersPanel.updateTable();
        errorsPanel.updateTable();
        errorTypesPanel.updateTable();
        try {
            txtResult.close();
        } catch (IOException e) {
            System.out.println("Ocurrió un error cerrando el archivo de resultados");
        }

        try {
            for (Element element : elementsStack) {
                String id = element.getId();
                String type = element.getType();
                String classType = element.getClassType();
                String ambit = String.valueOf(element.getAmbit());
                String[] arraySize = element.getArraySize();
                String arrayDim = String.valueOf(element.getArrayDim());
                String parQuantity = String.valueOf(element.getParQuantity());
                String parType = element.getParType();
                switch (element.getClassType()) {
                    case "variable", "variable let", "@anónima", "función", "función anónima", "método", "método anónimo", "get", "set" -> {
                        if (parType == null) {
                            String query = "INSERT INTO elementos (id_variable, tipo, clase, ambito) VALUES (?, ?, ?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, id);
                            preparedStatement.setString(2, type);
                            preparedStatement.setString(3, classType);
                            preparedStatement.setString(4, ambit);
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                        } else {
                            String query = "INSERT INTO elementos (id_variable, tipo, clase, ambito, cant_parametro, tipo_parametro) VALUES (?, ?, ?, ?, ?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, id);
                            preparedStatement.setString(2, type);
                            preparedStatement.setString(3, classType);
                            preparedStatement.setString(4, ambit);
                            preparedStatement.setString(5, parQuantity);
                            preparedStatement.setString(6, parType);
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                        }
                    }
                    case "interface", "clase", "clase anónima" -> {
                        String query = "INSERT INTO elementos (id_variable, tipo, clase, ambito, tipo_parametro) VALUES (?, ?, ?, ?, ?)";
                        prepareStatement(id, type, classType, ambit, parType, query);
                    }
                    case "arreglo" -> {
                        if (arraySize == null) {
                            String query = "INSERT INTO elementos (id_variable, tipo, clase, ambito, dim_arreglo) VALUES (?, ?, ?, ?, ?)";
                            prepareStatement(id, type, classType, ambit, arrayDim, query);
                        } else {
                            String query = "INSERT INTO elementos (id_variable, tipo, clase, ambito, tam_arreglo, dim_arreglo) VALUES (?, ?, ?, ?, ?, ?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(query);
                            preparedStatement.setString(1, id);
                            preparedStatement.setString(2, type);
                            preparedStatement.setString(3, classType);
                            preparedStatement.setString(4, ambit);
                            preparedStatement.setString(5, Arrays.toString(arraySize));
                            preparedStatement.setString(6, arrayDim);
                            preparedStatement.executeUpdate();
                            preparedStatement.close();
                        }
                    }
                }
            }
            String[] types = {"string", "number", "boolean", "real", "null", "#", "void"};
            for (int i = 0; i < ambit; i++) {
                for (String type : types) {
                    Statement statement = connection.createStatement();
                    String query;
                    if (type.equals("#")) {
                        query = "SELECT COUNT(*) FROM elementos WHERE ambito = " + i + " AND tipo LIKE '#%';";
                    } else {
                        query = "SELECT COUNT(*) FROM elementos WHERE ambito = " + i + " AND tipo = '" + type + "';";
                    }
                    ResultSet resultSet = statement.executeQuery(query);
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        for (Ambit currentAmbit : ambits) {
                            if (currentAmbit.getId() == i) {
                                currentAmbit.incCounter(type, count);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Semantics semantics : semanticsList) {
            System.out.println(semantics);
        }
    }

    private void prepareStatement(String id, String type, String classType, String ambit, String parType, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, type);
        preparedStatement.setString(3, classType);
        preparedStatement.setString(4, ambit);
        preparedStatement.setString(5, parType);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    private void checkLexic() {
        int i = 0;
        int state = 0;
        int lineNum = 1;
        int multiCommentLineNum = 1;
        StringBuilder lexeme = new StringBuilder();
        final String code = codeArea.getText() + "\n";

        while (i < code.length()) {
            final char character = code.charAt(i);
            final int column = getColumn(character);
            state = lexicMatrix[state][column];
            if (state < 0) {
                if (state == -58) state = getKeywordToken(lexeme.toString(), state);
                if (state == -25) {
                    tokenPanel.addToken(state, lexeme.toString(), multiCommentLineNum);
                } else {
                    tokenPanel.addToken(state, lexeme.toString(), lineNum);
                }
                if (state != -23 && state != -25) syntaxTokens.add(new model.Token(state, lexeme.toString(), lineNum));
                countersPanel.addCounter(state);
                lexeme.setLength(0);
                i--;
                state = 0;
            } else if (state >= 500) {
                if (state == 500) {
                    lexeme.append(character);
                } else i--;
                addError(state, lexeme.toString(), ErrorType.LEXIC, lineNum);
                lexeme.setLength(0);
                state = 0;
            } else {
                if (state != 0) lexeme.append(character);
                if (character == '\n') {
                    lineNum++;
                }
                if (state == 25 && lexeme.toString().equals("/*")) {
                    multiCommentLineNum = lineNum;
                }
            }
            i++;
        }
        if (state != 0) {
            state = 505;
            addError(state, lexeme.toString(), ErrorType.LEXIC, multiCommentLineNum);
        }
    }

    private void checkSyntax() {
        while (!syntaxTokens.isEmpty() && !syntaxStack.isEmpty()) {
            int topSyntaxStack = syntaxStack.peek();

            if (topSyntaxStack >= 200 && topSyntaxStack <= 292) {
                model.Token token = syntaxTokens.getFirst();
                int tokenCol = (token.token() * -1) - 1;
                String lexeme = token.lexeme();
                int lineNum = token.line();
                int production = syntaxMatrix[topSyntaxStack - 200][tokenCol];

                if (production > 499) {
                    addError(production, lexeme, ErrorType.SINTAXIS, lineNum);
                    syntaxTokens.removeFirst();
                } else if (production == 183) {
                    syntaxStack.pop();
                } else {
                    syntaxStack.pop();
                    for (int j = productions[production].length - 1; j >= 0; j--) {
                        syntaxStack.push(productions[production][j]);
                    }
                }
            } else if (topSyntaxStack >= 1000) {
                syntaxStack.pop();
                int line = syntaxTokens.getFirst().line();
                switch (topSyntaxStack) {
                    case 1000, 4020 -> {
                        Ambit newAmbit = new Ambit(ambit);
                        ambits.add(newAmbit);
                        ambitStack.push(newAmbit);
                        printAmbitAction("creó", ambit, line);
                        ambit++;
                    }
                    case 1001 -> {
                        int topAmbitLine = ambitStack.peek().getId();
                        ambitStack.pop();
                        printAmbitAction("eliminó", topAmbitLine, line);
                    }
                    case 1002 -> {
                        exeArea = true;
                        printAreaAction(line, "ejecución", "apertura");
                    }
                    case 1003 -> {
                        exeArea = false;
                        printAreaAction(line, "ejecución", "cierre");
                    }
                    case 1004 -> printAreaAction(line, "declaración", "apertura");
                    case 1005 -> printAreaAction(line, "declaración", "cierre");
                    case 2000 -> currentType = ElementType.DEC_VAR;
                    case 2002 -> {
                        decParameters = true;
                        currentType = ElementType.DEC_MET;
                    }
                    case 2004 -> {
                        decParameters = true;
                        currentType = ElementType.DEC_FUN;
                    }
                    case 2006 -> currentType = ElementType.DEC_CLASS;
                    case 2008 -> currentType = ElementType.DEC_INTER;
                    case 2010 -> currentType = ElementType.DEC_GET;
                    case 2012 -> {
                        decParameters = true;
                        currentType = ElementType.DEC_SET;
                    }
                    case 2014 -> decLet = true;
                    case 2018 -> {
                        decLet = false;
                        decParameters = true;
                        currentType = ElementType.DEC_AN_FUN;
                    }
                    case 2020 -> {
                        decLet = false;
                        decParameters = true;
                        currentType = ElementType.DEC_AN_MET;
                    }
                    case 2001, 2007, 2009 -> currentType = ElementType.NONE;
                    case 2003, 2005, 2011, 2013, 2019, 2021 -> {
                        Element lastMethod = elementsStack.get(tempPosition);
                        lastMethod.setParQuantity(tempParameters);
                        tempParameters = 0;
                        decParameters = false;
                        currentType = ElementType.NONE;
                    }
                    case 2015 -> {
                        if (decLet) {
                            decLet = false;
                            idType = false;
                            Element newElement = new Element(tempLet, "variable let", 0);
                            newElement.setType(tempType);
                            checkDuplicateElement(551, tempLet, line);
                            elementsStack.add(newElement);
                            if (tempType.charAt(0) == '@') {
                                Element typeElement = new Element(tempType, "@anónima", 0);
                                typeElement.setParType("1");
                                elementsStack.add(typeElement);
                            }
                        }
                    }
                    case 2016 -> {
                        if (decLet) {
                            decLet = false;
                            idType = false;
                            Element newElement = new Element(tempLet, "clase anónima", 0);
                            newElement.setType(tempType);
                            checkDuplicateElement(558, tempLet, line);
                            elementsStack.add(newElement);
                            if (tempType.charAt(0) == '@') {
                                Element typeElement = new Element(tempType, "@anónima", 0);
                                typeElement.setParType("1");
                                elementsStack.add(typeElement);
                            }
                        }
                    }
                    case 2017 -> {
                        if (decLet) {
                            decLet = false;
                            idType = false;
                            Element newElement = new Element(tempLet, "arreglo", 0);
                            if (tempType.charAt(0) == '@') {
                                tempType = tempType.substring(1);
                            }
                            newElement.setType(tempType);
                            newElement.setArrayDim(tempArrayDim == 0 ? 0 : tempArrayDim + 1);
                            if (!tempArraySize.isEmpty()) {
                                String[] arraySize = new String[tempArraySize.size()];
                                for (int i = 0; i < tempArraySize.size(); i++) {
                                    arraySize[i] = tempArraySize.get(i);
                                }
                                newElement.setArraySize(arraySize);
                            }
                            checkDuplicateElement(552, tempLet, line);
                            elementsStack.add(newElement);
                            tempArrayDim = 0;
                            tempArraySize.clear();
                        }
                    }
                    case 3000 -> {
                        assignating = true;
                        operations.add(new Operation(line));
                    }
                    case 3001 -> {
                        assignating = false;
                        boolean validOperation = true;

                        // realizar operación
                        while (!operatorStack.isEmpty()) {
                            doOperation();
                        }

                        // Sacar el tope de pila y la regla de semántica 2
                        String topStack = getString();

                        int rule = getRule();

                        if (rule == 1021 && topStack.equals("string")) topStack = "any";

                        // comparar con el operando que se está asignando
                        Operation lastOperation = operations.getLast();

                        if (tempOperand != null) {
                            if (rule == 1020 || rule == 1021 || rule == 1022) {
                                boolean concat = tempAssignation.toString().contains("+=") && tempOperand.type() == Type.STRING;
                                if (tempOperand.type() != operandStack.peek().type() && !concat) {
                                    validOperation = false;
                                    addError(609, syntaxTokens.getFirst().lexeme(), ErrorType.SEMANTICS, syntaxTokens.getFirst().line());
                                    lastOperation.addError();
                                }
                            }
                        }

                        // Revisar si es una condición o un ciclo
                        if (inIf || inWhile || inDoWhile || inForComp) {
                            topStack = "boolean";
                            validOperation = operandStack.peek().type() == Type.BOOLEAN;
                        }

                        // Revisar si es un switch
                        if (inSwitch) {
                            if (!switchStack.isEmpty()) topStack = switchStack.peek() ? "number" : "string";

                            if (inSwitchType) {
                                topStack = "number/string";
                                validOperation = operandStack.peek().type() == Type.NUMBER || operandStack.peek().type() == Type.STRING;
                                if (validOperation) {
                                    switchStack.push(operandStack.peek().type() == Type.NUMBER);
                                } else {
                                    switchError = true;
                                }
                            }
                            if (inSwitchCase) {
                                if (switchError) {
                                    validOperation = operandStack.peek().type() == Type.NUMBER || operandStack.peek().type() == Type.STRING;
                                    if (validOperation) {
                                        switchStack.push(operandStack.peek().type() == Type.NUMBER);
                                        topStack = "number/string";
                                        switchError = false;
                                    } else {
                                        topStack = "switch error";
                                    }
                                } else {
                                    boolean caseType = operandStack.peek().type() == Type.NUMBER;
                                    validOperation = caseType == switchStack.peek();
                                }
                            }
                        }

                        if (inForInit) topStack = "asignación";
                        else if (inForComp) topStack = "boolean";
                        else if (inForInc) topStack = "++/--";

                        if (inForInit) validOperation = tempAssignation.toString().contains("=");
                        if (inForComp) validOperation = operandStack.peek().type() == Type.BOOLEAN;

                        // Crear la entrada en la lista de semántica
                        String realValue = operandStack.peek().lexeme();
                        if (inSwitch) {
                            realValue = switch (operandStack.peek().type()) {
                                case STRING -> "string";
                                case NUMBER -> "number";
                                case BOOLEAN -> "boolean";
                                case REAL -> "real";
                                case CUSTOM -> "custom";
                                case VOID -> "void";
                                case VARIANT -> "variant";
                                case NULL -> "null";
                            };
                        }

                        semanticsList.add(new Semantics(rule, line, ambitStack.peek().getId()));
                        Semantics lastSemantics = semanticsList.getLast();
                        lastSemantics.setTopStack(topStack);
                        lastSemantics.setRealValue(realValue);
                        lastSemantics.setAccepted(validOperation);

                        // hacer el string de asignación
                        tempAssignation.append(operandStack.pop().lexeme());
                        lastOperation.setAssignation(tempAssignation.toString());
                        tempAssignation.setLength(0);
                        tempOperand = null;
                    }
                    case 4000 -> inIf       = true;
                    case 4001 -> inIf       = false;
                    case 4002 -> inWhile    = true;
                    case 4003 -> inWhile    = false;
                    case 4004 -> inDoWhile  = true;
                    case 4005 -> inDoWhile  = false;
                    case 4006 -> inSwitch   = true;
                    case 4007 -> {
                        switchStack.pop();
                        if (switchStack.isEmpty()) inSwitch = false;
                    }
                    case 4008 -> inSwitchType   = true;
                    case 4009 -> inSwitchType   = false;
                    case 4010 -> inSwitchCase   = true;
                    case 4011 -> inSwitchCase   = false;
                    case 4012 -> inFor          = true;
                    case 4013 -> inFor          = false;
                    case 4014 -> inForInit      = true;
                    case 4015 -> inForInit      = false;
                    case 4016 -> inForComp      = true;
                    case 4017 -> inForComp      = false;
                    case 4018 -> inForInc       = true;
                    case 4019 -> inForInc       = false;
                    case 4021 -> {
                        int rule = 0;
                        if (inForString) rule = 1083;
                        else if (inForArray) rule = 1084;

                        String topStack = "";
                        if (inForString) topStack = "string";
                        else if (inForArray) topStack = "arreglo";

                        boolean isArray = false;
                        System.out.println(tempForId);
                        String realValue = "variable no encontrada";
                        for (Element element : elementsStack) {
                            if (element.getId().equals(tempForId)) {
                                int elementAmbit = element.getAmbit();
                                for (Ambit activeAmbit : ambitStack) {
                                    if (activeAmbit.getId() == elementAmbit) {
                                        System.out.println("variable encontrada");
                                        isArray = (element.getClassType().equals("arreglo"));
                                        if (element.getType() != null) {
                                            realValue = element.getType();
                                        }
                                        break;
                                    }
                                }
                            }
                        }

                        int currentAmbit = ambitStack.peek().getId();

                        elementsStack.push(new Element(tempForNewId, "variable let", currentAmbit));
                        elementsStack.peek().setType(realValue);

                        boolean validFor = true;
                        if (inForString) validFor = realValue.equals("string");
                        if (inForArray) validFor = isArray;

                        semanticsList.add(new Semantics(rule, line, currentAmbit));
                        Semantics lastSemantics = semanticsList.getLast();
                        lastSemantics.setTopStack(topStack);
                        lastSemantics.setRealValue(realValue);
                        lastSemantics.setAccepted(validFor);

                        int topAmbitLine = ambitStack.peek().getId();
                        ambitStack.pop();
                        printAmbitAction("eliminó", topAmbitLine, line);

                        inForString = false;
                        inForArray = false;
                    }
                    case 4022 -> inForNewId     = true;
                    case 4023 -> inForNewId     = false;
                    case 4024 -> inForId        = true;
                    case 4025 -> inForId        = false;
                    case 4026 -> inForString    = true;
                    case 4027 -> inForArray     = true;
                }
            } else if (topSyntaxStack < 0) {
                int token = syntaxTokens.getFirst().token();
                if (token == topSyntaxStack) {
                    String lexeme = syntaxTokens.getFirst().lexeme();
                    int line = syntaxTokens.getFirst().line();
                    if (inForNewId && token == -58) {
                        tempForNewId = lexeme;
                    }
                    if (inForId && token == -58) {
                        System.out.println("blablaba");
                        tempForId = lexeme;
                    }
                    if (assignating) {
                        switch (token) {
                            // Asignaciones
                            case -3, -6, -12, -14, -21, -24, -27, -30, -35, -36, -38 -> {
                                tempOperand = operandStack.peek();
                                tempAssignation.append(operandStack.pop().lexeme()).append(" ").append(lexeme).append(" ");
                            }
                            // Operandos
                            case -52, -53, -54, -55, -56, -58, -59, -60, -61 -> {
                                operandStack.push(new Operand(token, lexeme, getType(token, lexeme)));
                                if (plusplus) {
                                    operatorStack.push(new Operator(-1, "+", 6));
                                    Operand newOperand;
                                    if (token == -58) {
                                        newOperand = new Operand(-54, "1", Type.NUMBER);
                                    } else {
                                        newOperand = new Operand(-600, "temp variant", Type.VARIANT);
                                    }
                                    if (tempOperand == null) {
                                        tempAssignation.append(operandStack.peek().lexeme()).append(" = ");
                                        tempOperand = newOperand;
                                    }
                                    operandStack.push(newOperand);
                                    plusplus = false;
                                }
                                if (minusminus) {
                                    operatorStack.push(new Operator(-4, "-", 6));
                                    Operand newOperand;
                                    if (token == -58) {
                                        newOperand = new Operand(-54, "1", Type.NUMBER);
                                    } else {
                                        newOperand = new Operand(-601, "temp variant", Type.VARIANT);
                                    }
                                    if (tempOperand == null) {
                                        tempAssignation.append(operandStack.peek().lexeme()).append(" = ");
                                        tempOperand = newOperand;
                                    }
                                    operandStack.push(newOperand);
                                    minusminus = false;
                                }
                            }
                            // Operadores
                            case -1, -2, -4, -5, -8, -9, -10, -11, -13, -19, -20, -22, -26, -28, -29, -31, -32, -33, -34, -37,
                                    -39, -40, -43, -44 -> {
                                if (operatorStack.isEmpty()) {
                                    switch (token) {
                                        case -2 -> plusplus = true;
                                        case -5 -> minusminus = true;
                                        default -> operatorStack.push(new Operator(token, lexeme, getPriority(token)));
                                    }
                                } else {
                                    Operator currentOperator = new Operator(token, lexeme, getPriority(token));
                                    if (currentOperator.priority() >= operatorStack.peek().priority()) {
                                        operatorStack.push(currentOperator);
                                    } else {
                                        while (!operatorStack.isEmpty() && currentOperator.priority() < operatorStack.peek().priority()) {
                                            doOperation();
                                        }
                                        operatorStack.push(new Operator(token, lexeme, getPriority(token)));
                                    }
                                }
                            }
                        }
                    }
                    if (decLet) {
                        if (topSyntaxStack == -58 && !idType) {
                            idType = true;
                            tempLet = lexeme;
                        } else {
                            switch (topSyntaxStack) {
                                case -15 -> tempArrayDim++;
                                case -52, -53, -54, -55, -56 -> tempArraySize.add(lexeme);
                                case -57 -> customType = true;
                                case -58 -> {
                                    if (customType) {
                                        customType = false;
                                        tempType = "#" + lexeme;
                                    } else {
                                        idType = false;
                                        tempType = "@" + lexeme;
                                    }
                                }
                                case -61 -> tempType = "null";
                                case -71 -> tempType = "real";
                                case -72 -> tempType = "boolean";
                                case -90 -> tempType = "number";
                                case -91 -> tempType = "string";
                            }
                        }
                    } else if (currentType != ElementType.NONE) {
                        int ambitNumber = ambitStack.peek().getId();
                        createElement(lexeme, line, ambitNumber, topSyntaxStack);
                    }
                    if (topSyntaxStack == -58 && exeArea) {
                        var validCode = elementExist(lexeme);
                        if (!validCode && !inForNewId) {
                            addError(549, lexeme, ErrorType.AMBIT, syntaxTokens.getFirst().line());
                        }
                    }
                    syntaxStack.pop();
                    syntaxTokens.removeFirst();
                } else if (token == -53 && topSyntaxStack == -52) {
                    syntaxStack.pop();
                    syntaxTokens.removeFirst();
                } else if (token == -55 && topSyntaxStack == -56) {
                    syntaxStack.pop();
                    syntaxTokens.removeFirst();
                } else {
                    System.out.println("Error de fuerza bruta, linea: " + syntaxTokens.getFirst().line() + " lexema: " + syntaxTokens.getFirst().lexeme());
                    syntaxStack.pop();
                    syntaxTokens.removeFirst();
                }
            }
        }
    }

    private int getRule() {
        String assigntationString = tempAssignation.toString();
        int rule = 1022;
        if (assigntationString.contains("=")) rule = 1020;
        if (assigntationString.contains("+=")) rule = 1021;

        if (inIf) rule = 1010;
        else if (inWhile) rule = 1011;
        else if (inDoWhile) rule = 1012;

        if (inSwitch) {
            if (inSwitchCase) rule = 1030;
            else if (inSwitchType) rule = 1031;
        }

        if (inFor) {
            if (inForInit) rule = 1080;
            else if (inForComp) rule = 1081;
            else if (inForInc) rule = 1082;
            else if (inForString) rule = 1083;
            else if (inForArray) rule = 1084;
        }
        return rule;
    }

    private String getString() {
        String topStack = "boolean";
        if (tempOperand != null) {
            topStack = switch (tempOperand.type()) {
                case BOOLEAN -> "boolean";
                case NUMBER -> "number";
                case REAL -> "real";
                case STRING -> "string";
                case NULL -> "null";
                case CUSTOM -> "custom";
                case VARIANT -> "variant";
                case VOID -> "void";
            };
        }
        return topStack;
    }

    private void doOperation() {
        Operation lastOperation = operations.getLast();
        int sheet = getSheet(operatorStack.pop().token());
        int column = getPosition(operandStack.pop().type());
        int row = getPosition(operandStack.pop().type());
        int result = semMatrix[sheet][column][row];
        String resultLexeme = "temp variant";
        Type resultType = Type.VARIANT;
        switch (result) {
            case -61 -> {
                resultLexeme = "temp null";
                resultType = Type.NULL;
            }
            case -71 -> {
                resultLexeme = "temp real";
                resultType = Type.REAL;
            }
            case -72 -> {
                resultLexeme = "temp boolean";
                resultType = Type.BOOLEAN;
            }
            case -90 -> {
                resultLexeme = "temp number";
                resultType = Type.NUMBER;
            }
            case -91 -> {
                resultLexeme = "temp string";
                resultType = Type.STRING;
            }
            case 600, 601, 602, 603, 604, 605, 606, 607, 608 -> {
                addError(result, syntaxTokens.getFirst().lexeme(), ErrorType.SEMANTICS, syntaxTokens.getFirst().line());
                lastOperation.addError();
            }
        }
        lastOperation.addCounter(resultType);
        operandStack.push(new Operand(result, resultLexeme, resultType));
    }

    private Type getType(int token, String lexeme) {
        return switch (token) {
            case -52, -53 -> Type.STRING;
            case -54 -> Type.NUMBER;
            case -55, -56 -> Type.REAL;
            case -59, -60 -> Type.BOOLEAN;
            case -61 -> Type.NULL;
            case -58 -> {
                for (Element element : elementsStack) {
                    if (element.getId().equals(lexeme)) {
                        var elementAmbit = element.getAmbit();
                        for (Ambit activeAmbit : ambitStack) {
                            if (activeAmbit.getId() == elementAmbit) {
                                String elementType;
                                if (element.getType() == null) {
                                    yield Type.VARIANT;
                                } else {
                                    elementType = element.getType();
                                }
                                yield switch (elementType) {
                                    case "boolean" -> Type.BOOLEAN;
                                    case "number" -> Type.NUMBER;
                                    case "real" -> Type.REAL;
                                    case "string" -> Type.STRING;
                                    case "void" -> Type.VOID;
                                    case "null" -> Type.NULL;
                                    default -> {
                                        if (elementType.charAt(0) == '#') yield Type.CUSTOM;
                                        yield Type.VARIANT;
                                    }
                                };
                            }
                        }
                    }
                }
                yield Type.VARIANT;
            }
            default -> Type.VARIANT;
        };
    }

    private int getPriority(int token) {
        return switch (token) {
            case -10, -11, -13 -> 1;
            case -28, -31, -39, -43, -37, -32, -40, -44 -> 2;
            case -4, -1, -29, -33, -34 -> 3;
            case -19, -22, -26 -> 4;
            case -20 -> 5;
            case -2, -5, -42, -7 -> 6;
            default -> 0;
        };
    }

    private int getPosition(Type type) {
        return switch (type) {
            case NUMBER -> 0;
            case REAL -> 1;
            case BOOLEAN -> 2;
            case STRING -> 3;
            case NULL -> 4;
            case VARIANT, CUSTOM, VOID -> 5;
        };
    }

    private int getSheet(int token) {
        return switch (token) {
            case -4 -> 1;
            case -19 -> 2;
            case -22 -> 3;
            case -7, -8, -10, -13, -20, -26, -29, -33, -34 -> 4;
            case -28, -31, -32, -37 -> 5;
            case -39, -43 -> 6;
            case -40, -44 -> 7;
            case -9, -11 -> 8;
            default -> 0;
        };
    }

    private boolean elementExist(String lexeme) {
        var validCode = false;
        for (Element element : elementsStack) {
            if (element.getId().equals(lexeme)) {
                var elementAmbit = element.getAmbit();
                for (Ambit activeAmbit : ambitStack) {
                    if (activeAmbit.getId() == elementAmbit) {
                        validCode = true;
                        break;
                    }
                }
            }
        }
        return validCode;
    }

    private void createElement(String lexeme, int line, int ambitNumber, int topSyntaxStack) {
        String classType = getClassType();
        if (topSyntaxStack == -50 && currentType == ElementType.DEC_AN_FUN || currentType == ElementType.DEC_AN_MET) {
            switch (currentType) {
                case DEC_AN_FUN -> checkDuplicateElement(554, tempLet, line);
                case DEC_AN_MET -> checkDuplicateElement(556, tempLet, line);
            }
            elementsStack.add(new Element(tempLet, classType, ambitNumber));
            tempPosition = elementsStack.size() - 1;
            Element lastElement = elementsStack.peek();
            lastElement.setType("void");
            lastElement.setParType(String.valueOf(ambit));
        } else if (topSyntaxStack == -58 && !customType) {
            switch (currentType) {
                case DEC_VAR -> checkDuplicateElement(550, lexeme, line);
                case DEC_FUN -> checkDuplicateElement(553, lexeme, line);
                case DEC_MET -> checkDuplicateElement(555, lexeme, line);
                case DEC_CLASS -> checkDuplicateElement(557, lexeme, line);
                case DEC_INTER -> checkDuplicateElement(559, lexeme, line);
                case DEC_GET -> checkDuplicateElement(560, lexeme, line);
                case DEC_SET -> checkDuplicateElement(561, lexeme, line);
            }
            elementsStack.add(new Element(lexeme, classType, ambitNumber));
            Element lastElement = elementsStack.peek();
            switch (currentType) {
                case DEC_VAR -> {
                    if (decParameters) {
                        tempParameters++;
                        lastElement.setParType(elementsStack.get(tempPosition).getId());
                        lastElement.setParQuantity(tempParameters);
                    }
                }
                case DEC_MET, DEC_FUN, DEC_GET, DEC_SET -> {
                    tempPosition = elementsStack.size() - 1;
                    lastElement.setType("void");
                    lastElement.setParType(String.valueOf(ambit));
                }
                case DEC_CLASS, DEC_INTER -> lastElement.setParType(String.valueOf(ambit));
            }
        }
        if (!elementsStack.isEmpty()) {
            Element lastElement = elementsStack.peek();
            switch (currentType) {
                case DEC_VAR -> setType(lexeme, topSyntaxStack, lastElement);
                case DEC_MET, DEC_FUN, DEC_GET, DEC_SET, DEC_AN_FUN ->
                        setType(lexeme, topSyntaxStack, elementsStack.get(tempPosition));
            }
        }
    }

    private String getClassType() {
        String classType = "";
        switch (currentType) {
            case DEC_VAR -> classType = "variable";
            case DEC_MET -> classType = "método";
            case DEC_FUN -> classType = "función";
            case DEC_CLASS -> classType = "clase";
            case DEC_INTER -> classType = "interface";
            case DEC_GET -> classType = "get";
            case DEC_SET -> classType = "set";
            case DEC_AN_FUN -> classType = "función anónima";
            case DEC_AN_MET -> classType = "método anónimo";
        }
        return classType;
    }

    private void setType(String lexeme, int topSyntaxStack, Element element) {
        switch (topSyntaxStack) {
            case -57 -> customType = true;
            case -58 -> {
                if (idType) {
                    idType = false;
                }
                if (customType) {
                    element.setType("#" + lexeme);
                    customType = false;
                }
            }
            case -61 -> element.setType("null");
            case -71 -> element.setType("real");
            case -72 -> element.setType("boolean");
            case -90 -> element.setType("number");
            case -91 -> element.setType("string");
        }
    }

    private void printAmbitAction(String action, int ambitNumber, int ambitLine) {
        String content = "Se " + action + " el ámbito: [" + ambitNumber + ", " + ambitLine + "]\n";
        writeTxt(content);
        StringBuilder formattedStack = new StringBuilder("Pila ");
        if (ambitStack.empty()) {
            formattedStack.append("vacía\n\n");
        } else {
            formattedStack.append("-> [");
            int stackSize = ambitStack.size();
            for (int i = 0; i < stackSize; i++) {
                formattedStack.append(ambitStack.get(i).getId());
                if (i < stackSize - 1) {
                    formattedStack.append(", ");
                }
            }
            formattedStack.append("]\n\n");
        }
        writeTxt(String.valueOf(formattedStack));
    }

    private void printAreaAction(int line, String type, String action) {
        String content = line + ", " + type + ", " + action + "\n\n";
        writeTxt(content);
    }

    private void writeTxt(String content) {
        try {
            txtResult.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getColumn(char character) {
        switch (character) {
            case '+' -> {
                return 0;
            }
            case '-' -> {
                return 1;
            }
            case '~' -> {
                return 2;
            }
            case '|' -> {
                return 3;
            }
            case '&' -> {
                return 4;
            }
            case '^' -> {
                return 5;
            }
            case ',' -> {
                return 6;
            }
            case '.' -> {
                return 7;
            }
            case ';' -> {
                return 8;
            }
            case ':' -> {
                return 9;
            }
            case '*' -> {
                return 10;
            }
            case '/' -> {
                return 11;
            }
            case '%' -> {
                return 12;
            }
            case '<' -> {
                return 13;
            }
            case '>' -> {
                return 14;
            }
            case '=' -> {
                return 15;
            }
            case '!' -> {
                return 16;
            }
            case '?' -> {
                return 17;
            }
            case '{' -> {
                return 18;
            }
            case '}' -> {
                return 19;
            }
            case '[' -> {
                return 20;
            }
            case ']' -> {
                return 21;
            }
            case '(' -> {
                return 22;
            }
            case ')' -> {
                return 23;
            }
            case '"' -> {
                return 24;
            }
            case '\'' -> {
                return 25;
            }
            case '#' -> {
                return 27;
            }
            case '@' -> {
                return 29;
            }
            case '_' -> {
                return 30;
            }
            case ' ' -> {
                return 31;
            }
            case '\n' -> {
                return 32;
            }
            case '\t' -> {
                return 33;
            }
            default -> {
                if (character >= '0' && character <= '9') return 26;
                else if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z') return 28;
                return 34;
            }
        }
    }

    private int getKeywordToken(String lexeme, int state) {
        if (Arrays.asList(keywords).contains(lexeme)) return state - Arrays.asList(keywords).indexOf(lexeme) - 1;
        return state;
    }

    private void checkDuplicateElement(int error, String lexeme, int lineNum) {
        for (Element element : elementsStack) {
            if (element.getId().equals(lexeme)) {
                var elementAmbit = element.getAmbit();
                var ambitOpened = false;
                for (Ambit activeAmbit : ambitStack) {
                    if (activeAmbit.getId() == elementAmbit) {
                        switch (currentType) {
                            case DEC_GET -> {
                                if (element.getClassType().equals("get")) ambitOpened = true;
                            }
                            case DEC_SET -> {
                                if (element.getClassType().equals("set")) ambitOpened = true;
                            }
                            default -> ambitOpened = true;
                        }
                        break;
                    }
                }
                if (ambitOpened) addError(error, lexeme, ErrorType.AMBIT, lineNum);
            }
        }
    }

    private void addError(int error, String lexeme, ErrorType errorType, int lineNum) {
        errorsPanel.addError(error, lexeme, errorType, lineNum);
        errorTypesPanel.addCounter(errorType);
        countersPanel.addCounter(error);
        if (errorType == ErrorType.AMBIT) {
            int currentAmbit = ambitStack.peek().getId();
            for (Ambit tempAmbit : ambits) {
                if (tempAmbit.getId() == currentAmbit) {
                    tempAmbit.incErrors();
                    break;
                }
            }
        }
    }

    final void loadLexicMatrix() {
        try {
            FileInputStream matrixFile = new FileInputStream("./src/main/resources/matrix-lexic.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(matrixFile);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                int columnCount = row.getLastCellNum();

                if (i == 1) lexicMatrix = new int[rowCount][columnCount - 1];

                for (int j = 1; j < columnCount; j++) {
                    Cell cell = row.getCell(j);
                    int cellValue = (int) cell.getNumericCellValue();
                    lexicMatrix[i - 1][j - 1] = cellValue;
                }
            }
        } catch (IOException e) {
            final Object[] options = {"OK"};
            final int selection = JOptionPane.showOptionDialog(JOptionPane.getFrameForComponent(this), "El archivo .xlsx de la matríz no pudo ser encontrado.", "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
            if (selection == JOptionPane.OK_OPTION) System.exit(0);
        }
    }

    final void loadSyntaxMatrix() {
        try {
            FileInputStream matrixFile = new FileInputStream("./src/main/resources/matrix-syntax.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(matrixFile);
            XSSFSheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                int columnCount = row.getLastCellNum();

                if (i == 1) syntaxMatrix = new int[rowCount][columnCount - 2];

                for (int j = 2; j < columnCount; j++) {
                    Cell cell = row.getCell(j);
                    int cellValue = (int) cell.getNumericCellValue();
                    syntaxMatrix[i - 1][j - 2] = cellValue;
                }
            }
        } catch (IOException e) {
            final Object[] options = {"OK"};
            final int selection = JOptionPane.showOptionDialog(JOptionPane.getFrameForComponent(this), "El archivo .xlsx de la matríz no pudo ser encontrado.", "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
            if (selection == JOptionPane.OK_OPTION) System.exit(0);
        }
    }

    final void loadSemanticaMatrix() {
        try {
            FileInputStream matrixFile = new FileInputStream("./src/main/resources/matrix-semantica.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(matrixFile);
            int sheetCount = workbook.getNumberOfSheets();
            for (int i = 0; i < sheetCount; i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                int rowCount = sheet.getLastRowNum();
                for (int j = 1; j <= rowCount; j++) {
                    Row row = sheet.getRow(j);
                    int columnCount = row.getLastCellNum();

                    if (i == 0 && j == 1) semMatrix = new int[sheetCount][rowCount][columnCount - 1];

                    for (int k = 1; k < columnCount; k++) {
                        Cell cell = row.getCell(k);
                        int cellValue = (int) cell.getNumericCellValue();
                        semMatrix[i][j - 1][k - 1] = cellValue;
                    }
                }
            }
        } catch (IOException e) {
            final Object[] options = {"OK"};
            final int selection = JOptionPane.showOptionDialog(JOptionPane.getFrameForComponent(this), "El archivo .xlsx de la matríz no pudo ser encontrado.", "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
            if (selection == JOptionPane.OK_OPTION) System.exit(0);
        }
    }

    public void setCode(String fileContent) {
        codeArea.setText(fileContent);
    }

    public LinkedList<Ambit> getAmbits() {
        return ambits;
    }

    public LinkedList<Operation> getOperations() {
        return operations;
    }

    public LinkedList<Semantics> getSemanticsList() {
        return semanticsList;
    }
}
