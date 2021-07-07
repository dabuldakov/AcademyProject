package restaurant2;

import java.util.Arrays;

public class Array {

    private Object[] array;
    private int length;

    public Array() {
        length = 0;
    }

    public Object[] getArray() {
        return array;
    }

    public void setArray(Object[] array) {
        this.array = array;
    }

    public int size() {
        return length;
    }

    public void add(Object o) {
        length = length + 1;
        Object[] newArray = new Object[length];

        for (int i = 0; i < length - 1; i++) {
            newArray[i] = array[i];
        }
        newArray[length - 1] = o;
        array = newArray;
    }

    public boolean contain(Object o) {
        for (int i = 0; i < length; i++) {
            if (array[i].equals(o))
                return true;
        }
        return false;
    }

    public int get(Object o) {
        for (int i = 0; i < length; i++) {
            if (array[i].equals(o))
                return i;
        }
        return -1;
    }

    boolean remove(int index) {
        if (index < 0 || index >= length) {
            return false;
        } else {
            Object[] newArray = new Object[length - 1];
            int offset = 0;
            for (int i = 0; i < length; i++) {
                if (index != i) {
                    newArray[i - offset] = array[i];
                } else {
                    offset = 1;
                }
            }
            array = newArray;
            length = length - 1;
            return true;
        }
    }

    int removeAll(Object o) {
        int deleted = 0;
        Object[] newArray = new Object[length];
        for (int i = 0; i < length; i++) {
            if (!array[i].equals(o)) {
                newArray[i - deleted] = array[i];
            } else {
                deleted++;
            }
        }
        if (deleted > 0) {
            cutArray(newArray, deleted);
            return deleted;
        } else {
            return 0;
        }
    }

    private void cutArray(Object[] array, int cutNumber) {
        Object[] newArray = new Object[length - cutNumber];
        for (int i = 0; i < length - cutNumber; i++) {
            newArray[i] = array[i];
        }
        this.array = newArray;
        this.length = length - cutNumber;
    }

    public boolean update(Object existing, Object updateTo) {
        for (int i = 0; i < length; i++) {
            if (array[i].equals(existing)) {
                array[i] = updateTo;
                return true;
            }
        }
        return false;
    }

    void print() {
        System.out.println("-----------------------");
        for (int i = 0; i < length; i++) {
            System.out.println("[" + i + "] " + array[i]);
        }
        System.out.println("Array size: " + length);
    }

    @Override
    public String toString() {
        return "Array{" +
                "array=" + Arrays.toString(array) +
                ", length=" + length +
                '}';
    }
}
