package view;

import model.ErrorType;
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
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Code extends PanelTemplate {
    private final String[] keywords = {"true", "false", "null", "if", "else", "switch", "for", "do", "while", "Console", "log", "fuction", "real", "boolean", "Array", "new", "read", "case", "default", "return", "expo", "sqrtv", "ConvBase", "asc", "sen", "val", "cos", "tan", "break", "let", "interface", "number", "string", "set", "get", "class", "toLowerCase", "toUpperCase", "legth", "trim", "charAt", "startsWith", "endsWith", "indexOf", "includes", "slice", "replace", "split", "in", "of", "Map", "forEach", "any", "const", "continue", "concat", "find", "findIndex", "filter", "push", "shift", "sort", "reverse", "splice", "typeof", "undefined"};
    private final int[][] productions = {{201, -46, 254, 206, -47},     // 0
            {247, 201},        // 1
            {207, 201},        // 2
            {220, 202, 203},      // 3
            {210, 204},        // 4
            {-17, 220, 202},      // 5
            {210, 205},         // 6
            {-17, 210, 204},     // 7
            {-17, 210, 205},      // 8
            {-17, 254, 206},    // 9
            {-17, 254, 206},   // 10
            {-93, -57, -46, 246, 208, 249, 209, -47},    // 11
            {-17, 246, 208},    // 12
            {249, 209},    // 13
            {-69, -57, -50, 246, 211, -51, 212, -46, 254, 213, -47},    // 14
            {-15, 246, 211},    // 15
            {-18, 218},    // 16
            {-17, 254, 213},    // 17
            {-91, -57, -50, 246, 215, -51, -46, 254, 216, -47},    // 18
            {-15, 246, 215},    // 19
            {-17, 254, 216},    // 20
            {-92, -57, -50, -51, -18, 218, -46, 254, 217, -47},    // 21
            {-17, 254, 217},    // 22
            {-90},    // 23
            {-89},    // 24
            {-71},    // 25
            {-60},    // 26
            {-70},    // 27
            {-52},    // 28
            {-54},    // 29
            {-58},    // 30
            {-59},    // 31
            {-55},    // 32
            {-60},    // 33
            {-87, -57, 221},    // 34
            {-30, 222},    // 35
            {-69, -50, 246, 223, -51, 224, -46, 254, 225, -47},    // 36
            {-15, 246, 223},    // 37
            {-18, 218},    // 38
            {-17, 254, 226},    // 39
            {-50, 246, 226, -51, -41, 254},    // 40
            {-15, 246, 226},    // 41
            {-18, 227},    // 42
            {-72, -28, 228, -32, -38, 229},    // 43
            {218},    // 44
            {-57},    // 45
            {-46, 230, -47}, //46
            {273, 231},    // 47
            {-15, 273, 231},    // 48
            {-73, -72, -50, -51},    // 49
            {218, 232},    // 50
            {-38, 233},    // 51
            {219},    // 52
            {-46, 246, -15, 234, 214, 235, 249, 236, -47},    // 53
            {246, -15},    // 54
            {-15, 214},    // 55
            {-15, 249},    // 56
            {-57, 237},    // 57
            {-38, 238},    // 58
            {219},    // 59
            {-46, 246, -15, 239, 214, 240, 249, 241, -47},    // 60
            {246, -15},    // 61
            {-15, 214},    // 62
            {-15, 249},    // 63
            {242, -28, 218, -15, 218, -32, -38, 243},    // 64
            {-108},    // 65
            {-73, -108, -50, -51},    // 66
            {-48, 244, -49}, // 67
            {273, 245},    // 68
            {-15, 273, 245},    // 69
            {-57, -18, 218},    // 70
            {-88, -57, -46, 246, 248, -47},    // 71
            {-17, 246, 248},    // 72
            {-57, -50, 246, 250, -51, 251, -46, 254, 252, -47},    // 73
            {-15, 246, 250},    // 74
            {-18, 218},    // 75
            {-17, 254, 252},    // 76
            {-38},    // 77
            {-3},    // 78
            {-24},    // 79
            {-21},    // 80
            {-6},    // 81
            {-27},    // 82
            {-12},    // 83
            {-14},    // 84
            {-30},    // 85
            {-36},    // 86
            {-35},    // 87
            {-67, -16, 255},    // 88
            {-61, -50, 273, -51, 254, 257},    // 89
            {-63, -50, 273, -51, -46, -75, 273, -18, 258, 254, 259, -86, 260, -47},    // 90
            {-46, 254, 263, -47},    // 91
            {-66, -50, 273, -51, 254},    // 92
            {273, -17},    // 93
            {-77, 273, -17},    // 94
            {-65, 254, -66, -50, 273, -51, -17},    // 95
            {-64, -50, 264, -51, 254},    // 96
            {-74, -50, 273, 256, -51},    // 97
            {-68, -50, 273, -51},    // 98
            {-15, 273, 256},    // 99
            {-62, 273},    // 100
            {-75, 273, -18, 258},    // 101
            {-17, 254, 259},    // 102
            {-75, 273, -18, 268, -86, 260},    // 103
            {-76, -18, 254, 262},    // 104
            {-17, 254, 261}, //105
            {-17, 254, 262}, //106
            {-17, 254, 263}, //107
            {273, 265, -17, 254, -17, 273, 266},  //108
            {-87, -57, 267, -57},  //109
            {-15, 273, 265},  //110
            {-15, 273, 266},  //111
            {-106},  //112
            {-107},  //113
            {254, 261},  //114
            {-94, -50, -51},  //115
            {-95, -50, -51},  //116
            {-96},  //117
            {-97, -50, -51},  //118
            {-98, -50, 273, -51},  //119
            {-99, -50, 273, -51},  //120
            {-100, -50, 273, -51},  //121
            {-101, -50, 273, -51},  //122
            {-102, -50, 273, -51},  //123
            {-103, -50, 273, -15, 273, -51},  //124
            {-104, -50, 273, -15, 273, -51},  //125
            {-105, -50, 273, -51},  //126
            {-78, -50, 273, -15, 273, -51},  //127
            {-79, -50, 273, -15, 273, -51},  //128
            {-80, -50, 273, -15, 273, -15, -51},  //129
            {-81, -50, 273, -51},  //130
            {-82, -50, 273, -51},  //131
            {-83, -50, 273, -51},  //132
            {-84, -50, 273, -51},  //133
            {-85, -50, 273, -51},  //134
            {269},  //135
            {-48, 273, 272, -49},  //136
            {-15, 273, 272},  //137
            {275, 274},  //138
            {-9, 275, 274},  //139
            {-8, 275, 274},  //140
            {277, 276},  //141
            {-11, 277, 276},  //142
            {-10, 277, 276},  //143
            {-13, 277, 276},  //144
            {279, 278},  //145
            {-28, 279, 278},  //146
            {-31, 279, 278},  //147
            {-39, 279, 278},  //148
            {-43, 279, 278},  //149
            {-37, 279, 278},  //150
            {-32, 279, 278},  //151
            {-40, 279, 278},  //152
            {-44, 279, 278},  //153
            {281, 280},  //154
            {-4, 281, 280},  //155
            {-1, 281, 280},  //156
            {-29, 281, 280},  //157
            {-33, 281, 280},  //158
            {-34, 281, 280},  //159
            {283, 282},  //160
            {-19, 283, 282},  //161
            {-22, 283, 282},  //162
            {-26, 283, 282},  //163
            {285, 284},  //164
            {-20, 285, 284},  //165
            {219},  //166
            {286, -57, 287},  //167
            {292, -50, 273, -51},  //168
            {270},  //169
            {-2},  //170
            {-5},  //171
            {271, 288},  //172
            {-50, 290, -51},  //173
            {253, 273, 289},  //174
            {-45, 273, -18, 273},  //175
            {273, 291},  //176
            {-15, 273, 291},  //177
            {-42},  //178
            {-7}  //179
    };
    private final RSyntaxTextArea codeArea = new RSyntaxTextArea();
    private final Tokens tokenPanel;
    private final Counters countersPanel;
    private final Errors errorsPanel;
    private final ErrorTypes errorTypesPanel;
    private final LinkedList<model.Token> syntaxTokens = new LinkedList<>();
    private final Stack<Integer> syntaxStack = new Stack<>();
    private int[][] lexicMatrix;
    private int[][] syntaxMatrix;

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
    }

    public void compile() {
        syntaxTokens.clear();
        syntaxStack.clear();
        syntaxStack.push(200);
        tokenPanel.emptyTokensList();
        countersPanel.restartCounter();
        errorsPanel.emptyErrorsList();
        errorTypesPanel.restartCounter();

        checkLexic();
        checkSyntax();

        tokenPanel.updateTable();
        countersPanel.updateTable();
        errorsPanel.updateTable();
        errorTypesPanel.updateTable();
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
                if (state == -57) state = getKeywordToken(lexeme.toString(), state);
                if (state == -25) {
                    tokenPanel.addToken(state, lexeme.toString(), multiCommentLineNum);
                } else {
                    tokenPanel.addToken(state, lexeme.toString(), lineNum);
                }
                if (state != -25 && state != 23) syntaxTokens.add(new model.Token(state, lexeme.toString(), lineNum));
                countersPanel.addCounter(state);
                lexeme.setLength(0);
                i--;
                state = 0;
            } else if (state >= 500) {
                if (state == 500) {
                    lexeme.append(character);
                } else i--;
                countersPanel.addCounter(state);
                errorsPanel.addError(state, lexeme.toString(), ErrorType.LEXIC, lineNum);
                errorTypesPanel.addCounter(ErrorType.LEXIC);
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
            countersPanel.addCounter(state);
            errorsPanel.addError(state, lexeme.toString(), ErrorType.LEXIC, multiCommentLineNum);
            errorTypesPanel.addCounter(ErrorType.LEXIC);
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

                if (production > 500) {
                    errorsPanel.addError(production, lexeme, ErrorType.SINTAXIS, lineNum);
                    errorTypesPanel.addCounter(ErrorType.SINTAXIS);
                    syntaxTokens.removeFirst();
                } else if (production == 180) {
                    syntaxStack.pop();
                } else {
                    syntaxStack.pop();
                    for (int j = productions[production].length - 1; j >= 0; j--) {
                        syntaxStack.push(productions[production][j]);
                    }
                }
            } else if (topSyntaxStack < 0) {
                int token = syntaxTokens.getFirst().token();
                if (token == topSyntaxStack) {
                    syntaxStack.pop();
                    syntaxTokens.removeFirst();
                } else if (token == -53 && topSyntaxStack == -52) {
                    syntaxStack.pop();
                    syntaxTokens.removeFirst();
                } else {
                    System.out.println("Error de fuerza bruta.");
                    syntaxStack.pop();
                    syntaxTokens.removeFirst();
                }
            }
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
            /*
            case '#' -> {
                return 27;
            }
             */
            case '@' -> {
                return 28;
            }
            case '_' -> {
                return 29;
            }
            case ' ' -> {
                return 30;
            }
            case '\n' -> {
                return 31;
            }
            case '\t' -> {
                return 32;
            }
            default -> {
                if (character >= '0' && character <= '9') return 26;
                else if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z') return 27;
                return 33;
            }
        }
    }

    private int getKeywordToken(String lexeme, int state) {
        if (Arrays.asList(keywords).contains(lexeme)) return state - Arrays.asList(keywords).indexOf(lexeme) - 1;
        return state;
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

    public void setCode(String fileContent) {
        codeArea.setText(fileContent);
    }
}
