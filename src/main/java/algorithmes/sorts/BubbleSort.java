package algorithmes.sorts;

public class BubbleSort implements Sort{

    private int[] array;

    BubbleSort(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public String getName() {
        return "BubbleSort";
    }

    @Override
    public void sort(int[] array) {
        int size = array.length;
        boolean sorting = true;
        int count = 0;

        while (sorting) {
            sorting = false;

            for (int i = 1; i < size - count; i++) {
                if (array[i-1] > array[i]) {
                    this.swap(array, i, i-1);
                    sorting = true;
                }
            }
            count++;
        }
    }
}
