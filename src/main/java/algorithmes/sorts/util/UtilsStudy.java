package algorithmes.sorts.util;

import algorithmes.sorts.sortInterface.Sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class UtilsStudy {

    public static ArrayList<Integer> createArrayList() {

        ArrayList<Integer> array = new ArrayList<>();
        Random random = new Random();
        int upperbound = 100000;
        for (int i = 1; i <= 100; i++) {
            array.add(random.nextInt(upperbound));
        }
        return array;
    }

    public static LinkedList<Integer> createLinkedList(int size) {

        LinkedList<Integer> list = new LinkedList<>();
        Random random = new Random();
        int upperbound = 100000;
        for (int i = 1; i <= size; i++) {
            list.add(random.nextInt(upperbound));
        }
        return list;
    }

    public static void printSort(Sort sort) {
        sort.print();
        long startTime = System.currentTimeMillis();
        sort.sort(sort.getArray());
        long endTime = System.currentTimeMillis();
        sort.print();
        System.out.println(sort.getName() + ". Total execution time array: " + (endTime - startTime) + "ms.");
        System.out.println("---------------------");
    }

    public static int[] prepareArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    public static int[] copyArray(int[] array) {
        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }
}
