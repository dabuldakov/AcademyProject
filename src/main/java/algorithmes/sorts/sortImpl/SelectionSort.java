package algorithmes.sorts.sortImpl;

import algorithmes.sorts.sortInterface.Sort;

import java.util.ArrayList;

public class SelectionSort implements Sort {

    private int[] array;

    public SelectionSort(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public String getName() {
        return "SelectionSort";
    }

    private static int findSmallestInList(ArrayList<Integer> list) {
        int smallest = list.get(0);
        int smallestIndex = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < smallest) {
                smallestIndex = i;
                smallest = list.get(i);
            }
        }
        return smallestIndex;
    }

    private static void selectionSortRecursion(ArrayList<Integer> list, ArrayList<Integer> newList) {
        if (list.size() > 0) {
            int index = findSmallestInList(list);
            newList.add(list.get(index));
            list.remove(index);
            selectionSortRecursion(list, newList);
        }
    }

    static void selectionSortCycle(int[] a) {
        int max = a.length;
        int save = 0;

        for (int i = 0; i < max; i++) {
            for (int j = i + 1; j < max; j++) {
                if (a[j] < a[i]) {
                    save = a[i];
                    a[i] = a[j];
                    a[j] = save;
                }
            }
        }
    }

    static void selectionSortCycle2(int[] a) {
        int max = a.length;
        int save = 0;
        int smallestIndex = 0;
        boolean flag = false;

        for (int i = 0; i < max; i++) {
            smallestIndex = i;
            for (int j = i + 1; j < max; j++) {
                if ((a[j] < a[i]) && (a[j] < a[smallestIndex])) {
                    smallestIndex = j;
                    flag = true;
                }
            }
            if (flag) {
                save = a[i];
                a[i] = a[smallestIndex];
                a[smallestIndex] = save;
            }
            flag = false;
        }
    }

    @Override
    public void sort(int[] array) {
        int max = array.length;
        int index = 0;

        for (int i = 0; i < max; i++) {
            int smallest = array[i];
            for (int j = i + 1; j < max; j++) {
                if (array[j] < smallest) {
                    index = j;
                    smallest = array[j];
                }
            }
            array[index] = array[i];
            array[i] = smallest;
        }
    }
}
