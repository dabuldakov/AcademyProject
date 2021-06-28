package search;

class BinarySearch extends Parent implements Searcher{

    public String getNAME() {
        return "Binary";
    }

    @Override
    public Answer search(int[] array, int number) {
        int low = 0;
        int high = array.length - 1;
        int mid = -1;
        int guess = 0;
        int iterations = 0;
        Answer answer = new Answer(mid, iterations);
        answer.setStartTime(System.nanoTime());

        while (low <= high) {
            mid = (low + high) / 2;
            guess = array[mid];
            iterations++;

            if (guess == number) {
                answer.setLookingIndex(mid);
                answer.setIteration(iterations);
                answer.setEndTime(System.nanoTime());
                return answer;
            }

            if (guess > number) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        answer.setIteration(iterations);
        answer.setLookingIndex(-1);
        answer.setEndTime(System.nanoTime());
        return answer;
    }
}
