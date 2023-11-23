package model;

public class Quadruple {

    private final int ambit;
    private int
            // temps
            tempBooleans, tempNumbers, tempReals, tempStrings, tempNulls, tempVariants,

            // operations
            calls, assignations, relationalOperators, logicalOperators, arithmeticOperators, unaryOperation,

            // jumps
            jumpFalses, jumpTrues, jumps,

            // labels
            ifLabels, forLabels, whileLabels, switchLabels, functionLabels, mainLabels;

    public Quadruple(int ambit) {
        this.ambit = ambit;
    }

    public int getAmbit() {
        return ambit;
    }

    public int getTempBooleans() {
        return tempBooleans;
    }

    public void increaseTempBooleans() {
        tempBooleans++;
    }

    public int getTempNumbers() {
        return tempNumbers;
    }

    public void increaseTempNumbers() {
        tempNumbers++;
    }

    public int getTempReals() {
        return tempReals;
    }

    public void increasetempReals() {
        tempReals++;
    }

    public int getTempStrings() {
        return tempStrings;
    }

    public void increaseTempStrings() {
        tempStrings++;
    }

    public int getTempNulls() {
        return tempNulls;
    }

    public void increaseTempNulls() {
        tempNulls++;
    }

    public int getTempVariants() {
        return tempVariants;
    }

    public void increaseTempVariants() {
        tempVariants++;
    }

    public int getCalls() {
        return calls;
    }

    public void increaseCalls() {
        calls++;
    }

    public int getAssignations() {
        return assignations;
    }

    public void increaseAssignations() {
        assignations++;
    }

    public int getRelationalOperators() {
        return relationalOperators;
    }

    public void increaseRelationalOperators() {
        relationalOperators++;
    }

    public int getLogicalOperators() {
        return logicalOperators;
    }

    public void increaseLogicalOperators() {
        logicalOperators++;
    }

    public int getArithmeticOperators() {
        return arithmeticOperators;
    }

    public void increaseArithmeticOperators() {
        arithmeticOperators++;
    }

    public int getUnaryOperation() {
        return unaryOperation;
    }

    public void increaseUnaryOperations() {
        unaryOperation++;
    }

    public int getJumpFalses() {
        return jumpFalses;
    }

    public void increaseJumpFalses() {
        jumpFalses++;
    }

    public int getJumpTrues() {
        return jumpTrues;
    }

    public int getJumps() {
        return jumps;
    }

    public void increaseJumps() {
        jumps++;
    }

    public int getIfLabels() {
        return ifLabels;
    }

    public void increaseIfLabels() {
        ifLabels++;
        jumpFalses++;
    }

    public int getForLabels() {
        return forLabels;
    }

    public void increaseForLabels() {
        forLabels++;
    }

    public int getWhileLabels() {
        return whileLabels;
    }

    public void increaseWhileLabels() {
        whileLabels++;
        jumpFalses++;
        jumps++;
    }

    public void increaseDoWhileLabels() {
        whileLabels++;
        jumpTrues++;
    }

    public int getSwitchLabels() {
        return switchLabels;
    }

    public void increaseSwitchLabels() {
        switchLabels++;
    }

    public int getFunctionLabels() {
        return functionLabels;
    }

    public void increaseFunctionLabels() {
        functionLabels++;
    }

    public int getMainLabels() {
        return mainLabels;
    }

    public void increaseMainLabels() {
        mainLabels++;
    }

    @Override
    public String toString() {
        return "Quadruple{" +
                "ambit=" + ambit +
                ", tempBooleans=" + tempBooleans +
                ", tempNumbers=" + tempNumbers +
                ", tempReals=" + tempReals +
                ", tempStrings=" + tempStrings +
                ", tempNulls=" + tempNulls +
                ", tempVariants=" + tempVariants +
                ", calls=" + calls +
                ", assignations=" + assignations +
                ", relationalOperators=" + relationalOperators +
                ", logicalOperators=" + logicalOperators +
                ", arithmeticOperators=" + arithmeticOperators +
                ", unaryOperation=" + unaryOperation +
                ", jumpFalses=" + jumpFalses +
                ", jumpTrues=" + jumpTrues +
                ", jumps=" + jumps +
                ", ifLabels=" + ifLabels +
                ", forLabels=" + forLabels +
                ", whileLabels=" + whileLabels +
                ", switchLabels=" + switchLabels +
                ", functionLabels=" + functionLabels +
                ", mainLabels=" + mainLabels +
                '}';
    }
}
