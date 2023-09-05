package model;

import java.util.Arrays;

public class Element {

    private final String name;
    private String type;
    private final String classType;
    private final int ambit;
    private int[] arraySize;
    private int arrayDim;
    private int parQuantity;
    private String parType;

    public Element(String name, String classType, int ambit) {
        this.name = name;
        this.classType = classType;
        this.ambit = ambit;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassType() {
        return classType;
    }

    public int getAmbit() {
        return ambit;
    }

    public int[] getArraySize() {
        return arraySize;
    }

    public void setArraySize(int[] arraySize) {
        this.arraySize = arraySize;
    }

    public int getArrayDim() {
        return arrayDim;
    }

    public void setArrayDim(int arrayDim) {
        this.arrayDim = arrayDim;
    }

    public int getParQuantity() {
        return parQuantity;
    }

    public void setParQuantity(int parQuantity) {
        this.parQuantity = parQuantity;
    }

    public String getParType() {
        return parType;
    }

    public void setParType(String parType) {
        this.parType = parType;
    }

    @Override
    public String toString() {
        return String.format("%10s%10s%20s%10d%15s%15d%15d%15s", name, type, classType, ambit, Arrays.toString(arraySize), arrayDim, parQuantity, parType);
    }
}
