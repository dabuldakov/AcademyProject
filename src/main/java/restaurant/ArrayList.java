package restaurant;

import java.util.Arrays;

public class ArrayList {

    private Object[] array;
    private int length;
    private int buffer;
    private final float koef = 0.5F;

    public ArrayList() {
        length = 0;
        buffer = 10;
        array = new Object[buffer];
    }

    public int length() {
        return length;
    }

    public void add(Object o) {
        if (length == buffer) {
            buffer = (int) (length * 1.5);
            Object[] newArray = new Object[buffer];
            for (int i = 0; i < length; i++) {
                newArray[i] = array[i];
            }
            newArray[length] = o;
            array = newArray;
        } else {
            array[length] = o;
        }
        length = length + 1;
    }

    public boolean contain(Object o) {
        for (int i = 0; i < length; i++) {
            if (array[i].equals(o))
                return true;
        }
        return false;
    }

    public int getIndex(Object o) {
        for (int i = 0; i < length; i++) {
            if (array[i].equals(o))
                return i;
        }
        return -1;
    }

    public Object get(int index) {
        if (index >= 0 && index < array.length) {
            return array[index];
        } else
            return -1;
    }

    boolean remove(int index) {
        if (index < 0 || index >= length) {
            return false;
        } else {
            if ((float) length / buffer < koef) {
                buffer = (int) (length * 1.5);
                Object[] newArray = new Object[buffer];
                int offset = 0;
                for (int i = 0; i < length; i++) {
                    if (index == i) {
                        offset++;
                    }
                    newArray[i] = array[i + offset];
                }
                array = newArray;
            } else {
                int offset = 0;
                for (int i = 0; i < length; i++) {
                    if (index == i) {
                        offset++;
                        array[i] = array[i + offset];
                    } else if (offset > 0) {
                        array[i] = array[i + offset];
                    }
                }
            }
        }
        length = length - 1;
        return true;
    }

    /*boolean remove(int index) {
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
    }*/



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
            if((float) (length-deleted) / buffer < koef)


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
        System.out.println("ArrayList length: " + length);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ArrayList{");
        for (int i = 0; i < length; i++) {
            stringBuilder.append("[");
            stringBuilder.append(array[i]);
            stringBuilder.append("]");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
