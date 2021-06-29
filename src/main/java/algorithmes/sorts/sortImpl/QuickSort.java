package algorithmes.sorts.sortImpl;

import algorithmes.sorts.sortInterface.Sort;

import java.util.ArrayList;

public class QuickSort implements Sort {

    private int[] array;

    public QuickSort(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public String getName() {
        return "QuickSort";
    }

    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static ArrayList<Integer> quickSortArray(ArrayList<Integer> array) {

        if (array.size() < 2) {
            return array;
        } else {
            int pivot = array.get(0);
            array.remove(0);
            ArrayList<Integer> lessArray = new ArrayList<Integer>();
            ArrayList<Integer> greaterArray = new ArrayList<Integer>();
            ArrayList<Integer> newArrayLess;
            ArrayList<Integer> newArrayGreater;

            for (int i : array) {
                if (i < pivot) {
                    lessArray.add(i);
                } else
                    greaterArray.add(i);
            }

            newArrayLess = quickSortArray(lessArray);
            newArrayGreater = quickSortArray(greaterArray);
            newArrayLess.add(pivot);
            newArrayLess.addAll(newArrayGreater);
            return newArrayLess;
        }
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int middle = left + (right - left) / 2;
        int opora = array[middle];
        int i = left, j = right;

        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }
            while (array[j] > opora) {
                j--;
            }
            if (i <= j) {
                this.swap(array, i, j);
                i++;
                j--;
            }
        }
        if (left < j)
            quickSort(array, left, j);
        if (right > i)
            quickSort(array, i, right);
    }
}
