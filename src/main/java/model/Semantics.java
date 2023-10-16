package model;

public class Semantics {
    String topStack, realValue;
    final int rule, line, ambit;
    boolean accepted;

    public Semantics(int rule, int line, int ambit) {
        this.rule = rule;
        this.line = line;
        this.ambit = ambit;
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

    public int getLine() {
        return line;
    }

    public int getAmbit() {
        return ambit;
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
