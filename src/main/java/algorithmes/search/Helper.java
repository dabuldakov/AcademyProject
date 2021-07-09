package algorithmes.search;

import java.util.Random;

class Helper {

    private static final double LOG_2_DIV = 1.000000000001 / Math.log(2);

    static void goSearch(Searcher searcher, int count){
        for (int [] array: searcher.getArrays()) {
            System.out.println("ArrayList length: " + array.length);
            for (int i = 0; i < count; i++) {
                prepareAnswer(searcher, array);
            }
            System.out.println("------------------");
            System.out.println(searcher.getNAME() + " average: " + searcher.getSum() / searcher.getCounter());
            System.out.println("Log 2 n: " + log2fp0(array.length));
            System.out.println("------------------");
            searcher.setSum(0);
            searcher.setCounter(0);
        }
    }

    private static int log2fp0(int bits) {
        if (bits == 0)
            return 0; // or throw exception
        return (int) (Math.log(bits & 0xffffffffL) * LOG_2_DIV);
    }

    private static void prepareAnswer(Searcher searcher, int[] array){
        Random random = new Random();
        int number = random.nextInt(array.length);
        Answer result = searcher.search(array, number);
        long time = result.getEndTime() - result.getStartTime();
        searcher.setSum(searcher.getSum() + time);
        searcher.setCounter(searcher.getCounter() + 1);
        System.out.println(searcher.getNAME() + " search index: " + result.getLookingIndex() +
                " | number: " + number +
                " | iteration: " + result.getIteration() +
                " | time: " + (time));
    }
}
