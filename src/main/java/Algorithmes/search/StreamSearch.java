package Algorithmes.search;

import java.util.Arrays;

class StreamSearch extends Parent implements Searcher{

    public String getNAME() {
        return "Stream";
    }

    @Override
    public Answer search(int[] array, int number) {
        int iterations = 0;
        Answer answer = new Answer(-1, iterations);
        answer.setStartTime(System.nanoTime());

        int count = (int) Arrays.stream(array).filter(x -> x == number).count();

        answer.setEndTime(System.nanoTime());
        answer.setLookingIndex(count);
        return answer;
    }
}
