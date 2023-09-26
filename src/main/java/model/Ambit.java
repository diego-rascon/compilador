package model;

public class Ambit {
    private final int id;
    private int booleans, numbers, reals, strings, nulls, customs, voids, errors, total;

    public Ambit(int id) {
        this.id = id;
    }

    public void incCounter(String type, int quantity) {
        switch (type) {
            case "boolean" -> booleans = quantity;
            case "number" -> numbers = quantity;
            case "real" -> reals = quantity;
            case "string" -> strings = quantity;
            case "null" -> nulls = quantity;
            case "#" -> customs = quantity;
            case "void" -> voids = quantity;
            case "errors" -> errors = quantity;
        }
        total += quantity;
    }

    public void incErrors() {
        errors++;
    }

    public int getId() {
        return id;
    }

    public int getBooleans() {
        return booleans;
    }

    public int getNumbers() {
        return numbers;
    }

    public int getReals() {
        return reals;
    }

    public int getStrings() {
        return strings;
    }

    public int getNulls() {
        return nulls;
    }

    public int getCustoms() {
        return customs;
    }

    public int getVoids() {
        return voids;
    }

    public int getErrors() {
        return errors;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Ambit{" +
                "number=" + id +
                ", booleans=" + booleans +
                ", numbers=" + numbers +
                ", reals=" + reals +
                ", strings=" + strings +
                ", nulls=" + nulls +
                ", customs=" + customs +
                ", errors=" + errors +
                ", total=" + total +
                '}';
    }
}
