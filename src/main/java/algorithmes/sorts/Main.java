package algorithmes.sorts;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] array = prepareArray(30);
        int[] array2 = copyArray(array);
        int[] array3 = copyArray(array);

        Sort quickSort = new QuickSort(array);
        Sort bubbleSort = new BubbleSort(array2);
        Sort selectionSort = new SelectionSort(array3);
        printSort(quickSort);
        printSort(bubbleSort);
        printSort(selectionSort);

    }

    private static void printSort(Sort sort){
        sort.print();
        long startTime = System.currentTimeMillis();
        sort.sort(sort.getArray());
        long endTime = System.currentTimeMillis();
        sort.print();
        System.out.println(sort.getName() + ". Total execution time array: " + (endTime - startTime) + "ms.");
        System.out.println("---------------------");
    }

    private static int[] prepareArray(int size){
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    private static int[] copyArray(int[] array){
        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }
}
