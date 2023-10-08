package model;

public class Operation {

    private final int line;
    private int strings, numbers, booleans, reals, customs, voids, variants, errors;
    private String assignation;

    public Operation(int line) {
        this.line = line;
    }

    public void addCounter(Type type) {
        switch (type) {
            case STRING -> strings++;
            case NUMBER -> numbers++;
            case BOOLEAN -> booleans++;
            case REAL -> reals++;
            case CUSTOM -> customs++;
            case VOID -> voids++;
            case VARIANT -> variants++;
        }
    }

    public void addError() {
        errors++;
    }

    public int getLine() {
        return line;
    }

    public int getStrings() {
        return strings;
    }

    public int getNumbers() {
        return numbers;
    }

    public int getBooleans() {
        return booleans;
    }

    public int getReals() {
        return reals;
    }

    public int getCustoms() {
        return customs;
    }

    public int getVoids() {
        return voids;
    }

    public int getVariants() {
        return variants;
    }

    public int getErrors() {
        return errors;
    }

    public String getAssignation() {
        return assignation;
    }

    public void setAssignation(String assignation) {
        this.assignation = assignation;
    }
}
