package Restaurant;

import java.util.Arrays;

public class Array {

    private Object object;
    private Object[] array;
    private int length;

    public Array() {
        length = 0;
    }

    public Array(Object object) {
        this.object = object;
        array = new Object[0];
        add(object);
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

    public boolean remove(Object o) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            count++;
            if (array[i].equals(o)) {
                length = length - 1;
                Object[] newArray = new Object[length];
                for (int j = 0; j < count; j++) {
                    newArray[j] = array[j];
                }
                for (int j = count; j < length; j++) {
                    newArray[j] = array[j];
                }
                return true;
            }
        }
        return false;
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

    public void print() {
        System.out.println("-----------------------");
        for (int i = 0; i < length; i++) {
            System.out.println("[" + i + "] " + array[i]);
        }
        System.out.println("Array size: " + length);
    }

    @Override
    public String toString() {
        return "Array{" +
                "object=" + object +
                ", array=" + Arrays.toString(array) +
                ", length=" + length +
                '}';
    }
}
