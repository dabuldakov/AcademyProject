package algorithmes.sorts.sortInterface;

public interface Sort {

    void sort(int[] array);

    int[] getArray();

    String getName();

    default void swap(int[] array, int a, int b) {
        int save = array[a];
        array[a] = array[b];
        array[b] = save;
    }

    default void print() {
        for (int el : this.getArray()) {
            System.out.print("[" + el + "]");
        }
        System.out.println();
    }
}
