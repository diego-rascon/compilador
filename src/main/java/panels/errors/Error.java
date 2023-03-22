package panels.errors;

public record Error(int error, String description, String lexeme, String type, int line) {
}
