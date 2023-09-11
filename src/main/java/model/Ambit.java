package model;

public class Ambit {
    private final int id;
    private int booleans, numbers, reals, strings, nulls, customs, errors, total;

    public Ambit(int id) {
        this.id = id;
    }

    public void incCounter(String type) {
        switch (type) {
            case "boolean" -> {
                booleans++;
                total++;
            }
            case "number" -> {
                numbers++;
                total++;
            }
            case "real" -> {
                reals++;
                total++;
            }
            case "string" -> {
                strings++;
                total++;
            }
            case "null" -> {
                nulls++;
                total++;
            }
            case "error" -> errors++;
            default -> {
                if (type.charAt(0) == '#') {
                    customs++;
                    total++;
                }
            }
        }
    }

    public int getId() {
        return id;
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

    public int getNulls() {
        return nulls;
    }

    public int getCustoms() {
        return customs;
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
