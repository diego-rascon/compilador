package model;

import java.util.Objects;

public final class Operand {
    private final int token;
    private final String lexeme;
    private Type type;

    public Operand(int token, String lexeme, Type type) {
        this.token = token;
        this.lexeme = lexeme;
        this.type = type;
    }

    public String lexeme() {
        return lexeme;
    }

    public Type type() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Operand) obj;
        return this.token == that.token &&
                Objects.equals(this.lexeme, that.lexeme) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, lexeme, type);
    }

    @Override
    public String toString() {
        return "Operand[" +
                "token=" + token + ", " +
                "lexeme=" + lexeme + ", " +
                "type=" + type + ']';
    }

}
