package model;

public class Operation {
    private final int line;
    private int strings, numbers, booleans, reals, customs, voids, variants, errors;
    private String assignation;

    public Operation(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    public int getStrings() {
        return strings;
    }

    public void setStrings(int strings) {
        this.strings = strings;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public int getBooleans() {
        return booleans;
    }

    public void setBooleans(int booleans) {
        this.booleans = booleans;
    }

    public int getReals() {
        return reals;
    }

    public void setReals(int reals) {
        this.reals = reals;
    }

    public int getCustoms() {
        return customs;
    }

    public void setCustoms(int customs) {
        this.customs = customs;
    }

    public int getVoids() {
        return voids;
    }

    public void setVoids(int voids) {
        this.voids = voids;
    }

    public int getVariants() {
        return variants;
    }

    public void setVariants(int variants) {
        this.variants = variants;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public String getAssignation() {
        return assignation;
    }

    public void setAssignation(String assignation) {
        this.assignation = assignation;
    }
}
