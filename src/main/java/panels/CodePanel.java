package panels;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import java.awt.*;
import java.io.IOException;

public class CodePanel extends PanelTemplate {

    private final RSyntaxTextArea codeArea = new RSyntaxTextArea();

    public CodePanel() {
        super("Código");

        final RTextScrollPane scrollPane = new RTextScrollPane(codeArea);

        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_TYPESCRIPT);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setBracketMatchingEnabled(true);
        codeArea.setAntiAliasingEnabled(true);

        try {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
            theme.apply(codeArea);
        } catch (IOException ioe) { // Never happens
            ioe.printStackTrace();
        }

        add(scrollPane, BorderLayout.CENTER);

//        final Color white = new Color(255, 255, 255);
//
//        codeArea.setBackground(new Color(40, 40, 40));
//        codeArea.setForeground(white);
//        codeArea.setSelectedTextColor(white);
//        codeArea.setCurrentLineHighlightColor(new Color(66, 66, 66));
//        codeArea.setSelectionColor(new Color(0, 122, 255));
//        codeArea.setMarginLineColor(Color.red);
    }

    public void compile() {
        final String code = codeArea.getText();
        int state = 0;
        for (int i = 0; i < code.length(); i++) {
            final char character = code.charAt(i);
            System.out.println(character);
        }
    }

    public RSyntaxTextArea getCodeArea() {
        return codeArea;
    }
}
