package model;

public class Semantics {
    String topStack, realValue;
    int rule, line, ambit;
    boolean accepted;

    public Semantics(int rule) {
        this.rule = rule;
    }

    public String getTopStack() {
        return topStack;
    }

    public void setTopStack(String topStack) {
        this.topStack = topStack;
    }

    public String getRealValue() {
        return realValue;
    }

    public void setRealValue(String realValue) {
        this.realValue = realValue;
    }

    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getAmbit() {
        return ambit;
    }

    public void setAmbit(int ambit) {
        this.ambit = ambit;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "Semantics{" +
                "topStack='" + topStack + '\'' +
                ", realValue='" + realValue + '\'' +
                ", rule=" + rule +
                ", line=" + line +
                ", ambit=" + ambit +
                ", accepted=" + accepted +
                '}';
    }
}
