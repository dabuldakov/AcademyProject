package algorithmes.search;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        int[] sortArray1 = IntStream.range(1, 11).toArray();
        int[] sortArray2 = IntStream.range(1, 101).toArray();
        int[] sortArray3 = IntStream.range(1, 1000001).toArray();
        int[] sortArray4 = IntStream.range(1, 10000001).toArray();
        int[] sortArray5 = IntStream.range(1, 100000001).toArray();

        int[][] sortArrays = {sortArray1, sortArray2, sortArray3};

        Searcher bs = new BinarySearch();
        bs.setArrays(sortArrays);
        Searcher ls = new LineSearch();
        ls.setArrays(sortArrays);
        Searcher ss = new StreamSearch();
        ss.setArrays(sortArrays);

        Helper.goSearch(bs, 4);
        Helper.goSearch(ls, 4);
        Helper.goSearch(ss, 4);
    }
}
